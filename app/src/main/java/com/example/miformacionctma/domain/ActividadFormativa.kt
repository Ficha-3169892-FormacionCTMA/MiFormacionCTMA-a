package com.example.miformacionctma.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Prioridad {
    BAJA,
    MEDIA,
    ALTA
}

@Entity(tableName = "actividades")
data class ActividadFormativa(
    @PrimaryKey val id: Long,
    val titulo: String,
    val descripcion: String?,
    val progreso: Int,
    val diasRestantes: Int,
    val prioridad: Prioridad
)
