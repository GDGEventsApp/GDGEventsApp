package com.gdgevents.gdgeventsapp.core.work.domain

import kotlinx.coroutines.flow.Flow

interface SyncManager {
    val isSyncing: Flow<Boolean>
    fun requestSync()
}