package com.example.miformacionctma.domain

data class ActividadFormativa(
    val id: Long,
    val titulo: String,
    val description: String?,
    val progreso: Int,
    val diasRestantes: Int,
    val prioridad: Prioridad
)

enum class Prioridad{
    Baja,
    Media,
    Alta
}

enum class EstadoActividad {
    Pendiente,
    En_progreso,
    Vencida,
    Completada
}

