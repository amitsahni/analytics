package com.sample.analytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.analytics.Event

class AnalyticsActivity : AppCompatActivity() {

    private val firebaseLogger by lazy {
        FirebaseLogger()
    }

    private val webEngageLogger by lazy {
        WebEngageLogger()
    }

    private val homeScreen by lazy {
        ScreenName.Home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val event = Event(
            Action.ButtonClicked,
            listOf(
                homeScreen,
                VersionName("1.0.0"),
                VersionCode("3"),
                BuildNumber("1")
            )
        )
        firebaseLogger.logEvent(event)
        webEngageLogger.logEvent(event)
    }
}