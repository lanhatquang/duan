package com.example.pc.quanlitaichinhcanhan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HomePage extends Fragment {
    private View view;
    private BarChart barChart;
    private ImageButton inputdata_button;
    private Dialog dialog;
    private EditText editText_title, editText_money;
    private Button button_date, button_submit,button_cancle;
    private Switch aSwitchbutton;
    private boolean datastatus = false;
    private String date;
    private DataModel dataModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.home_page,container,false);
        init();
        setWidget();
        return view;
    }

    private void setWidget() {

        draw_barchart();
        inputdata();

    }

    private void inputdata() {


        inputdata_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        aSwitchbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                datastatus = isChecked;
            }
        });

        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogdatepicker();
            }
        });

        //lay data
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushdatabase();
                dialog.dismiss();
                TimelinePage myFragment = new TimelinePage();
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://xuxu_minhanh");
                myFragment.setArguments(bundle);
            }
        });

    }

    private void pushdatabase() {

        dataModel = new DataModel(view.getContext(),"UserInfo.sqline",null,1);
        StringBuilder createdatabaseIncome = new StringBuilder();
        createdatabaseIncome.append("CREATE TABLE IF NOT EXISTS Money( Id INTEGER PRIMARY KEY AUTOINCREMENT,Title VARCHAR(200), Amount VARCHAR(20), Date VARCHAR(8), Type INTEGER DEFAULT 0)");
        dataModel.QueryData(createdatabaseIncome);

        String title = editText_title.getText().toString();
        String money = editText_money.getText().toString();
        int status = (datastatus == true)?1:0;

        if (!title.equals("") && !money.equals("")){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO Money VALUES(null,'"+title+"','"+money+"','"+date+"','"+status+"')");
            dataModel.QueryData(stringBuilder);
            Toast.makeText(view.getContext(), "ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void showdialogdatepicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = dayOfMonth +"/" + month +"/" + year;
            }
        },2018,10,10);
        datePickerDialog.show();
    }

    private void draw_barchart() {
        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(0, 6));
        NoOfEmp.add(new BarEntry(1, 3));
        NoOfEmp.add(new BarEntry(2, 7));
        NoOfEmp.add(new BarEntry(3, 2));
        NoOfEmp.add(new BarEntry(4, 8));
        NoOfEmp.add(new BarEntry(5, 1));
        NoOfEmp.add(new BarEntry(6, 9));

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        bardataset.setColor(Color.WHITE);

        barChart.animateY(5000);
        BarData data = new BarData((IBarDataSet)bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setNoDataTextColor(Color.WHITE);
        barChart.setData(data);
    }

    private void init() {
        dialog = new Dialog(view.getContext());
        inputdata_button = view.findViewById(R.id.inputdata_button);
        barChart = view.findViewById(R.id.bar_chart);
        dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        dialog.setContentView(R.layout.inputdata);
        editText_money = dialog.findViewById(R.id.Edittext_Money);
        editText_title = dialog.findViewById(R.id.Edittext_Title);
        button_cancle = dialog.findViewById(R.id.back_button);
        button_submit = dialog.findViewById(R.id.submit_button);
        button_date = dialog.findViewById(R.id.button_date);
        aSwitchbutton = dialog.findViewById(R.id.switchbutton);
    }
}
