package com.example.myapplication2

enum class ActivityType(val displayName: String, val iconRes: Int) {
    BIKE("Велосипед", R.drawable.welcome_screen_image),
    RUN("Бег", R.drawable.welcome_screen_image),
    WALK("Ходьба", R.drawable.welcome_screen_image)
}