package com.baokiiin.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.baokiiin.myapplication.R;

import static com.baokiiin.myapplication.utils.Utils.TITLE;
import static com.baokiiin.myapplication.utils.Utils.TYPE;
import static com.baokiiin.myapplication.utils.Utils.showPopupWindow;


public class CustomDiaLogFragment extends DialogFragment {

    Button btnCancel;
    Button btnOK;
    TextView txtTitle;
    TextView txtClick;
    String title;
    Boolean type;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        unit(view);
        create();
        clickView();
        return view;
    }

    private void unit(View view) {
        btnCancel = view.findViewById(R.id.btnCancel);
        txtTitle = view.findViewById(R.id.txtTitle);
        btnOK = view.findViewById(R.id.btnOk);
        txtClick = view.findViewById(R.id.txtClick);
    }

    private void create() {
        title = getArguments() != null ? getArguments().getString(TITLE, "") : null;
        type = getArguments().getBoolean(TYPE, false);
        txtTitle.setText(title);
        if (!type)
            btnCancel.setVisibility(View.GONE);
    }

    private void clickView() {
        btnCancel.setOnClickListener(v -> dismiss());
        btnOK.setOnClickListener(v -> {
            dismiss();
            Toast.makeText(getContext(), txtClick.getText().toString(), Toast.LENGTH_SHORT).show();
        });
        txtClick.setOnClickListener(v -> showPopupWindow(getContext(), v));
    }
}
