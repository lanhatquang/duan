package com.example.pc.quanlitaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartPage extends AppCompatActivity {

    private Animation animation,animation1;
    private TextView textView1,textView2;
    private ImageView imageView;
    private Button button_login, button_create;
    private Intent intent_signup,intent_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        init();
        handler();
        setWidget();
    }

    private void setWidget() {
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_signup);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_login);
            }
        });
    }

    private void handler() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                animation = AnimationUtils.loadAnimation(StartPage.this,R.anim.up);
                animation1 = AnimationUtils.loadAnimation(StartPage.this,R.anim.zoom);

                imageView.startAnimation(animation);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                button_create.startAnimation(animation);
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                button_login.startAnimation(animation);
//                try {
//                    Thread.sleep(400);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }).start();

    }

    private void init() {
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        imageView = findViewById(R.id.icon_app);
        button_create = findViewById(R.id.btn_create);
        button_login = findViewById(R.id.btn_login);
        intent_login = new Intent(this,LoginPage.class);
        intent_signup = new Intent(this,SignupPage.class);
    }

}
