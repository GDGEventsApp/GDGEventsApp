package com.gdgevents.gdgeventsapp.features.event.domain

import kotlinx.coroutines.flow.Flow

interface EventRepo {
    fun getEvents(): Flow<List<Event>>
    fun getFeaturedEvents(): Flow<List<Event>>
}