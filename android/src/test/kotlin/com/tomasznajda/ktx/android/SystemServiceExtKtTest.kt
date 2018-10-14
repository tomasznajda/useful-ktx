package com.tomasznajda.ktx.android

import android.app.*
import android.app.job.JobScheduler
import android.content.ClipboardManager
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.PowerManager
import android.os.Vibrator
import android.os.storage.StorageManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.tomasznajda.ktx.junit.assertIsInstanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SystemServiceExtKtTest {

    val context = RuntimeEnvironment.application

    @Test
    fun `getClipboardManager provides clipboard service as clipboard manager`() {
        context.clipboardManager!!.assertIsInstanceOf(ClipboardManager::class)
    }

    @Test
    fun `getWindowManager provides window service as window manager`() {
        context.windowManager!!.assertIsInstanceOf(WindowManager::class)
    }

    @Test
    fun `getLayoutInflater provides l service as `() {
        context.layoutInflater!!.assertIsInstanceOf(LayoutInflater::class)
    }

    @Test
    fun `getActivityManager provides service as `() {
        context.activityManager!!.assertIsInstanceOf(ActivityManager::class)
    }

    @Test
    fun `getPowerManager provides service as `() {
        context.powerManager!!.assertIsInstanceOf(PowerManager::class)
    }

    @Test
    fun `getAlarmManager provides service as `() {
        context.alarmManager!!.assertIsInstanceOf(AlarmManager::class)
    }

    @Test
    fun `getNotificationManager provides service as `() {
        context.notificationManager!!.assertIsInstanceOf(NotificationManager::class)
    }

    @Test
    fun `getKeyguardManager provides service as `() {
        context.keyguardManager!!.assertIsInstanceOf(KeyguardManager::class)
    }

    @Test
    fun `getLocationManager provides service as `() {
        context.locationManager!!.assertIsInstanceOf(LocationManager::class)
    }

    @Test
    fun `getSearchManager provides service as `() {
        context.searchManager!!.assertIsInstanceOf(SearchManager::class)
    }

    @Test
    fun `getSensorManager provides service as `() {
        context.sensorManager!!.assertIsInstanceOf(SensorManager::class)
    }

    @Test
    fun `getStorageManager provides service as `() {
        context.storageManager!!.assertIsInstanceOf(StorageManager::class)
    }

    @Test
    fun `getVibrator provides service as `() {
        context.vibrator!!.assertIsInstanceOf(Vibrator::class)
    }

    @Test
    fun `getConnectivityManager provides service as `() {
        context.connectivityManager!!.assertIsInstanceOf(ConnectivityManager::class)
    }

    @Test
    fun `getWifiManager provides service as `() {
        context.wifiManager!!.assertIsInstanceOf(WifiManager::class)
    }

    @Test
    fun `getAudioManager provides service as `() {
        context.audioManager!!.assertIsInstanceOf(AudioManager::class)
    }

    @Test
    fun `getMediaRouter provides service as `() {
        context.mediaRouter!!.assertIsInstanceOf(MediaRouter::class)
    }

    @Test
    fun `getTelephonyManager provides service as `() {
        context.telephonyManager!!.assertIsInstanceOf(TelephonyManager::class)
    }

    @Test
    fun `getSubscriptionManager provides service as `() {
        context.subscriptionManager!!.assertIsInstanceOf(SubscriptionManager::class)
    }

    @Test
    fun `getCarrierConfigManager provides service as `() {
        context.carrierConfigManager!!.assertIsInstanceOf(CarrierConfigManager::class)
    }

    @Test
    fun `getInputMethodManager provides service as `() {
        context.inputMethodManager!!.assertIsInstanceOf(InputMethodManager::class)
    }

    @Test
    fun `getUiModeManager provides service as `() {
        context.uiModeManager!!.assertIsInstanceOf(UiModeManager::class)
    }

    @Test
    fun `getDownloadManager provides service as `() {
        context.downloadManager!!.assertIsInstanceOf(DownloadManager::class)
    }

    @Test
    fun `getBatteryManager provides service as `() {
        context.batteryManager!!.assertIsInstanceOf(BatteryManager::class)
    }

    @Test
    fun `getJobScheduler provides service as `() {
        context.jobScheduler!!.assertIsInstanceOf(JobScheduler::class)
    }
}