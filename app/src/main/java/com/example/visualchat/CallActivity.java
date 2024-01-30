package com.example.visualchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIdEditText;
    TextView hetUserTextView;
    ZegoSendCallInvitationButton voiceCallBtn, videoCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIdEditText=findViewById(R.id.user_id_edit_text);
        hetUserTextView=findViewById(R.id.hey_user_text_view);
        voiceCallBtn=findViewById(R.id.voice_call_btn);
        videoCallBtn=findViewById(R.id.video_call_btn);

        voiceCallBtn.setVisibility(View.VISIBLE);
        videoCallBtn.setVisibility(View.VISIBLE);


        String userID=getIntent().getStringExtra("userID");
        hetUserTextView.setText("Hey "+userID);

        userIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String targetUserID=userIdEditText.getText().toString().trim();
                setVoiceCall(targetUserID);
                setVideoCall(targetUserID);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    void setVoiceCall(String targetUserID){
        voiceCallBtn.setIsVideoCall(false);
        voiceCallBtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
    void setVideoCall(String targetUserID){
        videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
}