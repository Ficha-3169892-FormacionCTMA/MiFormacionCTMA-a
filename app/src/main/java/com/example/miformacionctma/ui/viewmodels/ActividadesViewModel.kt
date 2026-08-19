package com.example.miformacionctma.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.miformacionctma.data.AppDatabase
import com.example.miformacionctma.domain.ActividadFormativa
import com.example.miformacionctma.domain.Prioridad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ActividadesViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val dao = database.actividadDao()

    // 1. Estados para los filtros (Privados para que solo el ViewModel los modifique)
    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda

    private val _prioridadFiltro = MutableStateFlow<Prioridad?>(null)
    val prioridadFiltro: StateFlow<Prioridad?> = _prioridadFiltro

    // 2. Combinamos la lista de la DB con los filtros locales
    val listaActividades: StateFlow<List<ActividadFormativa>> = combine(
        dao.obtenerTodas(),
        _busqueda,
        _prioridadFiltro
    ) { lista, texto, prioridad ->
        lista.filter { actividad ->
            val coincideTexto = actividad.titulo.contains(texto, ignoreCase = true)
            val coincidePrioridad = prioridad == null || actividad.prioridad == prioridad
            coincideTexto && coincidePrioridad
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Funciones para actualizar filtros
    fun actualizarBusqueda(nuevoTexto: String) {
        _busqueda.value = nuevoTexto
    }

    fun filtrarPorPrioridad(prioridad: Prioridad?) {
        _prioridadFiltro.value = prioridad
    }

    // Operaciones CRUD
    fun agregarActividad(actividad: ActividadFormativa) {
        viewModelScope.launch { dao.insertar(actividad) }
    }

    fun completarActividad(actividad: ActividadFormativa) {
        viewModelScope.launch { dao.actualizar(actividad.copy(progreso = 100)) }
    }

    fun borrarActividad(actividad: ActividadFormativa) {
        viewModelScope.launch { dao.eliminar(actividad) }
    }
}
