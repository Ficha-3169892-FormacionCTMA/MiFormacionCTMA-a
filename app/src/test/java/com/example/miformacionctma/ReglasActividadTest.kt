package com.example.miformacionctma

import com.example.miformacionctma.domain.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ReglasActividadTest {
    @Test
    fun porcentajePendiente_deberiaCalcularCorrectamente() {
        val actividad = ActividadFormativa(
            id = 1,
            titulo = "Kotlin",
            description = "Aprender Kotlin",
            progreso = 60,
            diasRestantes = 5,
            prioridad = Prioridad.Media
        )

        val resultado = porcentajePendiente(actividad)

        assertEquals(40, resultado)
    }

    @Test
    fun estaAtrasada_deberiaRetornarTrue() {
        val actividad = ActividadFormativa(
            id = 2,
            titulo = "Android",
            description = "Practicar Compose",
            progreso = 80,
            diasRestantes = -1,
            prioridad= Prioridad.Alta
        )

        val resultado = estaAtrasada(actividad)

        assertEquals(true, resultado)
    }

    @Test
    fun validarActividad_deberiaRetornarTrue() {
        val actividad = ActividadFormativa(
            id = 3,
            titulo = "Kotlin",
            description = "Aprender Kotlin",
            progreso = 50,
            diasRestantes = 5,
            prioridad = Prioridad.Media
        )

        assertEquals(true, validarActividad(actividad))
    }

    @Test
    fun validarActividad_deberiaRetornarFalseConProgresoInvalido() {
        val actividad = ActividadFormativa(
            id = 4,
            titulo = "Android",
            description = "Practicar Compose",
            progreso = 120,
            diasRestantes = 5,
            prioridad = Prioridad.Media
        )

        assertEquals(false, validarActividad(actividad))
    }

    @Test
    fun estadoActividad_deberiaSerCompletada() {
        val actividad = ActividadFormativa(
            id = 5,
            titulo = "Git",
            description = "Practicar Git",
            progreso = 100,
            diasRestantes = -2,
            prioridad = Prioridad.Baja
        )

        assertEquals(EstadoActividad.Completada, estadoActividad(actividad))
    }

    @Test
    fun estadoActividad_deberiaSerVencida() {
        val actividad = ActividadFormativa(
            id = 6,
            titulo = "Android",
            description = "Terminar pantalla",
            progreso = 80,
            diasRestantes = -1,
            prioridad = Prioridad.Alta
        )

        assertEquals(EstadoActividad.Vencida, estadoActividad(actividad))
    }

    @Test
    fun actividadesUrgentes_deberiaEncontrarActividad() {
        val actividades = listOf(
            ActividadFormativa(
                id = 7,
                titulo = "Kotlin",
                description = "Practicar",
                progreso = 30,
                diasRestantes = 2,
                prioridad = Prioridad.Alta
            ),
            ActividadFormativa(
                id = 8,
                titulo = "Git",
                description = "Practicar Git",
                progreso = 80,
                diasRestantes = 5,
                prioridad = Prioridad.Media
            )
        )

        val resultado = actividadesUrgentes(actividades)

        assertEquals(1, resultado.size)
        assertEquals("Kotlin", resultado[0].titulo)
    }

    @Test
    fun promedioProgreso_deberiaCalcularCorrectamente() {
        val actividades = listOf(
            ActividadFormativa(
                id = 9,
                titulo = "Kotlin",
                description = "Aprender",
                progreso = 20,
                diasRestantes = 5,
                prioridad = Prioridad.Media
            ),
            ActividadFormativa(
                id = 10,
                titulo = "Android",
                description = "Practicar",
                progreso = 60,
                diasRestantes = 5,
                prioridad = Prioridad.Media
            ),
            ActividadFormativa(
                id = 11,
                titulo = "Git",
                description = "Practicar",
                progreso = 100,
                diasRestantes = 5,
                prioridad = Prioridad.Baja
            )
        )

        assertEquals(60, promedioProgreso(actividades))
    }

    @Test
    fun buscarPorTitulo_deberiaIgnorarMayusculasYEspacios() {
        val actividades = listOf(
            ActividadFormativa(
                id = 12,
                titulo = "Kotlin básico",
                description = "Aprender Kotlin",
                progreso = 50,
                diasRestantes = 5,
                prioridad = Prioridad.Media
            ),
            ActividadFormativa(
                id = 13,
                titulo = "Android Studio",
                description = "Practicar Android",
                progreso = 70,
                diasRestantes = 5,
                prioridad = Prioridad.Media
            )
        )

        val resultado = buscarPorTitulo(actividades, " kotlin ")

        assertEquals(1, resultado.size)
        assertEquals("Kotlin básico", resultado[0].titulo)
    }
}