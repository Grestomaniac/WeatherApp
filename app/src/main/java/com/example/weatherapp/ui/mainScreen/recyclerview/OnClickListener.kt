package com.example.weatherapp.ui.mainScreen.recyclerview

class OnClickListener(val clickListener: (dayIndex: Int) -> Unit) {
    fun onClickItem(dayIndex: Int) = clickListener(dayIndex)
}