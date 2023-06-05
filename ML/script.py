import numpy as np
import pandas as pd
import tensorflow as tf
import mysql.connector
import json
from sklearn.preprocessing import StandardScaler
from datetime import datetime

#Defining preprocessing function
def preprocess_dt(dt_users, dt_activities):
    
    #Preprocessing user dataset 
    #Slice necessary columns and convert the ID to start from 0
    dt_users = dt_users.iloc[:, :-1]
    dt_users['user_id'] = dt_users['user_id'].apply(lambda x: x-1)
    
    #Reordering the gender column position and dropping unnecessary columns
    dt_users['gender_P'] = dt_users['gender']
    dt_users.drop(columns = ['user_name', 'email', 'passwords', 'gender'], inplace = True)
    
    
    #Preprocessing activity dataset
    #Slice necessary columns and convert the ID to start from 0
    dt_activities = dt_activities.iloc[:, :-3]
    dt_activities['act_id'] = dt_activities['act_id'].apply(lambda x: x-1)
    
    #Convert the datetime columns into datetime dtype
    fmt = '%Y-%m-%d %H:%M:%S'
    dt_activities['time_start'] = dt_activities['time_start'].apply(lambda x: datetime.strptime(str(x), fmt))
    dt_activities['time_end'] = dt_activities['time_end'].apply(lambda x: datetime.strptime(str(x), fmt))
    
    #Calculating the duration variable from the differences of ending and starting datetime
    dt_activities['duration'] = dt_activities['time_end'] - dt_activities['time_start']
    dt_activities['duration'] = dt_activities['duration'].apply(lambda x:(x.days * 24 * 60) + (x.seconds/60))
    
    #Calculating the daytime binary variable from ending and starting datetime
    daytime_start = dt_activities["time_start"].apply(lambda x: 1 if 6<= x.hour <= 18 else 0)
    daytime_end = dt_activities["time_end"].apply(lambda x: 1 if 6<= x.hour <= 18 else 0)
    dt_activities["daytime"] = [1 if daytime_start[x]==1 and daytime_end[x]==1 and dt_activities["duration"][x] < (12*60) else 0 for x in range(len(daytime_start))]
    
    #Parsing the category value into one-hot variables
    dt_activities['sports'] = dt_activities['category'].apply(lambda x: 1 if "sports" in x else 0)
    dt_activities['arts'] = dt_activities['category'].apply(lambda x: 1 if "arts" in x else 0)
    dt_activities['travel'] = dt_activities['category'].apply(lambda x: 1 if "travel" in x else 0)
    dt_activities['edu'] = dt_activities['category'].apply(lambda x: 1 if "edu" in x else 0)
    
    #Calculating the weekend binary variable from ending and starting datetime
    weekend_start = dt_activities["time_start"].apply(lambda x: 1 if x.weekday() in [5,6] else 0)
    weekend_end = dt_activities["time_end"].apply(lambda x: 1 if x.weekday() in [5,6] else 0)
    dt_activities["weekend"] = [1 if weekend_start[x]==1 or weekend_end[x]==1 else 0 for x in range(len(weekend_start))]
    
    #One-hot the community variables and drop unnecessary columns
    dt_activities = pd.get_dummies(dt_activities, columns=['community'], drop_first=True)
    dt_activities.drop(columns = ['act_name', 'time_start', 'time_end', 'location', 'category'], inplace=True)
    
    #Creating user-activity interaction matrix
    matrix = np.zeros((len(dt_users), len(dt_activities)), dtype=int)    
    matrix = pd.DataFrame(matrix)
    #Stacking the matrix back into tabular structure and merging with join left the data from users and activities dataset
    dt_interact = matrix.stack(dropna=True).reset_index().rename(columns={"level_0":"user_id", "level_1":"act_id", 0:"y"})    
    dt_interact = dt_interact.merge(dt_activities, how="left", left_on="act_id", right_on="act_id")
    dt_interact['user_id'].replace([0], dt_users['user_id'][0], inplace = True)
    
    dt_interact = dt_interact.merge(dt_users, how="left", left_on="user_id", right_on="user_id")
    
    
    #Preprocessing predictor and target variable
    #Slice necessary values accordingly
    y = dt_interact.iloc[:,2]
    X = dt_interact.iloc[:,3:]
    
    #Slice necessary act and user features accordingly
    xact = X.iloc[:,:-8]
    xuser = X.iloc[:,-8:]
    
    #Standardize the scale of act features
    scalerAct = StandardScaler()
    scalerAct.fit(xact)
    xact = scalerAct.transform(xact)
    
    #Standardize the scale of user features
    scalerUser = StandardScaler()
    scalerUser.fit(xuser)
    xuser = scalerUser.transform(xuser)
    
    return xuser, xact

def run_model(xuser, xact, model_filename = 'model.h5'):
    
    tf.random.set_seed(1)
    saved_model = tf.keras.models.load_model(model_filename, compile=False)
    
    opt = tf.keras.optimizers.Adam(learning_rate=0.001)
    cost_fn = tf.keras.losses.BinaryFocalCrossentropy()
    auc = tf.keras.metrics.AUC(name="auc")
    stop_early = tf.keras.callbacks.EarlyStopping(monitor='val_loss', patience=10)
    
    saved_model.compile(optimizer=opt, loss=cost_fn, metrics = auc)
        
    y_out = saved_model.predict([xuser, xact])
    
    return y_out

def user_inference(user_dict):
    #Convert the new user dictionary into a dataframe
    dt_users = pd.DataFrame(user_dict, index = [0])
    
    #Connect to cloud database
    config = {
    'user': 'machine-learning',
    'password': 'mlminsatu',
    'host': '34.101.62.4',
    'database': 'minsatu_usersdata',
    'unix_socket': '/cloudsql/minsatu-capstone:asia-southeast2:users-data-asia'
    }
    
    mydb = mysql.connector.connect(**config)
    
    #Preprocess with the defined function
    dt_activities = pd.read_sql("SELECT * FROM activities",  mydb)
    xuser, xact = preprocess_dt(dt_users, dt_activities)
    
    #Run inference with the defined function
    y_out = run_model(xuser, xact, 'model.h5')
    
    #Sorting the values from the highest probability of interest and showing the corresponding activity id
    y_out = pd.DataFrame(y_out)    
    y_out.sort_values(by=[0], ascending=False, inplace=True)
    rank = y_out.index + 1
    return rank

new_user = ##GET parameter.json dari API CC
rank = user_inference(new_user)
output_dict = {"rank": list(rank)}
with open("output.json", "w") as f:
    json.dump(output_dict, f)