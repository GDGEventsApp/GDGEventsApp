package com.gdgevents.gdgeventsapp.core.work.data

import com.gdgevents.gdgeventsapp.core.work.domain.SyncManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SyncManagerImpl @Inject constructor() : SyncManager {

    // add implementation
    override val isSyncing: Flow<Boolean> = flowOf()

    override fun requestSync() {
        // add implementation
    }
}