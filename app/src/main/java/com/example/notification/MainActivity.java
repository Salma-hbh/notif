package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.messaging.RemoteMessage;
import com.microsoft.windowsazure.messaging.notificationhubs.NotificationHub;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationHub.setListener(new CustomNotificationListener());
        NotificationHub.start(this.getApplication(), "notification", "Endpoint=sb://Testnot.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=rvc9nsNXMXEudhfqhuMnfV/F0W1xYaqPV2KKwxMt3YA=");
    }
}