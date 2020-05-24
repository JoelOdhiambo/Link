package com.wesley.link;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private int MY_DATA_CHECK_CODE=0;
    private TextToSpeech myTTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button done = findViewById(R.id.button_done);
        done.setOnClickListener(this);

        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
    public void onClick(View view) {
        //handling user clicks
        EditText input =findViewById(R.id.text_input);
        String message=input.getText().toString();
        speakWords(message);

    }
    private  void speakWords(String message){
        //TTS implementation
        myTTS.speak(message, TextToSpeech.QUEUE_FLUSH,null);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);
            } else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    @Override
    public void onInit(int initStatus) {
    if(initStatus== TextToSpeech.SUCCESS){
        myTTS.setLanguage(Locale.US);
    }else if(initStatus==TextToSpeech.ERROR){
        Toast.makeText(this,"Sorry! Text TO Speech failed...", Toast.LENGTH_LONG).show();
    }if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE) myTTS.setLanguage(Locale.US);

    }
}
