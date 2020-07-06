package com.example.bonvoyaide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.kommunicate.KmConversationBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KmCallback;

public class Chatbot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);


        List<String> agentList = new ArrayList();
        agentList.add("darshanpatil761@gmail.com"); //add your agentID
        List<String> botList = new ArrayList();
        botList.add("bon-voyaide-zutb4"); //enter your integrated bot Ids

        Kommunicate.init(Chatbot.this, "1f4c22141d7d521295a1e370198a89638");
        Kommunicate.openConversation(Chatbot.this);



        new KmConversationBuilder(Chatbot.this)
                .setAgentIds(agentList)
                .setBotIds(botList)
                .createConversation(new KmCallback() {
                    @Override
                    public void onSuccess(Object message) {
                        Log.d("ChatLaunch", "Success : " + message);

                    }

                    @Override
                    public void onFailure(Object error) {
                        Log.d( "ChatLaunch", "Failure : " + error);

                    }
                });

//        Kommunicate.launchSingleChat(Chatbot.this, "Support", Kommunicate.getVisitor(), false, true, agentList, botList, new KmCallback(){
//            @Override
//            public void onSuccess(Object message) {
//                Log.d("ChatLaunch", "Success : " + message);
//            }
//            @Override
//            public void onFailure(Object error) {
//                Log.d( "ChatLaunch", "Failure : " + error);
//            }
//        });

    }
}
