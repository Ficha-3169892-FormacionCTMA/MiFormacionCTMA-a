package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleActividad(
    actividadId: Long,
    actividades: List<ActividadFormativa>,
    onBackClick: () -> Unit
) {
    val actividad = actividades.find { it.id == actividadId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Actividad") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        if (actividad == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Actividad no encontrada")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onBackClick) {
                        Text("Regresar")
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = actividad.titulo,
                    style = MaterialTheme.typography.headlineMedium
                )
                
                DetailItem(label = "Descripción", value = actividad.descripcion ?: "Sin descripción")
                DetailItem(label = "Fecha", value = actividad.fecha)
                DetailItem(label = "Prioridad", value = actividad.prioridad.name)
                
                Text(
                    text = "Progreso: ${actividad.progreso}%",
                    style = MaterialTheme.typography.bodyLarge
                )
                LinearProgressIndicator(
                    progress = { actividad.progreso / 100f },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
