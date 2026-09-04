package com.example.miformacionctma.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa

@Composable
fun ResumenActividades(
    actividades: List<ActividadFormativa>
) {

    val completadas = actividades.count {
        it.progreso == 100
    }

    val enProceso = actividades.count {
        it.progreso > 0 && it.progreso < 100
    }

    val pendientes = actividades.count {
        it.progreso == 0
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Resumen",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text("Total: ${actividades.size}")

                Text("Completadas: $completadas")

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text("En proceso: $enProceso")

                Text("Pendientes: $pendientes")

            }
        }
    }
}

