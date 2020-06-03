package com.wesley.link.ui.home;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.wesley.link.R;

import java.util.Locale;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextToSpeech textToSpeech;
    private Button done;
    private EditText editTextMessage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        done=root.findViewById(R.id.button_done);
        editTextMessage=root.findViewById(R.id.text_input);

        //Initializing  TTS
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    //Check if language is supported
                    if(ttsLang==TextToSpeech.LANG_MISSING_DATA || ttsLang==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Oops!The language is not currently supported!");
                    }else{
                        Log.i("TTS","Language supported.");
                    }
                    Log.i("TTS","Initialization success!");
                }else {
                    Toast.makeText(getActivity(),"TTS initialization failed!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        done.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                //Read text when button  is clicked
                String data =editTextMessage.getText().toString();
                Log.i("TTS","button clicked: " + data);

                int speechStatus=textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
                if (speechStatus==TextToSpeech.ERROR){
                    Log.e("TTS", "Error in converting Text to Speech!!!");
                    onDestroy();
                }
            }
        });

        return root;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
