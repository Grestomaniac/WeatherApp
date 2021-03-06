package com.example.weatherapp.ui.mainScreen.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.HourlyWeatherLayoutBinding
import com.example.weatherapp.model.Day
import com.example.weatherapp.ui.mainScreen.recyclerview.models.HourlyForecast

// Адаптер наследуется от ListAdapter для автоматического обновления содержимого
// Также ListAdapter содержит список данных в себе, так что не нужно объявлять для него переменную
class HourlyForecastAdapter(private val clickListener: OnHourlyForecastClickListener): ListAdapter<HourlyForecast, HourlyForecastAdapter.ViewHolder>(HourlyForecastDiffCallback()) {

    // Функция которая вызывается адаптером для создания нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // ViewHolder создаётся не напрямую, а через класс компаньон, для инкапсуляции
        return ViewHolder.from(parent)
    }

    // Функция вызываемая адаптером при добавлении
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Функция getItem просто возвращает элемент из встроенного в ListAdapter списка
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(private val binding: HourlyWeatherLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        // Для инкапсуляции связывание происходит внутри объекта, а не в адаптере
        fun bind(item: HourlyForecast, clickListener: OnHourlyForecastClickListener) {
            // данные передаются для связывания в лэйаут
            binding.hourlyWeather = item
            binding.clickListener = clickListener
            // Вроде бы нужен для анимации
            binding.executePendingBindings()
        }

        // Тут находятся статические функции и переменные
        companion object {
            // Создаёт новый объект ViewHolder
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                // Для инфлейта используется сгенерированный объект Binding
                val binding = HourlyWeatherLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

// Класс необходимый для ListAdapter, который используется им для определения одинаковых и похожих объектов
class HourlyForecastDiffCallback: DiffUtil.ItemCallback<HourlyForecast>() {
    override fun areItemsTheSame(oldItem: HourlyForecast, newItem: HourlyForecast): Boolean {
        // true если два объекта имеют одинаковые ссылки, нужно было что-то вроде PrimaryKey использовать,
        // но думаю для этого адаптера и этого хватит
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: HourlyForecast, newItem: HourlyForecast): Boolean {
        // true если эти два объекта имеют одинаковое содержимое
        return oldItem == newItem
    }

}

class OnHourlyForecastClickListener(val clickListener: (dayIndex: Int) -> Unit) {
    fun onClickItem(dayIndex: Int) = clickListener(dayIndex)
}