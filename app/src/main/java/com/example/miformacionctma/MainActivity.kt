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
import com.example.miformacionctma.domain.*

val actividadesEjemplo = listOf(
    ActividadFormativa(
        id = 1,
        titulo = "Kotlin básico",
        description = "Aprender fundamentos de Kotlin",
        progreso = 60,
        diasRestantes = 2,
        prioridad = Prioridad.Alta
    ),
    ActividadFormativa(
        id = 2,
        titulo = "Android Studio",
        description = "Practicar Compose",
        progreso = 100,
        diasRestantes = -1,
        prioridad = Prioridad.Media
    ),
    ActividadFormativa(
        id = 3,
        titulo = "Git y GitHub",
        description = "Practicar ramas y commits",
        progreso = 30,
        diasRestantes = 5,
        prioridad = Prioridad.Baja
    )
)

val promedio = promedioProgreso(actividadesEjemplo)
val urgentes = actividadesUrgentes(actividadesEjemplo)

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
                        promedio = promedio,
                        urgentes = urgentes.size,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(
    promedio: Int,
    urgentes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Mi Formación CTMA",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Resumen de formación",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Progreso promedio: $promedio%"
        )

        Text(
            text = "Actividades urgentes: $urgentes"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiFormacionCTMATheme {
        PantallaInicio(
            promedio = 63,
            urgentes = 1
        )
    }
}