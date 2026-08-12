package com.example.miformacionctma.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.estadoActividad

@Composable
fun TarjetaActividad(
    actividad: ActividadFormativa,
    onActividadClick: (ActividadFormativa) -> Unit = {}
) {
    val estado = estadoActividad(actividad)

    Card(
        onClick = { onActividadClick(actividad) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = actividad.titulo,
                style = MaterialTheme.typography.titleMedium
            )

            actividad.descripcion?.let { descripcion ->
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Estado: $estado"
                )

                Text(
                    text = "Prioridad: ${actividad.prioridad}"
                )
            }

            Text(
                text = "Progreso: ${actividad.progreso}%"
            )

            LinearProgressIndicator(
                progress = { actividad.progreso.coerceIn(0, 100) / 100f },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = if (actividad.diasRestantes >= 0) {
                    "Días restantes: ${actividad.diasRestantes}"
                } else {
                    "Actividad vencida"
                }
            )
        }
    }
}