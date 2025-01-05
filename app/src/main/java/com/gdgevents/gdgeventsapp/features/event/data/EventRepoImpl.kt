package com.gdgevents.gdgeventsapp.features.event.data

import com.gdgevents.gdgeventsapp.features.event.domain.DummyEventList
import com.gdgevents.gdgeventsapp.features.event.domain.DummyFeaturedEventList
import com.gdgevents.gdgeventsapp.features.event.domain.EventRepo
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class EventRepoImpl @Inject constructor() : EventRepo {

    override fun getEvents() = flowOf(DummyEventList)
    override fun getFeaturedEvents() = flowOf(DummyFeaturedEventList)
}