package com.poly.myasm.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import com.cottacush.android.currencyedittext.CurrencyEditText;
import com.poly.myasm.Adapter.CategoryItemAdapter;
import com.poly.myasm.DAO.CategoryDAO;
import com.poly.myasm.DAO.Income_ExpenseDAO;
import com.poly.myasm.DTO.Category;
import com.poly.myasm.DTO.Income_Expense;
import com.poly.myasm.R;
import com.poly.myasm.Utilities.Constaints;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dialog_Khoanthuchi_Fragments extends DialogFragment
        implements FragmentResultListener, DatePickerDialog.OnDateSetListener {

    private EditText editText_name, editText_description, editText_amount;
    private TextView textViewCreated, textViewCreatedValue;
    private Spinner spinnerCategory;
    private Button buttonCancelCategoryName, buttonSaveCategoryName;

    private List<Category> data;
    private CategoryItemAdapter adapter;
    CurrencyEditText    currencyEditText;
    private Integer flag = Constaints.income;
    private Integer id = -1;

    private Integer category_id = 0;
    private Date createdDate = new Date();
    Income_ExpenseDAO dao;

    public Dialog_Khoanthuchi_Fragments() {
    }

    public static Dialog_Khoanthuchi_Fragments newInstance(Integer id, Integer flag) {
        Dialog_Khoanthuchi_Fragments fragment = new Dialog_Khoanthuchi_Fragments();
        Bundle arg = new Bundle();
        arg.putInt("id", id);
        arg.putInt("flag", flag);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_ie_add, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new Income_ExpenseDAO(getContext());
        textViewCreatedValue = (TextView) view.findViewById(R.id.textViewCreatedValue);
        textViewCreated = (TextView) view.findViewById(R.id.textViewCreated);
        buttonCancelCategoryName = (Button) view.findViewById(R.id.buttonCancelCategoryName);
        buttonSaveCategoryName = (Button) view.findViewById(R.id.buttonSaveCategoryName);
        editText_name = (EditText) view.findViewById(R.id.editText_name);
        editText_description = (EditText) view.findViewById(R.id.editText_description);
        currencyEditText = view.findViewById(R.id.editText_amount);
        spinnerCategory = (Spinner) view.findViewById(R.id.spinnerCategory);

        // lay danh sach category cho vao spinner
        data = (new CategoryDAO(getContext())).get();
        adapter = new CategoryItemAdapter(getContext(), data);
        spinnerCategory.setAdapter(adapter);
        spinnerCategory.setSelection(0);

        flag = getArguments().getInt("flag", Constaints.income);
        id = getArguments().getInt("id", -1);
        if (id != -1) {
            Income_Expense incomeExpense = dao.getId(id);
            editText_name.setText(incomeExpense.getName());
            editText_description.setText(incomeExpense.getDescription());
            currencyEditText.setText(NumberFormat.getInstance().format(incomeExpense.getMoney()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            textViewCreatedValue.setText(sdf.format(incomeExpense.getDate()));
            category_id = getIndex(data, incomeExpense.getCategory_id());
            spinnerCategory.setSelection(category_id);
        }
        // neu id = -1: them moi
        // nguoc lai: cap nhat
//        editTextName.setText(name);

//        editTextName.requestFocus();



        buttonSaveCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Income_Expense model = new Income_Expense();
                model.setName(editText_name.getText().toString());
                model.setDescription(editText_description.getText().toString());
                model.setMoney(currencyEditText.getNumericValue());
                model.setId(id);
                model.setFlag(flag);
                model.setDate(createdDate);
                model.setCategory_id(category_id);

                if (id == -1) {
                    dao.insert(model);

                } else {
                    dao.update(model);
                }
                getParentFragmentManager().setFragmentResult("key", new Bundle());
                onCancelClick();
            }
        });

        buttonCancelCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClick();
            }
        });

        textViewCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getChildFragmentManager(), "datePicker");
            }
        });

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = (Category) adapterView.getItemAtPosition(i);
                String name = category.getName();

                List<Category> list = (new CategoryDAO(getContext())).get();



                for(int index = 0;index < list.size();index++){


                    if(name.equalsIgnoreCase(list.get(index).getName())){
                        category_id = list.get(index).getId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void onCancelClick() {
        dismiss();
    }

    @Override
    public void onFragmentResult(String requestKey, Bundle result) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        createdDate = calendar.getTime();
        textViewCreatedValue.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year + " ");
    }

    private int getIndex(List<Category> list, int category_id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == category_id) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(WindowManager.LayoutParams.TYPE_APPLICATION_MEDIA, WindowManager.LayoutParams.WRAP_CONTENT);
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }
    }
}

