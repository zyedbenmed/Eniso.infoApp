package com.example.alaabid.eniso.EventSchedulerPackage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.alaabid.eniso.R;

/**
 * Created by Ala Abid on 24/03/2018.
 */

public class EventDialog extends AppCompatDialogFragment {

    private EditText etTitle, etDescr;
    private EventDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = etTitle.getText().toString();
                        String desc = etDescr.getText().toString();
                        listener.applyTexts(title, desc);
                    }
                });
        etDescr = view.findViewById(R.id.et_desc);
        etTitle = view.findViewById(R.id.et_title);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EventDialogListener) context;
    }

    public interface EventDialogListener{
        void applyTexts(String title, String desc);
    }

}
