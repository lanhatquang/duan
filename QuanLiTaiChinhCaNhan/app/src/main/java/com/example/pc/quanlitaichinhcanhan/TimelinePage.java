package com.example.pc.quanlitaichinhcanhan;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class TimelinePage extends Fragment {
    private View view;
    private ArrayList<ListChooseIncome> listChooseIncomes;
    private AdapterListChoose adapterListChoose;
    private ListView listView;
    private int index = -1;
    private ImageButton IB_tick;
    private DataModel dataModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.time_line_page,container,false);
        init();
        showList();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String link = bundle.getString("url");
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute();
            adapterListChoose.notifyDataSetChanged();
        }
    }

    private void showList() {
        listChooseIncomes = new ArrayList<>();
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        adapterListChoose = new AdapterListChoose(view.getContext(),R.layout.listview_custom,listChooseIncomes);

        //tao adapter

        listView.setAdapter(adapterListChoose);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean check = listChooseIncomes.get(position).isCheck();
                check = !check;
                ListChooseIncome tmp = listChooseIncomes.get(position);
                tmp.setCheck(check);
                listChooseIncomes.set(position,tmp);
                adapterListChoose.notifyDataSetChanged();
                index = position;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                alertsure(position);
                return false;
            }
        });

//        adapterListChoose.notifyDataSetChanged();
    }

    private void init() {
        dataModel = new DataModel(view.getContext(),"UserInfo.sqline",null,1);
        listView = view.findViewById(R.id.listview_choose);
    }

    public class MyAsyncTask extends AsyncTask<Void,Void,Void> {
        Cursor cursor = null;

        public MyAsyncTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            while (cursor.moveToNext()){
                listChooseIncomes.add(new ListChooseIncome(cursor.getString(1),cursor.getString(3),cursor.getString(2),cursor.getInt(0),true));
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                StringBuilder createdatabaseIncome = new StringBuilder();
                createdatabaseIncome.append("CREATE TABLE IF NOT EXISTS Money( Id INTEGER PRIMARY KEY AUTOINCREMENT,Title VARCHAR(200), Amount VARCHAR(20), Date VARCHAR(8), Type INTEGER DEFAULT 0)");
                dataModel.QueryData(createdatabaseIncome);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT * FROM Money");
                cursor = dataModel.GetData(stringBuilder);
            }catch (Exception e){
                throw e;
            }

            return null;
        }
    }
}
