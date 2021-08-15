package com.poly.myasm.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.myasm.DAO.Income_ExpenseDAO;
import com.poly.myasm.R;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;


public class activity_charts extends AppCompatActivity {
    Button btnyear;
    EditText edtyear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        btnyear = findViewById(R.id.btnyear);
        edtyear = findViewById(R.id.yeartext);
    final LineView lineView = (LineView) findViewById(R.id.line_chart);

    initLineView(lineView);
    btnyear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            randomSet(lineView);
        }
    });

    }
    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            test.add(String.valueOf(i + 1));
        }
        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[] {
                Color.parseColor("#F44336"), Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F3"), Color.parseColor("#009688")
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
    }

    private void randomSet(LineView lineView) {
//        Double a = 5.5;
//        Float b = a.floatValue();
//        Log.e("values",b+"");
//        ArrayList<Float> dataList = new ArrayList<>();
//        float random = (float) (Math.random() * 9 + 1);
//        for (int i = 0; i < 12; i++) {
//            dataList.add((float) (Math.random() * random));
//        }
//
//        ArrayList<Float> dataList2 = new ArrayList<>();
//        random = (int) (Math.random() * 9 + 1);
//        for (int i = 0; i < 12; i++) {
//            dataList2.add((float) (Math.random() * random));
//        }
//
//        ArrayList<Float> dataList3 = new ArrayList<>();
//        random = (int) (Math.random() * 9 + 1);
//        for (int i = 0; i < 12; i++) {
//                if(i == 2){
//                    dataList3.add(12.5f);
//                }
//                else{dataList3.add((float) (Math.random() * random));}
//
//        }
        ArrayList<Float> datain = (new Income_ExpenseDAO(this)).getChart( Integer.parseInt(edtyear.getText().toString()),1);
        ArrayList<Float> dataex = (new Income_ExpenseDAO(this)).getChart( Integer.parseInt(edtyear.getText().toString()),0);
        ArrayList<ArrayList<Float>> dataLists = new ArrayList<>();
//        dataLists.add(dataList);
//        dataLists.add(dataList2);
//        dataLists.add(dataList3);
        dataLists.add(datain);
        dataLists.add(dataex);
        lineView.setFloatDataList(dataLists);
        



}}