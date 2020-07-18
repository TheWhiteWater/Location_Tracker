package nz.co.redice.myapplication.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import nz.co.redice.myapplication.MainActivity;
import nz.co.redice.myapplication.R;

import static nz.co.redice.myapplication.service.Common.CHANNEL_ID;
import static nz.co.redice.myapplication.service.Common.EXTRA_STARTED_FROM_NOTIFICATION;
import static nz.co.redice.myapplication.service.Common.NOTIFICATION_ID;

class NotificationHelper {

    private Context mContext;
    NotificationManagerCompat mNotificationManager;


    public NotificationHelper(Context context) {
        mContext = context;
        mNotificationManager = NotificationManagerCompat.from(mContext);
        createChannel();
    }

    private void createChannel() {
        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.app_name);
            // Create the channel for the notification
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setSound(null, null);
            // Set the Notification Channel for the Notification Manager.
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }


    /**
     * Returns the {@link NotificationCompat} used as part of the foreground service.
     */
    Notification getNotification() {
        Intent intent = new Intent(mContext, LocationService.class);

        // Extra to help us figure out if we arrived in onStartCommand via the notification or not.
        intent.putExtra(EXTRA_STARTED_FROM_NOTIFICATION, true);

        // The PendingIntent that leads to a call to onStartCommand() in this service.
        PendingIntent servicePendingIntent = PendingIntent.getService(mContext, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // The PendingIntent to launch activity.
        PendingIntent activityPendingIntent = PendingIntent.getActivity(mContext, 0,
                new Intent(mContext, MainActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .addAction(R.drawable.ic_launch, mContext.getString(R.string.launch_activity),
                        activityPendingIntent)
                .addAction(R.drawable.ic_cancel, mContext.getString(R.string.cancel_location_updates),
                        servicePendingIntent)
                .setContentText("Context text")
                .setContentTitle(Utils.getLocationTitle(mContext))
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Ticker text")
                .setWhen(System.currentTimeMillis());

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID); // Channel ID
        }

        return builder.build();
    }

    void showNotification() {
        mNotificationManager.notify(NOTIFICATION_ID, getNotification());
    }


}
