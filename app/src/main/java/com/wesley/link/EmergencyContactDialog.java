package com.wesley.link;

import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EmergencyContactDialog extends AppCompatDialogFragment {
    private EditText editTextContact;
    private EmergencyContactDialogListener listener ;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.e_contact_dialog,null);
        editTextContact=view.findViewById(R.id.emergency_number);
        builder.setView(view).setTitle("Emergency Contact");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String number=editTextContact.getText().toString();
                listener.applyTexts(number);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });



        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (EmergencyContactDialogListener)context;
        }catch (ClassCastException e){
            throw  new  ClassCastException(context.toString()+"Must implement EmergencyDialogListener");
        }
    }

    public interface EmergencyContactDialogListener{
        void applyTexts(String number);
    }
}
