package com.dicoding.mdminsatuapp.util

import androidx.annotation.DrawableRes
import com.dicoding.mdminsatuapp.R

class OnboardingData(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
)

val listData = listOf(
    OnboardingData(
        R.drawable.onboarding_1,
        "Explore Your Interest",
        "Uncover a diverse range of activities and hobbies tailored to your interests. Explore our curated collection and find the perfect activities that ignite your enthusiasm."
    ),
    OnboardingData(
        R.drawable.onboarding_2,
        "Connect With Communities",
        "Connect with communities that share your passion and values. Meet fellow enthusiasts, newcomers, and locals who are eager to welcome you."
    ),
    OnboardingData(
        R.drawable.onboarding_3,
        "Welcome to MinSatu",
        "Discover, connect, and thrive with -1 (Min Satu) App! Explore a diverse range of activities, connect with like-minded communities, and unlock hidden gems in your city."
    )
)
