package com.example.laxtech.actors;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSubscriber implements MessageListener {

    //public static List<String> messageList = new ArrayList<String>();

    public void onMessage(final Message message, final byte[] pattern) {
        System.out.println("onMessage" );
        System.out.println("message" + message );
      //De-serialization of object
        ByteArrayInputStream bis = new   ByteArrayInputStream(message.getBody());
        Actor actor = null;
        try {
            ObjectInputStream in = new ObjectInputStream(bis);
            actor = (Actor) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //messageList.add(message.toString());
        System.out.println("Message received: " + actor);
    }

}