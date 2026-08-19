package com.example.miformacionctma.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.ui.screens.FormularioActividadUiState

@Composable
fun FormularioActividad(
    state: FormularioActividadUiState,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onFechaChange: (String) -> Unit,
    onPrioridadChange: (Prioridad) -> Unit,
    onProgresoChange: (String) -> Unit,
    onGuardarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = state.titulo,
            onValueChange = onTituloChange,
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.errores.containsKey("titulo"),
            supportingText = {
                state.errores["titulo"]?.let { Text(it) }
            }
        )

        OutlinedTextField(
            value = state.descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripción (opcional)") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.errores.containsKey("descripcion"),
            supportingText = {
                state.errores["descripcion"]?.let { Text(it) }
            }
        )

        OutlinedTextField(
            value = state.fecha,
            onValueChange = onFechaChange,
            label = { Text("Fecha (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: 2026-08-19") },
            isError = state.errores.containsKey("fecha"),
            supportingText = {
                state.errores["fecha"]?.let { Text(it) }
            }
        )

        Text("Prioridad", style = MaterialTheme.typography.labelLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Prioridad.values().forEach { prioridad ->
                FilterChip(
                    selected = state.prioridad == prioridad,
                    onClick = { onPrioridadChange(prioridad) },
                    label = { Text(prioridad.name) }
                )
            }
        }

        OutlinedTextField(
            value = state.progreso,
            onValueChange = onProgresoChange,
            label = { Text("Progreso (%)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = state.errores.containsKey("progreso"),
            supportingText = {
                state.errores["progreso"]?.let { Text(it) }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onGuardarClick,
            enabled = state.puedeGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Actividad")
        }
    }
}
