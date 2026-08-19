package com.example.miformacionctma.data

import androidx.room.TypeConverter
import com.example.miformacionctma.domain.Prioridad

class Converters {
    @TypeConverter
    fun fromPrioridad(prioridad: Prioridad): String {
        return prioridad.name
    }

    @TypeConverter
    fun toPrioridad(prioridadString: String): Prioridad {
        return Prioridad.valueOf(prioridadString)
    }
}
