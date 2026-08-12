package com.example.miformacionctma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.domain.actividadesUrgentes
import com.example.miformacionctma.domain.promedioProgreso
import com.example.miformacionctma.ui.screens.PantallaActividades
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MiFormacionCTMATheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    PantallaInicio(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(
    modifier: Modifier = Modifier
) {

    val actividades = listOf(
        ActividadFormativa(
            id = 1,
            titulo = "Aprender Kotlin",
            descripcion = "Variables y funciones",
            progreso = 80,
            diasRestantes = 2,
            prioridad = Prioridad.ALTA
        ),
        ActividadFormativa(
            id = 2,
            titulo = "Jetpack Compose",
            descripcion = "Crear interfaces declarativas",
            progreso = 100,
            diasRestantes = -2,
            prioridad = Prioridad.MEDIA
        ),
        ActividadFormativa(
            id = 3,
            titulo = "Git y GitHub",
            descripcion = "Control de versiones",
            progreso = 40,
            diasRestantes = 1,
            prioridad = Prioridad.ALTA
        ),
        ActividadFormativa(
            id = 4,
            titulo = "Diseño de interfaces",
            descripcion = "Crear una interfaz clara y accesible",
            progreso = 0,
            diasRestantes = 5,
            prioridad = Prioridad.MEDIA
        ),
        ActividadFormativa(
            id = 5,
            titulo = "Material 3",
            descripcion = "Aplicar componentes de Material Design",
            progreso = 30,
            diasRestantes = 4,
            prioridad = Prioridad.MEDIA
        ),
        ActividadFormativa(
            id = 6,
            titulo = "Accesibilidad",
            descripcion = "Revisar contraste y tamaño de texto",
            progreso = 60,
            diasRestantes = 2,
            prioridad = Prioridad.ALTA
        ),
        ActividadFormativa(
            id = 7,
            titulo = "LazyColumn",
            descripcion = "Construir listas eficientes en Compose",
            progreso = 20,
            diasRestantes = 3,
            prioridad = Prioridad.MEDIA
        ),
        ActividadFormativa(
            id = 8,
            titulo = "Preview de Compose",
            descripcion = "Probar diferentes configuraciones",
            progreso = 50,
            diasRestantes = 6,
            prioridad = Prioridad.BAJA
        ),
        ActividadFormativa(
            id = 9,
            titulo = "Prueba en dispositivo",
            descripcion = "Ejecutar la aplicación en el teléfono",
            progreso = 0,
            diasRestantes = 1,
            prioridad = Prioridad.ALTA
        ),
        ActividadFormativa(
            id = 10,
            titulo = "Documentación",
            descripcion = "Registrar decisiones y resultados",
            progreso = 100,
            diasRestantes = 0,
            prioridad = Prioridad.BAJA
        )
    )

    PantallaActividades(
        actividades = actividades,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiFormacionCTMATheme {
        PantallaInicio()
    }
}