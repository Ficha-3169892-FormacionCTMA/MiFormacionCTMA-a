package com.example.miformacionctma.domain


fun validarTitulo(titulo: String): String? {
    return when {
        titulo.isBlank() -> "El título es obligatorio."
        titulo.length < 3 -> "El título debe tener al menos 3 caracteres."
        titulo.length > 80 -> "El título no puede exceder los 80 caracteres."
        else -> null
    }
}

fun validarDescripcion(descripcion: String): String? {
    return if (descripcion.length > 240) {
        "La descripción no puede exceder los 240 caracteres."
    } else null
}

fun validarProgreso(progreso: String): String? {
    val valor = progreso.toIntOrNull()
    return if (valor == null || valor !in 0..100) {
        "El progreso debe ser un número entre 0 y 100."
    } else null
}

fun validarFecha(fecha: String): String? {
    // Validación simple de formato AAAA-MM-DD y que no sea anterior a hoy
    return try {
        val partes = fecha.split("-")
        if (partes.size != 3) return "Formato inválido (AAAA-MM-DD)."
        
        val anio = partes[0].toInt()
        val mes = partes[1].toInt()
        val dia = partes[2].toInt()
        
        val fechaIngresada = java.util.Calendar.getInstance().apply {
            set(anio, mes - 1, dia, 0, 0, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }
        
        val hoy = java.util.Calendar.getInstance().apply {
            set(java.util.Calendar.HOUR_OF_DAY, 0)
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }
        
        if (fechaIngresada.before(hoy)) {
            "La fecha no puede ser anterior a hoy."
        } else null
    } catch (e: Exception) {
        "Fecha inválida."
    }
}

fun estadoActividad(actividad: ActividadFormativa): String {

    return when {
        actividad.progreso == 100 -> "COMPLETADA"
        actividad.diasRestantes < 0 -> "VENCIDA"
        actividad.progreso > 0 -> "EN PROCESO"
        else -> "PENDIENTE"
    }
}

fun actividadesUrgentes(lista: List<ActividadFormativa>): List<ActividadFormativa> {

    return lista.filter {
        it.progreso < 100 && it.diasRestantes <= 2
    }
}

fun promedioProgreso(lista: List<ActividadFormativa>): Double {

    if (lista.isEmpty()) return 0.0

    return lista.map { it.progreso }.average()
}

fun buscarPorTitulo(
    lista: List<ActividadFormativa>,
    titulo: String
): List<ActividadFormativa> {

    return lista.filter {
        it.titulo.trim().contains(
            titulo.trim(),
            ignoreCase = true
        )
    }
}