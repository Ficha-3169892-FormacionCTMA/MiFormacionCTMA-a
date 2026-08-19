package com.example.miformacionctma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.ui.screens.PantallaActividades
import com.example.miformacionctma.ui.screens.PantallaDetalleActividad
import com.example.miformacionctma.ui.screens.PantallaFormularioActividad
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MiFormacionCTMATheme {
                val navController = rememberNavController()
                
                // Fuente de verdad compartida (en memoria para este incremento)
                val actividades = remember {
                    mutableStateListOf(
                        ActividadFormativa(
                            id = 1,
                            titulo = "Aprender Kotlin",
                            descripcion = "Variables y funciones",
                            progreso = 80,
                            fecha = "2026-08-21",
                            prioridad = Prioridad.ALTA
                        ),
                        ActividadFormativa(
                            id = 2,
                            titulo = "Jetpack Compose",
                            descripcion = "Crear interfaces declarativas",
                            progreso = 100,
                            fecha = "2026-08-15",
                            prioridad = Prioridad.MEDIA
                        ),
                        ActividadFormativa(
                            id = 3,
                            titulo = "Git y GitHub",
                            descripcion = "Control de versiones",
                            progreso = 40,
                            fecha = "2026-08-20",
                            prioridad = Prioridad.ALTA
                        )
                    )
                }

                NavHost(
                    navController = navController,
                    startDestination = "lista"
                ) {
                    composable("lista") {
                        PantallaActividades(
                            actividades = actividades,
                            onActividadClick = { id ->
                                navController.navigate("detalle/$id")
                            },
                            onAddClick = {
                                navController.navigate("crear")
                            }
                        )
                    }
                    
                    composable("crear") {
                        PantallaFormularioActividad(
                            onBack = { navController.popBackStack() },
                            onGuardar = { titulo, desc, fecha, prio, prog ->
                                val nueva = ActividadFormativa(
                                    id = System.currentTimeMillis(),
                                    titulo = titulo,
                                    descripcion = desc,
                                    progreso = prog,
                                    fecha = fecha,
                                    prioridad = prio
                                )
                                actividades.add(nueva)
                                navController.popBackStack()
                            }
                        )
                    }
                    
                    composable(
                        route = "detalle/{actividadId}",
                        arguments = listOf(navArgument("actividadId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getLong("actividadId") ?: -1L
                        PantallaDetalleActividad(
                            actividadId = id,
                            actividades = actividades,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiFormacionCTMATheme {
        Text("Mi Formación CTMA")
    }
}
