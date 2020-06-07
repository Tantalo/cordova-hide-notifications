package it.tantalo.cat.hidenotifications;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class HideNotificationsPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equalsIgnoreCase("enable")) {
            if (!checkNotificationListenerPermission()) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                this.cordova.getActivity().startActivity(intent);
            } else {
                HideNotificationService.setIsEnabled(true);
            }
        }

        if (action.equalsIgnoreCase("disable")) {
            HideNotificationService.setIsEnabled(false);
        }

        String msg = HideNotificationService.isEnabled() + "";

        if (action.equalsIgnoreCase("getStatus")) {
            msg = HideNotificationService.getStatus();
        }

        PluginResult result = new PluginResult(PluginResult.Status.OK, msg);
        result.setKeepCallback(true);
        callbackContext.sendPluginResult(result);

        return true;
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private boolean checkNotificationListenerPermission() {
        ComponentName cn = new ComponentName(this.cordova.getContext(), HideNotificationService.class);
        String flat = Settings.Secure.getString(this.cordova.getActivity().getContentResolver(), "enabled_notification_listeners");
        final boolean enabled = flat != null && flat.contains(cn.flattenToString());
        return enabled;
    }

}
