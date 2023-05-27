#!/usr/bin/env python
# coding: utf-8

# In[1]:


#import libraries
import numpy as np
import pandas as pd
import tensorflow as tf
import matplotlib.pyplot as plt
from tensorflow import keras
from sklearn.metrics import classification_report, f1_score
from sklearn.preprocessing import StandardScaler, MinMaxScaler
from sklearn.model_selection import train_test_split
from datetime import datetime


# In[212]:


#Import users data
dt_users = pd.read_csv("users.csv")
dt_users = dt_users.iloc[:, :-8]

#One hot gender into binary features and convert user_id so it starts from 0
dt_users = pd.get_dummies(dt_users, columns=['gender'], drop_first=True)
dt_users['user_id'] = dt_users['user_id'].apply(lambda x: x-1)
dt_users


# In[40]:


#import activities data
dt_activities = pd.read_csv("activities.csv")

#Convert date from string type into date type, then extract weekend binary feature from date variable
dt_activities["date"] = dt_activities["date"].apply(lambda x: datetime.strptime(x, '%d/%m/%Y').date())
dt_activities["weekend"] = dt_activities["date"].apply(lambda x: 1 if x.weekday() in [5,6] else 0)

#Convert act_id so that it starts from 0 and one-hot the community variable into binary features
dt_activities['act_id'] = dt_activities['act_id'].apply(lambda x: x-1)
dt_activities = pd.get_dummies(dt_activities, columns=['community'], drop_first=True)

#Drop unnecessary features
dt_activities = dt_activities.drop(columns = ['date', 'Unnamed: 11', 'Unnamed: 12', 'Unnamed: 13'])
dt_activities


# In[201]:


#Import interaction matrix data and drop unnecessary collumns
dt_interact = pd.read_csv("mat_interaksi.csv")
dt_interact = dt_interact.iloc[:,1:]

#Replace all 0.5-valued cells to 1.0
dt_interact


# In[202]:


#Transform the matrix structure into tabularized structure (stacking) and convert the act_id so that it starts from 0
dt_interact = dt_interact.stack(dropna=True).reset_index().rename(columns={"level_0":"user_id", "level_1":"act_id", 0:"y"})
dt_interact['act_id'] = dt_interact['act_id'].apply(lambda x: int(x)-1)

# dt_interact = pd.get_dummies(dt_interact, columns=['y'])
# dt_interact.rename(columns = {'y_0.0':'no', 'y_1.0':'yes'}, inplace = True)
dt_interact


# In[187]:


#Merge (left join) both the activities and user dataframe into the interaction dataframe
dt_interact = dt_interact.merge(dt_activities, how="left", left_on="act_id", right_on="act_id")
dt_interact = dt_interact.merge(dt_users, how="left", left_on="user_id", right_on="user_id")
dt_interact


# In[188]:


#Slice the collumns into the array accordingly
y = dt_interact.iloc[:,2:3].values
xact = dt_interact.iloc[:,3:-8].values
xuser = dt_interact.iloc[:,-8:].values

print(f"dt_users shape : {xuser.shape}")
print(f"dt_activities shape : {xact.shape}")
print(f"y shape : {y.shape}")


# In[189]:


#Standardize the x features (Y is not scaled for now)
scalerAct = StandardScaler()
scalerAct.fit(xact)
xact = scalerAct.transform(xact)

scalerUser = StandardScaler()
scalerUser.fit(xuser)
xuser = scalerUser.transform(xuser)

# scalerTarget = StandardScaler()
# scalerTarget.fit(y)
# y = scalerTarget.transform(y)


# In[190]:


#Check the proportion of the classes in the sample
print("% of y = 0: ", (y[y == 0].shape[0])/y.shape[0]*100)
# print("% of y = Bucket: ", (y[y[:,1] == 1].shape[0])/y.shape[0]*100)
print("% of y = Joined or Bucketlisted: ", (y[y == 1].shape[0])/y.shape[0]*100)


# In[191]:


xact_train, xact_test, xuser_train, xuser_test, y_train, y_test = train_test_split(xact, xuser, y,
                                                                                   train_size=0.80,
                                                                                   shuffle=True,
                                                                                   random_state=1,
                                                                                   stratify=y
                                                                                  )

# xuser_train, xuser_test = train_test_split(xuser, train_size=0.80, shuffle=True, random_state=1)
# y_train, y_test = train_test_split(y, train_size=0.80, shuffle=True, random_state=1)

print(f"act training data shape: {xact_train.shape}")
print(f"act test data shape: {xact_test.shape}")
print(f"user training data shape: {xuser_train.shape}")
print(f"user test data shape: {xuser_test.shape}")
print(f"y training data shape: {y_train.shape}")
print(f"y test data shape: {y_test.shape}")


# In[192]:


print("% of y_train = 0: ", (y_train[y_train == 0].shape[0])/y_train.shape[0]*100)
# print("% of y_train = Bucket: ", (y_train[y_train[:,1] == 1].shape[0])/y_train.shape[0]*100)
print("% of y_train = Joined or Bucketlisted: ", (y_train[y_train == 1].shape[0])/y_train.shape[0]*100)

print("\n% of y_test = 0: ", (y_test[y_test == 0].shape[0])/y_test.shape[0]*100)
# print("% of y_test = Bucket: ", (y_test[y_test[:,1] == 1].shape[0])/y_test.shape[0]*100)
print("% of y_test = Joined or Bucketlisted: ", (y_test[y_test == 1].shape[0])/y_test.shape[0]*100)


# In[214]:


num_outputs = 64
tf.random.set_seed(1)
user_NN = tf.keras.models.Sequential([   
    
    tf.keras.layers.Dense(32, activation='relu'),
    tf.keras.layers.Dense(num_outputs, activation='relu'),
  
])

act_NN = tf.keras.models.Sequential([
    
    tf.keras.layers.Dense(32, activation='relu'),
    tf.keras.layers.Dense(num_outputs, activation='relu'),

])

num_user_features = xuser_train.shape[1] 
num_act_features = xact_train.shape[1]

input_user = tf.keras.layers.Input(shape=(num_user_features))
vu = user_NN(input_user)
vu = tf.linalg.l2_normalize(vu, axis=1)

# create the item input and point to the base network
input_act = tf.keras.layers.Input(shape=(num_act_features))
va = act_NN(input_act)
va = tf.linalg.l2_normalize(va, axis=1)

# compute the dot product of the two vectors vu and va
dot = tf.keras.layers.Dot(axes=1)([vu, va])
o_NN = tf.keras.models.Sequential([
    
    tf.keras.layers.Dense(32, activation='relu',
#                           kernel_regularizer = tf.keras.regularizers.L2(l2= 0.1),
#                           bias_regularizer = tf.keras.regularizers.L2(l2= 0.001)
                         ),
    tf.keras.layers.Dense(1, activation='sigmoid',
                          kernel_regularizer = tf.keras.regularizers.L2(l2= 1.0),
#                           bias_regularizer = tf.keras.regularizers.L2(l2= 0.001)
                         ),

])
output = o_NN(dot)

# specify the inputs and output of the model
model = tf.keras.Model([input_user, input_act], output)

model.summary()


# In[215]:


tf.random.set_seed(1)
cost_fn = tf.keras.losses.BinaryFocalCrossentropy()
opt = keras.optimizers.Adam(learning_rate=0.001)
stop_early = tf.keras.callbacks.EarlyStopping(monitor='val_loss', patience=10)
auc = tf.keras.metrics.AUC(name="auc")
model.compile(optimizer=opt,
              loss=cost_fn, metrics = auc
             )


# In[216]:


tf.random.set_seed(1)
history = model.fit([xuser_train, xact_train],
                    y_train,
                    epochs=1000,
                    batch_size=16,
                    verbose=2,
                    validation_split = 0.2,
                    callbacks = [stop_early]
                   )


# In[217]:


# Retrieve a list of list results on training and validation data
# sets for each training epoch
loss = history.history['loss']
val_loss = history.history['val_loss']

# Get number of epochs
epochs = range(len(loss))

# Plot training and validation loss per epoch
plt.plot(epochs, loss)
plt.plot(epochs, val_loss)
legend_drawn_flag = True
plt.legend(["Training", "Validation"], loc=0, frameon=legend_drawn_flag)
plt.xlabel('Epoch')
plt.ylabel('Loss (MAE)')
plt.title('Training and Validation Loss (MAE)')

print('Best Model: Min Training Loss (MAE) = {:.3f}, Min Validation Loss (MAE) = {:.3f}'
      .format(min(loss), min(val_loss)))


# In[218]:


# Retrieve a list of list results on training and validation data
# sets for each training epoch
acc = history.history['auc']
val_acc = history.history['val_auc']

# Get number of epochs
epochs = range(len(acc))

# Plot training and validation loss per epoch
plt.plot(epochs, acc)
plt.plot(epochs, val_acc)
legend_drawn_flag = True
plt.legend(["Training", "Validation"], loc=0, frameon=legend_drawn_flag)
plt.xlabel('Epoch')
plt.ylabel('AUC')
plt.title('Training and Validation AUC')

print('Best Model: Max Training AUC = {:.3f}, Max Validation AUC = {:.3f}'
      .format(max(acc), max(val_acc)))


# In[221]:


y_out = model.predict([xuser_test, xact_test])
y_pred = np.array([1 if i >= 0.5 else 0 for i in y_out])
y_pred


# In[223]:


cr = classification_report(y_test, y_pred, zero_division = 0)
print(cr)


# In[235]:


zmax=0
jopt=0
for j in np.arange(0.25, 0.5, 0.1):
    y_pred = np.array([1 if i >= j else 0 for i in y_out])
    z = f1_score(y_test, y_pred, average='weighted', zero_division = 0)
    if z >= zmax:
        zmax = z
        jopt = j
print(j)

cr = classification_report(y_test, np.array([1 if i >= j else 0 for i in y_out]), zero_division = 0)
print(cr)


# In[233]:





# In[ ]:




