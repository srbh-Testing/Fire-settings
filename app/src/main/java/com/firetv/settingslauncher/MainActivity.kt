package com.firetv.settingslauncher

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : Activity() {

    companion object {
        private const val TAG = "FireTVSettingsLauncher"

        private val PRIMARY = ComponentName(
            "com.amazon.tv.launcher",
            "com.amazon.tv.launcher.ui.MainSettingsActivity"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchSettings()
        finish()
    }

    private fun launchSettings() {
        try {
            val intent = Intent("amazon.intent.action.MAINSETTINGS").apply {
                component = PRIMARY
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            startActivity(intent)
            return
        } catch (e: Exception) {
            Log.w(TAG, "Explicit MainSettingsActivity launch failed", e)
        }

        try {
            val fallback = Intent(android.provider.Settings.ACTION_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(fallback)
            return
        } catch (e: Exception) {
            Log.e(TAG, "Fallback settings launch failed", e)
        }

        Toast.makeText(this, "Could not open Settings", Toast.LENGTH_LONG).show()
    }
}
