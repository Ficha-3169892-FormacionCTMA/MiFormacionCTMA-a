package com.example.miformacionctma.domain

enum class Prioridad {
    BAJA,
    MEDIA,
    ALTA
}

data class ActividadFormativa(
    val id: Long,
    val titulo: String,
    val descripcion: String?,
    val progreso: Int,
    val fecha: String,
    val prioridad: Prioridad
) {
    val diasRestantes: Int
        get() = try {
            val partes = fecha.split("-")
            val anio = partes[0].toInt()
            val mes = partes[1].toInt()
            val dia = partes[2].toInt()
            
            val fechaLimite = java.util.Calendar.getInstance().apply {
                set(anio, mes - 1, dia, 0, 0, 0)
                set(java.util.Calendar.MILLISECOND, 0)
            }
            
            val hoy = java.util.Calendar.getInstance().apply {
                set(java.util.Calendar.HOUR_OF_DAY, 0)
                set(java.util.Calendar.MINUTE, 0)
                set(java.util.Calendar.SECOND, 0)
                set(java.util.Calendar.MILLISECOND, 0)
            }
            
            val diff = fechaLimite.timeInMillis - hoy.timeInMillis
            (diff / (1000 * 60 * 60 * 24)).toInt()
        } catch (e: Exception) {
            0
        }
}
