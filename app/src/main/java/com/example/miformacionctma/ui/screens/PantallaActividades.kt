package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.ui.components.ResumenActividades
import com.example.miformacionctma.ui.components.TarjetaActividad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaActividades(
    actividades: List<ActividadFormativa>,
    onActividadClick: (Long) -> Unit,
    onAddClick: () -> Unit
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
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar actividad")
            }
        }
    ) { paddingValues ->

        if (actividades.isEmpty()) {

            EstadoVacio(
                paddingValues = paddingValues,
                onAddClick = onAddClick
            )

        } else {
// ... (rest of the BoxWithConstraints logic)

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                // Pantalla pequeña
                if (maxWidth < 600.dp) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),

                        contentPadding = PaddingValues(
                            vertical = 16.dp
                        ),

                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        // Resumen
                        item {
                            ResumenActividades(
                                actividades = actividades
                            )
                        }

                        // Actividades
                        items(
                            items = actividades,
                            key = { actividad -> actividad.id }
                        ) { actividad ->

                            TarjetaActividad(
                                actividad = actividad,
                                onClick = {
                                    onActividadClick(actividad.id)
                                }
                            )
                        }
                    }

                } else {

                    // Pantalla ancha
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),

                        contentPadding = PaddingValues(
                            vertical = 16.dp
                        ),

                        horizontalArrangement = Arrangement.spacedBy(12.dp),

                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        // Resumen ocupa las dos columnas
                        item(
                            span = {
                                androidx.compose.foundation.lazy.grid.GridItemSpan(
                                    maxLineSpan
                                )
                            }
                        ) {

                            ResumenActividades(
                                actividades = actividades
                            )
                        }

                        // Actividades en dos columnas
                        items(
                            items = actividades,
                            key = { actividad -> actividad.id }
                        ) { actividad ->

                            TarjetaActividad(
                                actividad = actividad,
                                onClick = {
                                    onActividadClick(actividad.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EstadoVacio(
    paddingValues: PaddingValues,
    onAddClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "No hay actividades"
            )

            Text(
                text = "Aún no tienes actividades registradas."
            )

            Button(
                onClick = onAddClick
            ) {
                Text("Agregar actividad")
            }
        }
    }
}
