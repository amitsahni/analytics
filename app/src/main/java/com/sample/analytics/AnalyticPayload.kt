package com.sample.analytics

import com.analytics.*

// ScreenName
enum class ScreenName(override val pageName: String, override val pageType: String) : ScreenInfo {
    Home("home", "home")
}

// EventName
enum class Action(
    override val eventName: String, override val type: EventType = EventType.Action,
    override val supportedAnalytics: List<SupportedAnalytics>
) : EventInfo {
    ButtonClicked(eventName = "button_clicked", supportedAnalytics = ALL)
}

// Analytics Supported
val ALL = listOf(AnalyticsSupported.Firebase, AnalyticsSupported.WebEngage)

enum class AnalyticsSupported(override val enabled: Boolean) : SupportedAnalytics {
    Firebase(false),
    WebEngage(true)
}

// Payload
data class VersionName(val id: String) : EventPayLoad
data class VersionCode(val id: String) : EventPayLoad
data class BuildNumber(val id: String) : EventPayLoad