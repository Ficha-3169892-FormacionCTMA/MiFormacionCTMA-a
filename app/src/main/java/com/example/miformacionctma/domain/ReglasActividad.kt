package com.example.miformacionctma.domain

fun estaAtrasada(actividad: ActividadFormativa): Boolean{
    return actividad.progreso < 100 && actividad.diasRestantes < 0
}

fun porcentajePendiente(actividad: ActividadFormativa): Int{
    return 100 - actividad.progreso
}

fun validarActividad(actividad: ActividadFormativa): List<String> {
    val errores = mutableListOf<String>()

    if (actividad.titulo.isBlank()) {
        errores.add("El título es obligatorio")
    }

    if (actividad.progreso !in 0..100) {
        errores.add("El progreso debe estar entre 0 y 100")
    }

    return errores
}

fun estadoActividad(actividad: ActividadFormativa): EstadoActividad{
    return when {
        actividad.progreso == 100 -> EstadoActividad.Completada
        actividad.diasRestantes < 0 -> EstadoActividad.Vencida
        actividad.progreso == 0 -> EstadoActividad.Pendiente
        else -> EstadoActividad.En_progreso
    }
}

fun actividadesUrgentes(
    actividades: List<ActividadFormativa>
): List<ActividadFormativa> {
    return actividades.filter { actividad ->
        actividad.progreso < 100 &&
                actividad.diasRestantes <= 2
    }
}

fun  promedioProgreso(
    actividades: List<ActividadFormativa>
): Int {
    if (actividades.isEmpty()){
        return 0
    }

    return actividades.map {actividad ->
        actividad.progreso
    }.sum() / actividades.size
}

fun buscarPorTitulo(
    actividades: List<ActividadFormativa>,
    texto: String
): List<ActividadFormativa> {
    val textoLimpio = texto.trim()

    return actividades.filter { actividad ->
        actividad.titulo.contains(textoLimpio, ignoreCase = true)
    }
}