package com.wesley.link.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesley.link.ProfileActivity;
import com.wesley.link.R;

public class NotificationsFragment extends Fragment implements ProfileActivity.setFirstName {
    public static TextView firstname,surname,number,years;
//    private NotificationsViewModel notificationsViewModel;
public static final String EXTRA_MESSAGE="com.wesley.link.extra.MESSAGE";
    private String profileMessage="Profile edit";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View root = inflater.inflate(R.layout.fragment_corner, container, false);

        firstname=(TextView) root.findViewById(R.id.textView6);
        surname=(TextView)root.findViewById(R.id.view_sname);
        number=(TextView)root.findViewById(R.id.view_phone);
        years=(TextView)root.findViewById(R.id.view_age);
        Bundle  bundleFinal;
                bundleFinal=getArguments();
        Toast.makeText(root.getContext(), "This is just a preview with test functionality!!", Toast.LENGTH_SHORT).show();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        Button edit= view.findViewById(R.id.button_edit);

            edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ProfileActivity.class);
                intent.putExtra(EXTRA_MESSAGE,profileMessage);
                startActivity(intent);
            }
        });
    }

    @Override
    public void applyName(String firstName) {
        firstname.setText(firstName);
    }

    @Override
    public void applySname(String sname) {
        surname.setText(sname);
    }

    @Override
    public void applyNo(String phone_no) {
        number.setText(phone_no);
    }

    @Override
    public void applyAge(String age) {
        years.setText(age);
    }
}
