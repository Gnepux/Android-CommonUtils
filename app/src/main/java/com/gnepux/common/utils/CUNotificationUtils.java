package com.gnepux.common.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Notification辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class CUNotificationUtils {

    /**
     * 发送Notification，requestCode默认为0
     *
     * @param context
     * @param tickerText
     * @param iconId
     * @param title
     * @param content
     * @param intent 不能为null
     */
    public static void sendNoticifaction(Context context, String tickerText, int iconId, String title, String content, Intent intent) {

        sendNoticifaction(context, tickerText, iconId, title, content, intent, 0);

    }

    /**
     * 发送Notification，需传requestCode
     *
     * @param context
     * @param tickerText
     * @param iconId
     * @param title
     * @param content
     * @param intent 不能为null
     * @param reuqstCode
     */
    public static void sendNoticifaction(Context context, String tickerText, int iconId, String title, String content, Intent intent, int reuqstCode) {

        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0 /*PendingIntent.FLAG_ONE_SHOT*/ /* flag */);

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(context)
                    .setSmallIcon(iconId)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)
                    //.setLargeIcon()
                    //.setStyle(new Notification.BigTextStyle().bigText(title)) //设置style，4.1及以上上才支持
                    //.setStyle(new Notification.BigPictureStyle().bigPicture().bigLargeIcon()) //设置style，4.0及以上上才支持
                    .setContentIntent(pIntent).build();

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            notification = new Notification.Builder(context)
                    .setSmallIcon(iconId)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)
                    //.setLargeIcon()
                    .setContentIntent(pIntent).getNotification();
        } else {
            notification = new Notification(iconId, tickerText, System.currentTimeMillis());
            notification.setLatestEventInfo(context, title, content, pIntent);
        }

        // Remove the notification on click
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        manager.notify(reuqstCode, notification);

    }

    /**
     * 清除当前context下的所有通知
     *
     * @param context
     */
    public static void cancelAll(Context context) {

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        manager.cancelAll();
    }

    /**
     * 清除当前context下号为id的通知
     *
     * @param context
     * @param id
     */
    public static void cancel(Context context, int id) {

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        manager.cancel(id);
    }

}
