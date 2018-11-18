package com.example.pc.quanlitaichinhcanhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterListChoose extends BaseAdapter {

    private Animation animation;

    private Context context;
    private int layout;
    private List<ListChooseIncome> listChooseIncomes;

    public AdapterListChoose(Context context, int layout, List<ListChooseIncome> listChooseIncomes) {
        this.context = context;
        this.layout = layout;
        this.listChooseIncomes = listChooseIncomes;
    }



    @Override
    public int getCount() {
        return listChooseIncomes.size();
    }

    @Override
    public Object getItem(int position) {
        return listChooseIncomes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView title_text, money_text, date_text;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.title_text = convertView.findViewById(R.id.titlecustom);
            viewHolder.date_text = convertView.findViewById(R.id.datecustom);
            viewHolder.money_text = convertView.findViewById(R.id.moneycustom);
            viewHolder.imageView = convertView.findViewById(R.id.checkboxcustom);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListChooseIncome template = listChooseIncomes.get(position);
        viewHolder.title_text.setText(template.getTitle());
        viewHolder.date_text.setText(template.getDate());
        viewHolder.money_text.setText(template.getMoney()+"$");
        if (template.isCheck() == true){
            viewHolder.imageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }else {
            viewHolder.imageView.setImageResource(R.drawable.ic_remove_circle_black_24dp);
        }

        return convertView;
    }
}
