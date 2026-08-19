package com.example.miformacionctma.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.miformacionctma.domain.ActividadFormativa
import kotlinx.coroutines.flow.Flow

@Dao
interface ActividadDao {

    // Flow nos permite "escuchar" la base de datos en tiempo real
    @Query("SELECT * FROM actividades ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<ActividadFormativa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(actividad: ActividadFormativa)

    @Update
    suspend fun actualizar(actividad: ActividadFormativa)

    @Delete
    suspend fun eliminar(actividad: ActividadFormativa)
    
    @Query("SELECT * FROM actividades WHERE id = :id")
    suspend fun obtenerPorId(id: Long): ActividadFormativa?
}
