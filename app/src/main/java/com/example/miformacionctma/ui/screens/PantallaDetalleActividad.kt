package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleActividad(
    actividadId: Long,
    actividades: List<ActividadFormativa>,
    onBack: () -> Unit
) {
    val actividad = actividades.find { it.id == actividadId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Actividad") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (actividad != null) {
            DetalleActividad(
                actividad = actividad,
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text("La actividad con ID $actividadId no existe.")
            }
        }
    }
}

@Composable
fun DetalleActividad(
    actividad: ActividadFormativa,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = actividad.titulo,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Prioridad: ${actividad.prioridad}",
            style = MaterialTheme.typography.titleMedium
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        HorizontalDivider()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Descripción",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = actividad.descripcion ?: "Sin descripción",
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Fecha límite: ${actividad.fecha}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Días restantes: ${actividad.diasRestantes}",
            style = MaterialTheme.typography.bodyMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Progreso: ${actividad.progreso}%",
            style = MaterialTheme.typography.titleMedium
        )
        LinearProgressIndicator(
            progress = { actividad.progreso / 100f },
            modifier = Modifier.fillMaxSize(),
        )
    }
}
