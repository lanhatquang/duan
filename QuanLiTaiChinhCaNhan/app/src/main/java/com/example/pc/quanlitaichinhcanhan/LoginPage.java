package com.example.pc.quanlitaichinhcanhan;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private EditText password;
    private Button button_login;
    private Intent intent_fragment;
    private DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        init();
        setWidget();
    }

    private void setWidget() {
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("");
    }

    private void init() {
        password = findViewById(R.id.password_login);
        button_login = findViewById(R.id.buton_login);
        intent_fragment = new Intent(this,FragmentPage.class);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, Void> {

        private Cursor cursor;

        public MyAsyncTask() {
            super();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String password_edit = password.getText().toString();
                    if(password_edit.equals("")){
                        Toast.makeText(LoginPage.this, "You have to enter your email and password", Toast.LENGTH_SHORT).show();
                    }else{

                        String password_data = null;
                        while (cursor.moveToNext()){
                            password_data = cursor.getString(0);
                        }
                        if(password_edit.equals(password_data)){
                            startActivity(intent_fragment);
                            finish();
                        }

                    }

                }
            });
        }

        @Override
        protected Void doInBackground(String... strings) {
            //tao database
            dataModel = new DataModel(LoginPage.this,"UserInfo.sqline",null,1);

            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT Password FROM User");
                cursor = dataModel.GetData(stringBuilder);
            }catch (Exception e){
                throw e;
            }

            return null;
        }
    }

}
