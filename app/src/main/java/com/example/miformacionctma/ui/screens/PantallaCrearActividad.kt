package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.domain.validarActividad
import com.example.miformacionctma.ui.components.FormularioActividad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearActividad(
    onActividadGuardada: (ActividadFormativa) -> Unit,
    onBackClick: () -> Unit
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var fecha by rememberSaveable { mutableStateOf("") }
    var prioridad by rememberSaveable { mutableStateOf(Prioridad.MEDIA) }
    var progreso by rememberSaveable { mutableStateOf("0") }

    // Estado para evitar doble pulsación
    var guardando by remember { mutableStateOf(false) }

    val uiState = remember(titulo, descripcion, fecha, prioridad, progreso) {
        val erroresList = validarActividad(
            ActividadFormativa(
                id = 0,
                titulo = titulo,
                descripcion = descripcion,
                progreso = progreso.toIntOrNull() ?: -1,
                fecha = fecha,
                diasRestantes = 0,
                prioridad = prioridad
            )
        )

        val erroresMap = mutableMapOf<String, String>()
        erroresList.forEach { error ->
            when {
                error.contains("título", ignoreCase = true) -> erroresMap["titulo"] = error
                error.contains("descripción", ignoreCase = true) -> erroresMap["descripcion"] = error
                error.contains("fecha", ignoreCase = true) -> erroresMap["fecha"] = error
                error.contains("progreso", ignoreCase = true) -> erroresMap["progreso"] = error
            }
        }

        FormularioActividadUiState(
            titulo = titulo,
            descripcion = descripcion,
            fecha = fecha,
            prioridad = prioridad,
            progreso = progreso,
            errores = erroresMap,
            puedeGuardar = erroresMap.isEmpty() && !guardando
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Actividad") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        FormularioActividad(
            state = uiState,
            onTituloChange = { titulo = it },
            onDescripcionChange = { descripcion = it },
            onFechaChange = { fecha = it },
            onPrioridadChange = { prioridad = it },
            onProgresoChange = { progreso = it },
            onGuardarClick = {
                if (uiState.puedeGuardar) {
                    guardando = true
                    onActividadGuardada(
                        ActividadFormativa(
                            id = System.currentTimeMillis(),
                            titulo = titulo,
                            descripcion = descripcion.ifBlank { null },
                            progreso = progreso.toIntOrNull() ?: 0,
                            fecha = fecha,
                            diasRestantes = 0, // Se podría calcular pero no es requisito para el guardado
                            prioridad = prioridad
                        )
                    )
                }
            },
            modifier = Modifier.padding(padding)
        )
    }
}
