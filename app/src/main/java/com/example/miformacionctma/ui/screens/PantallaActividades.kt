package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.ui.components.ResumenActividades
import com.example.miformacionctma.ui.components.TarjetaActividad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaActividades(
    actividades: List<ActividadFormativa>,
    busqueda: String,
    prioridadSeleccionada: Prioridad?,
    onBusquedaChange: (String) -> Unit,
    onPrioridadChange: (Prioridad?) -> Unit,
    onActividadClick: (ActividadFormativa) -> Unit,
    onAgregarClick: () -> Unit,
    onCompletarActividad: (ActividadFormativa) -> Unit,
    onBorrarActividad: (ActividadFormativa) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mi Formación CTMA")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAgregarClick,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar actividad"
                )
            }
        }
    ) { paddingValues ->

        if (actividades.isEmpty()) {
            EstadoVacio(
                paddingValues = paddingValues,
                onAgregarClick = onAgregarClick
            )
        } else {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (maxWidth < 600.dp) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            SeccionFiltros(
                                busqueda = busqueda,
                                prioridadSeleccionada = prioridadSeleccionada,
                                onBusquedaChange = onBusquedaChange,
                                onPrioridadChange = onPrioridadChange
                            )
                        }
                        
                        item {
                            ResumenActividades(actividades = actividades)
                        }

                        items(
                            items = actividades,
                            key = { actividad -> actividad.id }
                        ) { actividad ->
                            TarjetaActividad(
                                actividad = actividad,
                                onClick = { onActividadClick(actividad) },
                                onCompletar = { onCompletarActividad(actividad) },
                                onBorrar = { onBorrarActividad(actividad) }
                            )
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                            SeccionFiltros(
                                busqueda = busqueda,
                                prioridadSeleccionada = prioridadSeleccionada,
                                onBusquedaChange = onBusquedaChange,
                                onPrioridadChange = onPrioridadChange
                            )
                        }

                        item(
                            span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }
                        ) {
                            ResumenActividades(actividades = actividades)
                        }

                        items(
                            items = actividades,
                            key = { actividad -> actividad.id }
                        ) { actividad ->
                            TarjetaActividad(
                                actividad = actividad,
                                onClick = { onActividadClick(actividad) },
                                onCompletar = { onCompletarActividad(actividad) },
                                onBorrar = { onBorrarActividad(actividad) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SeccionFiltros(
    busqueda: String,
    prioridadSeleccionada: Prioridad?,
    onBusquedaChange: (String) -> Unit,
    onPrioridadChange: (Prioridad?) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = busqueda,
            onValueChange = onBusquedaChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Buscar actividad...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Prioridad.entries.forEach { prioridad ->
                FilterChip(
                    selected = prioridadSeleccionada == prioridad,
                    onClick = { 
                        if (prioridadSeleccionada == prioridad) onPrioridadChange(null)
                        else onPrioridadChange(prioridad)
                    },
                    label = { Text(prioridad.name) }
                )
            }
        }
    }
}

@Composable
private fun EstadoVacio(
    paddingValues: PaddingValues,
    onAgregarClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Assignment,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            )

            Text(
                text = "No hay actividades",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Aún no tienes actividades registradas. Empieza agregando una nueva actividad formativa para realizar el seguimiento.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onAgregarClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Agregar actividad")
            }
        }
    }
}
