package nz.co.redice.myapplication.service;

public class Common {

    static final String PACKAGE_NAME = "nz.co.redice.myapplication.service";
    public static final String EXTRA_LOCATION = PACKAGE_NAME + ".location";
    public static final String ACTION_BROADCAST = PACKAGE_NAME + ".broadcast";
    static final String EXTRA_STARTED_FROM_NOTIFICATION = PACKAGE_NAME + ".started_from_notification";
    // The name of the channel for notifications.
    static final String CHANNEL_ID = "channel_01";

    // The identifier for the notification displayed for the foreground service.
    static final int NOTIFICATION_ID = 12345678;
}
