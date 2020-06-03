package com.wesley.link;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wesley.link.ui.notifications.NotificationsFragment;

public class ProfileActivity extends AppCompatActivity {
    public static EditText fname,surname,phone,age;
    TextView name,sname,number,years;
    public String firstName;
    public String srname;
    public String phone_no;
    public String age_yrs;
    public setFirstName listener;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";
    NotificationsFragment notificationsFragment=new NotificationsFragment();
//    NotificationsFragment profile=new NotificationsFragment();
//    FragmentTransaction profile_changes=getFragmentManager().beginTransaction();
    //profile_changes.replace(R.id.container,profile).commit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String message =intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);


        Button done= findViewById(R.id.button_done);
        fname= (EditText)findViewById(R.id.edit_fname);
        surname=(EditText)findViewById(R.id.edit_sname);
        phone=(EditText)findViewById(R.id.edit_phone);
        age=(EditText)findViewById(R.id.edit_age);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = fname.getText().toString();
                String srname=surname.getText().toString();
                String phone_no=phone.getText().toString();
                String age_yrs=age.getText().toString();
                String myMessage = "Stack Overflow is cool!";
                save_fname();
                save_age();
                save_phone();
                save_sname();
                load_fname();
                load_sname();
                load_phone();
                load_age();
                updateView();
                NotificationsFragment notificationsFragment=new NotificationsFragment();
                Bundle bundle = new Bundle();
                notificationsFragment.setArguments(bundle);
                FragmentManager fm =getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.commit();
                end();


            }
        });

    }
    public void end() {
        this.finish();
    }
    public void save_fname(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("fname",fname.getText().toString());
        editor.apply();

    }

    public void load_fname(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        firstName=sharedPreferences.getString("fname","");
    }

    public void save_sname(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("sname",surname.getText().toString());
        editor.apply();

    }

    public void load_sname(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        srname=sharedPreferences.getString("sname","");
    }

    public void save_phone(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("phone",phone.getText().toString());
        editor.apply();

    }

    public void load_phone(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        phone_no=sharedPreferences.getString("phone","");
    }

    public void save_age(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("age",age.getText().toString());
        editor.apply();

    }

    public void load_age(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        age_yrs=sharedPreferences.getString("age","");
    }




    public void updateView(){
        notificationsFragment.applyName(firstName);
        notificationsFragment.applySname(srname);
        notificationsFragment.applyNo(phone_no);
        notificationsFragment.applyAge(age_yrs);
    }

    public interface setFirstName{
        void applyName(String firstName);
        void applySname(String sname);
        void applyNo(String phone_no);
        void applyAge(String age);
    }

}
