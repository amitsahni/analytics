package com.analytics

enum class EventType(val type: String) {
    Action("action"), State("state"), Impression("impression")
}

data class Event(val eventInfo: EventInfo, val list: List<EventPayLoad> = emptyList())

interface EventPayLoad
interface ScreenInfo : EventPayLoad {
    val pageName: String
    val pageType: String
}

interface EventInfo : EventPayLoad {
    val eventName: String
    val type: EventType
    val supportedAnalytics : List<SupportedAnalytics>
}
interface SupportedAnalytics : EventPayLoad {
    val enabled : Boolean
}