package com.example.visualchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText userIdEditText;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdEditText=findViewById(R.id.user_id_edit_text);
        startBtn=findViewById(R.id.start_btn);

        startBtn.setOnClickListener((v)->{
            String userId= userIdEditText.getText().toString().trim();
            if(userId.isEmpty()){
                return;
            }
            //start the service
            startService(userId);
        });

    }
    void startService(String userID){
        Application application = getApplication(); // Android's application context
        long appID = 1336299220;   // yourAppID
        String appSign ="492302b35a6a2ba48af2c30da02eb605fff90879952bcac760f9ce22fc0e5a6a";  // yourAppSign
         // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName =userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}