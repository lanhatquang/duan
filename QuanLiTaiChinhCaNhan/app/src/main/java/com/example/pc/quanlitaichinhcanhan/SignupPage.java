package com.example.pc.quanlitaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupPage extends AppCompatActivity {

    private EditText password,confirm;
    private Button button_continue;
    private DataModel dataModel;
    private Intent intent_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        init();
        setwidget();
    }

    private void init() {
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        button_continue = findViewById(R.id.buton_continue);
        intent_fragment = new Intent(SignupPage.this,FragmentPage.class);
    }

    private void setwidget() {

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password_edit = password.getText().toString();
                String confirm_edit = confirm.getText().toString();

                //tao database
                dataModel = new DataModel(SignupPage.this,"UserInfo.sqline",null,1);



                StringBuilder createdatabase = new StringBuilder();
                createdatabase.append("CREATE TABLE IF NOT EXISTS User( Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(200), Address VARCHAR(200), " +
                        "NumberPhone VARCHAR(11), Email VARCHAR(80), Password VARCHAR(20), Birthday VARCHAR(100), Identifine VARCHAR(20),imageprofile INTEGER)");
                /*
                 * id, name, address, numberphone, email, password,birthday, identifine, imageprofile*/
                dataModel.QueryData(createdatabase);
                //insert data de so sanh;

                StringBuilder createdatabasedelete = new StringBuilder();
                createdatabasedelete.append("DELETE FROM User WHERE ID = 0;");
                dataModel.QueryData(createdatabasedelete);

                try {
                    if(password_edit.length()>8 && password_edit.equals(confirm_edit)){
                        Toast.makeText(SignupPage.this, "ok", Toast.LENGTH_SHORT).show();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("INSERT INTO User VALUES(null,'null',null,null,null,'"+password_edit+"',null,null,null)");
                        dataModel.QueryData(stringBuilder);
                        startActivity(intent_fragment);
                        finish();
                    }
                }catch (Exception e){
                    throw e;
                }

            }
        });
    }
}
