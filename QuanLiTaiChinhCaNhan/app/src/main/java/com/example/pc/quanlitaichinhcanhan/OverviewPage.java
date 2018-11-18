package com.example.pc.quanlitaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class OverviewPage extends Fragment {
    private View view;
    private Intent intent_profilepage;
    private ImageButton imageButton_profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.overview_page,container,false);
        init();
        setWidget();
        return view;
    }

    private void setWidget() {
        imageButton_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_profilepage);
            }
        });
    }

    private void init() {
        intent_profilepage = new Intent(view.getContext(),ProfilePage.class);
        imageButton_profile = view.findViewById(R.id.ImageButton_profile);
    }
}
