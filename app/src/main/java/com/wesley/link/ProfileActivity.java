package com.wesley.link;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    EditText fname,surname,phone,age;
    TextView name,sname,number,years;
    Button done= findViewById(R.id.button_done);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String message =intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        name=findViewById(R.id.name);
        sname=findViewById(R.id.surname);
        number=findViewById(R.id.phone_number);
        years=findViewById(R.id.age);

        fname= (EditText)findViewById(R.id.edit_fname);
        surname=(EditText)findViewById(R.id.edit_sname);
        phone=(EditText)findViewById(R.id.edit_phone);
        age=(EditText)findViewById(R.id.edit_age);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName=fname.getText().toString();
                String srname=surname.getText().toString();
                String phone_no=phone.getText().toString();
                String age_yrs=age.getText().toString();
                name.setText(firstName);
                sname.setText(srname);
                number.setText(phone_no);
                years.setText(age_yrs);

            }
        });
    }
}
