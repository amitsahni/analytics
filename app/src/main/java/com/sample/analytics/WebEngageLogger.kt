package com.sample.analytics

import android.util.Log
import com.analytics.*

class WebEngageLogger : AnalyticsLogger {

    override fun logEvent(event: Event) {
        val map = mutableMapOf<String, String>()
        map.putAll(configureEventName(event.eventInfo))
        map.putAll(configureData(event.list))
        if(event.eventInfo.supportedAnalytics.contains(AnalyticsSupported.WebEngage) && AnalyticsSupported.WebEngage.enabled) {
            Log.i("WebEngageLogger", map.toString())
        }
    }

    override fun configureData(eventPayLoadList: List<EventPayLoad>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        eventPayLoadList.map { eventPayLoad ->
            when (eventPayLoad) {
                is ScreenInfo -> {
                    map["p_n"] = eventPayLoad.pageName
                    map["p_t"] = eventPayLoad.pageType
                }
                is VersionName -> map["v_n"] = eventPayLoad.id
                is VersionCode -> map["v_c"] = eventPayLoad.id
                is BuildNumber -> map["b_n"] = eventPayLoad.id
            }
        }
        return map
    }

    override fun configureEventName(eventInfo: EventInfo) = mutableMapOf<String, String>().apply {
        val eventName = when (eventInfo) {
            //Action.ButtonClicked -> "splash_click"
            else -> eventInfo.eventName
        }
        put("e_n", eventName)
        put("e_t", eventInfo.type.type)
    }
}