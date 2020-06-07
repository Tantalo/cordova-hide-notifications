package it.tantalo.cat.hidenotifications;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class HideNotificationService extends NotificationListenerService {

    private static boolean enabled = true;
    private static String status = "initial";

    public static void setIsEnabled(boolean enabled) {
        HideNotificationService.enabled = enabled;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String getStatus() {
        return status;
    }

    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        status = sbn.getId() + "";
        if (HideNotificationService.isEnabled())
            HideNotificationService.this.cancelAllNotifications();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}
