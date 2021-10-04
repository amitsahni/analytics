package com.sample.analytics

import android.util.Log
import com.analytics.*

class FirebaseLogger : AnalyticsLogger {

    override fun logEvent(event: Event) {
        val map = mutableMapOf<String, String>()
        map.putAll(configureEventName(event.eventInfo))
        map.putAll(configureData(event.list))
        if(event.eventInfo.supportedAnalytics.contains(AnalyticsSupported.Firebase) && AnalyticsSupported.Firebase.enabled) {
            Log.i("FirebaseLogger", map.toString())
        }
    }

    override fun configureData(eventPayLoadList: List<EventPayLoad>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        eventPayLoadList.map { eventPayLoad ->
            when (eventPayLoad) {
                is ScreenInfo -> {
                    map["page_name"] = eventPayLoad.pageName
                    map["page_type"] = eventPayLoad.pageType
                }
                is VersionName -> map["version_name"] = eventPayLoad.id
                is VersionCode -> map["version_code"] = eventPayLoad.id
                is BuildNumber -> map["build_number"] = eventPayLoad.id
            }
        }
        return map
    }

    override fun configureEventName(eventInfo: EventInfo) = mutableMapOf<String, String>().apply {
        val eventName = when (eventInfo) {
            Action.ButtonClicked -> "button"
            else -> eventInfo.eventName
        }
        put("event_name", eventName)
        put("event_type", eventInfo.type.type)
    }
}