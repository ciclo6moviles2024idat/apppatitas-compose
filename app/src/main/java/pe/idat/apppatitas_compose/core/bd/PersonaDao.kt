package pe.idat.apppatitas_compose.core.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPersona(vararg personaEntity: PersonaEntity)

    @Update
    suspend fun actualizarPersona(vararg personaEntity: PersonaEntity)

    @Query("DELETE FROM persona")
    suspend fun eliminarPersona()

    @Query("SELECT * FROM persona LIMIT 1")
    fun obtenerPersona(): LiveData<PersonaEntity>

}