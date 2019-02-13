package com.notifybuddy.app;



import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;



public class FeedbackFragment extends Fragment {

    TextView messagedata;
    ImageButton send;
    Firebase firebase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        send=(ImageButton)v.findViewById(R.id.btn_send);

        // create id for data
        messagedata = v.findViewById(R.id.feedback_message);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebase = new Firebase("https://notifybuddy-464c2.firebaseio.com/user/" + "test@test" + System.currentTimeMillis());
                final  String message = messagedata.getText().toString();

                Firebase child_message = firebase.child("Message");
                child_message.setValue(message);
                if (message.isEmpty())
                {
                    messagedata.setError("This is an require field!");
                    send.setEnabled(false);
                }
                else {
                    messagedata.setError(null);
                    send.setEnabled(true);
                    firebase.push();
                    Toast.makeText(getContext(), "Feedback sent successfully.", Toast.LENGTH_SHORT).show();
                    messagedata.setText("");

                }
            }
        });

        return v;

    }


}
