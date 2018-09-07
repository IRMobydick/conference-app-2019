package io.github.droidkaigi.confsched2019.data.db.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class SessionDao {

    @Query("SELECT * FROM session")
    abstract fun sessionsLiveData(): LiveData<List<SessionEntity>>

    @Query("SELECT * FROM session")
    abstract fun sessions(): List<SessionEntity>

    @Query("DELETE FROM session")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<SessionEntity>)

    @Transaction
    open fun clearAndInsert(newSessions: List<SessionEntity>) {
        deleteAll()
        insert(newSessions)
    }
}