package com.example.windowsremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textViewIP;
    Button buttonIP, buttonShutdown, buttonRestart, buttonLogout;
    EditText editTextIP;
    String cmd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextIP=(EditText)findViewById(R.id.editTextIP);
        textViewIP=(TextView)findViewById(R.id.textViewIP);
        buttonIP=(Button)findViewById(R.id.buttonIP);

        buttonShutdown=(Button)findViewById(R.id.buttonShutdown);
        buttonRestart=(Button)findViewById(R.id.buttonRestart);
        buttonLogout=(Button)findViewById(R.id.buttonLogout);



        buttonIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=editTextIP.getText().toString();
                SharedPreferences shrd=getSharedPreferences("Karen",MODE_PRIVATE);
                SharedPreferences.Editor editor =shrd.edit();

                editor.putString("str",msg);
                editor.apply();
                textViewIP.setText(msg);
            }
        });

        SharedPreferences getShared = getSharedPreferences("Karen",MODE_PRIVATE);
        final String value =getShared.getString("str","Saved IP will show up here");
        textViewIP.setText(value);

        buttonShutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmd ="shutdown";
                messageSender MessageSender=new messageSender();
                MessageSender.execute(cmd,value);
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmd="restart";
                messageSender MessageSender=new messageSender();
                MessageSender.execute(cmd,value);
            }
        });
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmd="logout";
                messageSender MessageSender=new messageSender();
                MessageSender.execute(cmd,value);
            }
        });
    }

    public void send(View v)
    {
        messageSender MessageSender=new messageSender();
        MessageSender.execute(cmd);
    }
}