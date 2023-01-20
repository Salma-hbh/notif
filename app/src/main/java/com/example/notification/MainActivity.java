package com.example.notification;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.microsoft.windowsazure.messaging.notificationhubs.NotificationHub;
import com.microsoft.windowsazure.messaging.notificationhubs.NotificationListener;

public class MainActivity extends AppCompatActivity {
    EditText etToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToken = findViewById(R.id.etToken);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG, token);
                        Toast.makeText(MainActivity.this,"Your token is" + token, Toast.LENGTH_SHORT).show();
                        etToken.setText(token);
                    }
                });
        NotificationHub.setListener(new CustomNotificationListener());
        NotificationHub.start(this.getApplication(), "notification", "Endpoint=sb://Testnot.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=rvc9nsNXMXEudhfqhuMnfV/F0W1xYaqPV2KKwxMt3YA=");
    }
}