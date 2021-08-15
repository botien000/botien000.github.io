package com.poly.myasm.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import com.poly.myasm.DAO.CategoryDAO;
import com.poly.myasm.DTO.Category;
import com.poly.myasm.R;

public class Dialog_Loaithuchi_fragments extends DialogFragment implements FragmentResultListener {

        private EditText editTextName;
        private Button buttonCancel, buttonSave;

        public Dialog_Loaithuchi_fragments(){}

        public static Dialog_Loaithuchi_fragments newInstance(int id){
            Dialog_Loaithuchi_fragments fragment = new Dialog_Loaithuchi_fragments();
            Bundle arg = new Bundle();
            arg.putInt("id", id);

            fragment.setArguments(arg);

            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dialog_category,
                    container);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            editTextName = (EditText) view.findViewById(R.id.etName);
            buttonCancel = (Button) view.findViewById(R.id.cancel_button);
            buttonSave = (Button) view.findViewById(R.id.submit);
            int id = getArguments().getInt("id", -1);

            editTextName.requestFocus();
            if(id != -1){
               editTextName.setText((new CategoryDAO(getContext()).get(id).getName()));
            }


            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryDAO dao = new CategoryDAO(getContext());
                    String name = editTextName.getText().toString();
                    Category category = new Category();
                    category.setName(name);
                    if (id == -1){
                        dao.insert(category);
                    } else {
                        category.setId(id);
                        dao.update(category);
                    }
                    getParentFragmentManager().setFragmentResult("keyy", new Bundle());
                    onCancelClick();
                }
            });

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCancelClick();
                }
            });


        }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(WindowManager.LayoutParams.TYPE_APPLICATION_MEDIA,WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            getDialog().getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }
        private void onCancelClick(){
            dismiss();
        }

        @Override
        public void onFragmentResult(String requestKey, Bundle result) {

        }
    }


