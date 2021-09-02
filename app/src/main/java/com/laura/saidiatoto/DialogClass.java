package com.laura.saidiatoto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

public class DialogClass extends AppCompatDialogFragment {
    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable  Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Change Language").setMessage("Change Language to Swahili?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Context context;
                LocaleHelper.setLocale(getContext(),"sw-rKE"); //for french;
                Toast.makeText(getContext(), "Changed to Swahili", Toast.LENGTH_SHORT).show();

            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LocaleHelper.setLocale(getContext(),"en"); //for english;
                Toast.makeText(getContext(), "Changed to English", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();

    }
}
