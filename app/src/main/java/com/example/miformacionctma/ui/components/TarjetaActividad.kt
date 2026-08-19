package com.example.miformacionctma.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.domain.estadoActividad

@Composable
fun TarjetaActividad(
    actividad: ActividadFormativa,
    onClick: () -> Unit,
    onCompletar: () -> Unit,
    onBorrar: () -> Unit
) {
    val estado = estadoActividad(actividad)

    // Determinamos el color basado en el estado para dar semántica visual
    val colorEstado = when (estado) {
        "COMPLETADA" -> MaterialTheme.colorScheme.primary
        "VENCIDA" -> MaterialTheme.colorScheme.error
        "EN PROCESO" -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.outline
    }

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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = actividad.titulo,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            actividad.descripcion?.let { descripcion ->
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = estado,
                    style = MaterialTheme.typography.labelLarge,
                    color = colorEstado
                )

                // Lógica visual para la prioridad
                val (iconPrioridad, colorPrioridad) = when (actividad.prioridad) {
                    Prioridad.ALTA -> Icons.Default.PriorityHigh to MaterialTheme.colorScheme.error
                    Prioridad.MEDIA -> Icons.Default.TrendingUp to Color(0xFFFFA500) // Naranja
                    Prioridad.BAJA -> Icons.Default.TrendingDown to MaterialTheme.colorScheme.outline
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = iconPrioridad,
                        contentDescription = null, // El texto ya lo describe
                        modifier = Modifier.size(16.dp),
                        tint = colorPrioridad
                    )
                    Text(
                        text = "Prioridad: ${actividad.prioridad}",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorPrioridad
                    )
                }
            }

            Text(
                text = if (actividad.diasRestantes >= 0) {
                    "Días restantes: ${actividad.diasRestantes}"
                } else {
                    "Actividad vencida"
                },
                style = MaterialTheme.typography.bodySmall,
                color = if (actividad.diasRestantes < 0) colorEstado else MaterialTheme.colorScheme.onSurface
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Progreso: ${actividad.progreso}%",
                    style = MaterialTheme.typography.labelSmall
                )
                LinearProgressIndicator(
                    progress = {
                        actividad.progreso.coerceIn(0, 100) / 100f
                    },
                    modifier = Modifier.fillMaxWidth(),
                    color = colorEstado,
                    trackColor = colorEstado.copy(alpha = 0.2f)
                )
            }

            if (actividad.progreso < 100) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onBorrar) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Borrar",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    IconButton(onClick = onCompletar) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Completar",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
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
            prioridad = Prioridad.ALTA
        ),
        onClick = {},
        onCompletar = {},
        onBorrar = {}
    )
}