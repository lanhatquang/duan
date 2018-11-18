package com.example.pc.quanlitaichinhcanhan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {

    private ImageButton update_profile;
    private Dialog dialog_update;
    private EditText E_name, E_address, E_numberphone;
    private TextView T_name, T_numberphone, T_birthday, T_address;
    private String date;
    private DataModel dataModel;
    private String name, address, numberphone;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        init();
        setwidgets();
        drawwidget();
    }

    private void drawwidget() {


        dialog_update.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        dialog_update.setContentView(R.layout.profilesetting);
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_update.show();
            }
        });

        dialog_update.findViewById(R.id.button_birthday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpickerDialog();
            }
        });

        dialog_update.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_update.dismiss();
            }
        });



        E_name =(EditText) dialog_update.findViewById(R.id.Edittext_fullname);
        E_address =(EditText) dialog_update.findViewById(R.id.Edittext_address);
        E_numberphone = (EditText) dialog_update.findViewById(R.id.Edittext_numberphone);

        dialog_update.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = E_name.getText().toString();
                address = E_address.getText().toString();
                numberphone = E_numberphone.getText().toString();
                if(name.equals("") == false && address.equals("")==false && numberphone.equals("") == false){
                    insert_data_user();
                    dialog_update.dismiss();
                }
            }
        });

    }

    private void insert_data_user() {
        dataModel = new DataModel(ProfilePage.this,"UserInfo.sqline",null,1);
        //insert data de so sanh;

        try {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE User SET Name='"+name+"',Address='"+address+"',NumberPhone='"+numberphone+"',Birthday= '"+date+"' WHERE 1");
            dataModel.QueryData(stringBuilder);
            myAsyncTask myAsyncTaskupdate = new myAsyncTask();
            myAsyncTaskupdate.execute();

        }catch (Exception e){
            throw e;
        }
    }

    private void showpickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = dayOfMonth +"/"+ month +"/"+ year;
            }
        },1990,01,01);
        datePickerDialog.show();
    }

    private void setwidgets() {
        //lay du lieu do ra giao dien

        myAsyncTask myAsyncTasktmp = new myAsyncTask();
        myAsyncTasktmp.execute();


    }


    private void init() {
        dialog_update = new Dialog(this);
        update_profile = findViewById(R.id.ImageButton_SetProfileInfo_ProfilePage);

        T_name = findViewById(R.id.TextView_Name_ProfilePage);
        T_numberphone = findViewById(R.id.TextView_NumberPhone_ProfilePage);
        T_address = findViewById(R.id.TextView_Address_ProfilePage);
        T_birthday = findViewById(R.id.TextView_Birthday_ProfilePage);
    }

    public class myAsyncTask extends AsyncTask<Void, Void, Void> {

        public myAsyncTask() {
            super();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            while (cursor.moveToNext()){
                T_name.setText(cursor.getString(0));
                T_numberphone.setText(cursor.getString(2));
                T_address.setText(cursor.getString(3));
                T_birthday.setText(cursor.getString(4));
            }
            cursor.close();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                dataModel = new DataModel(ProfilePage.this,"UserInfo.sqline",null,1);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT Name,Email,NumberPhone,Address,Birthday,Id FROM User");
                cursor = dataModel.GetData(stringBuilder);
            }catch (Exception e){
                throw e;
            }

            return null;
        }
    }
}
