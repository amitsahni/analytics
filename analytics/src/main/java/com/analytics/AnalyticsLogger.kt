package com.analytics

interface AnalyticsLogger {
    fun logEvent(event: Event)
    fun configureData(eventPayLoadList: List<EventPayLoad>) : Map<String,String>
    fun configureEventName(eventInfo: EventInfo) : Map<String,String>
}