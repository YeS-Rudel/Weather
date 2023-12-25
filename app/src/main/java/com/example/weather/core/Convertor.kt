package com.example.weather.core

interface Convertor<T, R> {
    fun convertEntityToModel(entity: T): R
}