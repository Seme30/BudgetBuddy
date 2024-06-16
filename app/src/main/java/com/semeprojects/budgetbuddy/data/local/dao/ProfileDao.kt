package com.semeprojects.budgetbuddy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semeprojects.budgetbuddy.data.local.model.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profiles WHERE profileId = :profileId")
    suspend fun getProfile(profileId: Int): Profile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)
}