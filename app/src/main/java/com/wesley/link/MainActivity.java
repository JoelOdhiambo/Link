package com.wesley.link;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements EmergencyContactDialog.EmergencyContactDialogListener {
    private static final int REQUEST_CALL = 1;
    EditText contactInput;
    private TextView tnumber;
    private static final String EXTRA_MESSAGE = "com.wesley.link.extra.MESSAGE";
    private String profileMessage = "Profile edit";
    String contact;
    private String numberFromDialog;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer sirenSound = MediaPlayer.create(this, R.raw.salamisound);
//        Button edit=findViewById(R.id.button_edit);
//
//            edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
//                intent.putExtra(EXTRA_MESSAGE,profileMessage);
//                startActivity(intent);
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.trust_contact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numberFromDialog));
                if (numberFromDialog != null ){
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    } else {
                        startActivity(i);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }

        });
        FloatingActionButton ring = findViewById(R.id.emergency_ring);
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sirenSound.start();
            }
        });
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                onClickShowAbout(R.id.about);
                return true;
            case R.id.emergency_contact:
                openDialog(R.id.emergency_contact);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean onClickShowAbout(int item) {
        AlertDialog.Builder myAboutBuilder = new AlertDialog.Builder(MainActivity.this);
        myAboutBuilder.setTitle("About App");
        myAboutBuilder.setMessage("App by Wesley Joel Odhiambo");

        myAboutBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "WJ", Toast.LENGTH_SHORT).show();
            }
        });
        myAboutBuilder.show();
        return true;
    }

    public boolean openDialog(int item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.e_contact_dialog, null);
        final EditText dialogEditTextContact = view.findViewById(R.id.emergency_number);
        builder.setView(view).setTitle("Emergency Contact");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                numberFromDialog = dialogEditTextContact.getText().toString();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        return true;
    }


    @Override
    public void applyTexts(String number) {

    }
}
