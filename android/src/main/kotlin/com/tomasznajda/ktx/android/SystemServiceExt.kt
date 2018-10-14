package com.tomasznajda.ktx.android

import android.app.*
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.content.ClipboardManager
import android.content.Context
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.*
import android.os.storage.StorageManager
import android.support.annotation.RequiresApi
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

@Suppress("UNCHECKED_CAST")
fun <T> Context.getSystemService(name: String) = getSystemService(name) as? T

val Context.clipboardManager: ClipboardManager?
    get() = getSystemService<ClipboardManager>(Context.CLIPBOARD_SERVICE)

val Context.windowManager: WindowManager?
    get() = getSystemService<WindowManager>(Context.WINDOW_SERVICE)

val Context.layoutInflater: LayoutInflater?
    get() = getSystemService<LayoutInflater>(Context.LAYOUT_INFLATER_SERVICE)

val Context.activityManager: ActivityManager?
    get() = getSystemService<ActivityManager>(Context.ACTIVITY_SERVICE)

val Context.powerManager: PowerManager?
    get() = getSystemService<PowerManager>(Context.POWER_SERVICE)

val Context.alarmManager: AlarmManager?
    get() = getSystemService<AlarmManager>(Context.ALARM_SERVICE)

val Context.notificationManager: NotificationManager?
    get() = getSystemService<NotificationManager>(Context.NOTIFICATION_SERVICE)

val Context.keyguardManager: KeyguardManager?
    get() = getSystemService<KeyguardManager>(Context.KEYGUARD_SERVICE)

val Context.locationManager: LocationManager?
    get() = getSystemService<LocationManager>(Context.LOCATION_SERVICE)

val Context.searchManager: SearchManager?
    get() = getSystemService<SearchManager>(Context.SEARCH_SERVICE)

val Context.sensorManager: SensorManager?
    get() = getSystemService<SensorManager>(Context.SENSOR_SERVICE)

val Context.storageManager: StorageManager?
    get() = getSystemService<StorageManager>(Context.STORAGE_SERVICE)

val Context.vibrator: Vibrator?
    get() = getSystemService<Vibrator>(Context.VIBRATOR_SERVICE)

val Context.connectivityManager: ConnectivityManager?
    get() = getSystemService<ConnectivityManager>(Context.CONNECTIVITY_SERVICE)

val Context.wifiManager: WifiManager?
    get() = applicationContext.getSystemService<WifiManager>(Context.WIFI_SERVICE)

val Context.audioManager: AudioManager?
    get() = getSystemService<AudioManager>(Context.AUDIO_SERVICE)

val Context.mediaRouter: MediaRouter?
    get() = getSystemService<MediaRouter>(Context.MEDIA_ROUTER_SERVICE)

val Context.telephonyManager: TelephonyManager?
    get() = getSystemService<TelephonyManager>(Context.TELEPHONY_SERVICE)

val Context.subscriptionManager: SubscriptionManager?
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    get() = getSystemService<SubscriptionManager>(Context.TELEPHONY_SUBSCRIPTION_SERVICE)

val Context.carrierConfigManager: CarrierConfigManager?
    @RequiresApi(Build.VERSION_CODES.M)
    get() = getSystemService<CarrierConfigManager>(Context.CARRIER_CONFIG_SERVICE)

val Context.inputMethodManager: InputMethodManager?
    get() = getSystemService<InputMethodManager>(Context.INPUT_METHOD_SERVICE)

val Context.uiModeManager: UiModeManager?
    get() = getSystemService<UiModeManager>(Context.UI_MODE_SERVICE)

val Context.downloadManager: DownloadManager?
    get() = getSystemService<DownloadManager>(Context.DOWNLOAD_SERVICE)

val Context.batteryManager: BatteryManager?
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<BatteryManager>(Context.BATTERY_SERVICE)

val Context.jobScheduler: JobScheduler?
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<JobScheduler>(Context.JOB_SCHEDULER_SERVICE)