package com.semeprojects.budgetbuddy.data.local.repository

import com.semeprojects.budgetbuddy.data.local.dao.ProfileDao
import com.semeprojects.budgetbuddy.data.local.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepository(private val profileDao: ProfileDao) {
    suspend fun insertProfile(profile: Profile){
        profileDao.insertProfile(profile)
    }

    suspend fun getProfile(profileId: Int): Profile {
        return profileDao.getProfile(profileId)
    }

    suspend fun deleteProfile(profile: Profile) {
        profileDao.deleteProfile(profile)
    }
}
