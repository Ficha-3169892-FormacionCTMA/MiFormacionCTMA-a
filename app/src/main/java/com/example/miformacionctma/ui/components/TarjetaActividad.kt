package com.example.miformacionctma.ui.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.estadoActividad

@Composable
fun TarjetaActividad(
    actividad: ActividadFormativa,
    onClick: () -> Unit
) {
    val estado = estadoActividad(actividad)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                role = Role.Button
                stateDescription = "Estado: $estado"
            }
            .clickable {
                onClick()
            }
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
                    text = "Estado: $estado",
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = "Prioridad: ${actividad.prioridad}",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Text(
                text = if (actividad.diasRestantes >= 0) {
                    "Días restantes: ${actividad.diasRestantes}"
                } else {
                    "Actividad vencida"
                }
            )

            Text(
                text = "Progreso: ${actividad.progreso}%"
            )

            LinearProgressIndicator(
                progress = {
                    actividad.progreso.coerceIn(0, 100) / 100f
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true
)
@Composable
private fun TarjetaActividadPreview() {
    TarjetaActividad(
        actividad = ActividadFormativa(
            id = 999L,
            titulo = "Esta es una actividad con un título bastante largo para comprobar que no se recorta",
            descripcion = "Actividad utilizada para probar el diseño de la tarjeta.",
            progreso = 100,
            diasRestantes = 0,
            prioridad = com.example.miformacionctma.domain.Prioridad.ALTA
        ),
        onClick = {}
    )
}