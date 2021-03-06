
package com.example.bundauapp.common.ktx

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

fun Activity.fullScreen() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            // Set the content to appear under the system bars so that the
            // content doesn't resize when the system bars hide and show.
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
}

fun Activity.lightTheme() {
    setLightStatusBar(window)
    setLightNavigationBar(window)
}

fun setLightStatusBar(window: Window) {
    if (Build.VERSION.SDK_INT < 23) return
    setSystemUiFlags(
        window,
        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    )
}

fun setLightNavigationBar(window: Window) {
    if (Build.VERSION.SDK_INT < 27) return
    setSystemUiFlags(
        window,
        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    )
}

fun Activity.transparentStatusNavigationBar(isLightTheme: Boolean = true) {
    transparentStatusNavigationBar(
        isLightThemeStatusBar = isLightTheme,
        isLightThemeNavigationBar = isLightTheme
    )
}

fun Activity.transparentStatusNavigationBar(
    isLightThemeStatusBar: Boolean = true,
    isLightThemeNavigationBar: Boolean = true
) {
    kotlin.runCatching {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                setSystemUiFlags(this, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                if (isLightThemeStatusBar) {
                    setLightStatusBar(window)
                }
                if (isLightThemeNavigationBar) {
                    setLightNavigationBar(window)
                    navigationBarColor = Color.WHITE
                }
                statusBarColor = Color.TRANSPARENT
            }
        }
    }
}

fun setSystemUiFlags(window: Window, flags: Int) {
    val view = window.decorView
    var uiFlags = view.systemUiVisibility
    uiFlags = uiFlags or flags
    view.systemUiVisibility = uiFlags
}