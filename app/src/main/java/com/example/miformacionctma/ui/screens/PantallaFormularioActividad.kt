package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.domain.Prioridad
import com.example.miformacionctma.domain.validarDescripcion
import com.example.miformacionctma.domain.validarFecha
import com.example.miformacionctma.domain.validarProgreso
import com.example.miformacionctma.domain.validarTitulo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormularioActividad(
    onBack: () -> Unit,
    onGuardar: (String, String, String, Prioridad, Int) -> Unit
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var fecha by rememberSaveable { mutableStateOf("") }
    var prioridad by rememberSaveable { mutableStateOf(Prioridad.BAJA) }
    var progreso by rememberSaveable { mutableStateOf("0") }

    val errorTitulo = validarTitulo(titulo)
    val errorDescripcion = validarDescripcion(descripcion)
    val errorFecha = validarFecha(fecha)
    val errorProgreso = validarProgreso(progreso)

    val puedeGuardar = errorTitulo == null && 
                      errorDescripcion == null && 
                      errorFecha == null && 
                      errorProgreso == null

    val uiState = FormularioActividadUiState(
        titulo = titulo,
        descripcion = descripcion,
        fecha = fecha,
        prioridad = prioridad,
        progreso = progreso,
        errorTitulo = errorTitulo,
        errorDescripcion = errorDescripcion,
        errorFecha = errorFecha,
        errorProgreso = errorProgreso,
        puedeGuardar = puedeGuardar
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Actividad") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        FormularioActividad(
            uiState = uiState,
            onTituloChange = { titulo = it },
            onDescripcionChange = { descripcion = it },
            onFechaChange = { fecha = it },
            onPrioridadChange = { prioridad = it },
            onProgresoChange = { progreso = it },
            onGuardar = {
                onGuardar(titulo, descripcion, fecha, prioridad, progreso.toInt())
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun FormularioActividad(
    uiState: FormularioActividadUiState,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onFechaChange: (String) -> Unit,
    onPrioridadChange: (Prioridad) -> Unit,
    onProgresoChange: (String) -> Unit,
    onGuardar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var guardando by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = uiState.titulo,
            onValueChange = onTituloChange,
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errorTitulo != null,
            supportingText = uiState.errorTitulo?.let { { Text(it) } }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errorDescripcion != null,
            supportingText = uiState.errorDescripcion?.let { { Text(it) } }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.fecha,
            onValueChange = onFechaChange,
            label = { Text("Fecha (AAAA-MM-DD)") },
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errorFecha != null,
            supportingText = uiState.errorFecha?.let { { Text(it) } }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Prioridad", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Prioridad.entries.forEach { p ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = uiState.prioridad == p,
                        onClick = { onPrioridadChange(p) }
                    )
                    Text(p.name, modifier = Modifier.padding(end = 8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.progreso,
            onValueChange = onProgresoChange,
            label = { Text("Progreso (0-100)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = uiState.errorProgreso != null,
            supportingText = uiState.errorProgreso?.let { { Text(it) } }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!guardando) {
                    guardando = true
                    onGuardar()
                }
            },
            enabled = uiState.puedeGuardar && !guardando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Actividad")
        }
    }
}
