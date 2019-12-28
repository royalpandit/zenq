package com.abhishek.zeiqindia.FCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.abhishek.zeiqindia.MainActivity;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.activity.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.Calendar;
import java.util.Map;

/**
 * Created by win10 on 21/DEC/2019.
 */

public class NotificationHandler extends FirebaseMessagingService {


    private static final String TAG = "Notification Tag";
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("NEW_TOKEN", s).apply();
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Map<String, String> data = remoteMessage.getData();
        sendNotification(data.get("title"), data.get("message"));

/*        CaseListFragment caseListFragment = CaseListFragment.getInstance( );
        if (caseListFragment != null){
            caseListFragment.setupList(false);
        }*/
    }

    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logozenq)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logozenq))
                .setContentTitle(title)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(messageBody))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationID = (int) ((Calendar.getInstance().getTime().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(notificationID, notificationBuilder.build());
    }

}
