package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CrearReporteRoute(
    viewModel: CrearReporteViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    CrearReporteContent(
        state = uiState,
        onTituloChange = viewModel::actualizarTitulo,
        onGuardar = viewModel::guardar
    )
}

@Composable
fun CrearReporteContent(
    state: CrearUiState,
    onTituloChange: (String) -> Unit,
    onGuardar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = state.titulo,
            onValueChange = onTituloChange,
            label = { Text("Título del reporte") },
            isError = state.errorTitulo != null,
            supportingText = { state.errorTitulo?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.guardando
        )
        
        Button(
            onClick = onGuardar,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.guardando
        ) {
            if (state.guardando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Guardar")
            }
        }
        
        state.guardadoId?.let {
            Text("Guardado con éxito. ID: $it", color = MaterialTheme.colorScheme.primary)
        }
    }
}
