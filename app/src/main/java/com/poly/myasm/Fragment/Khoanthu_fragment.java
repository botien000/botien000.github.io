package com.poly.myasm.Fragment;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.myasm.Adapter.Income_Expenses_Apdater;
import com.poly.myasm.DAO.Income_ExpenseDAO;
import com.poly.myasm.DTO.Income_Expense;
import com.poly.myasm.R;
import com.poly.myasm.Utilities.Constaints;

import java.text.NumberFormat;
import java.util.List;


public class Khoanthu_fragment extends Fragment implements Income_Expenses_Apdater.IDelete {
    private List<Income_Expense> data;
    private RecyclerView   recyclerView;
    private Income_Expenses_Apdater adapter;
    private TextView total;
    FloatingActionButton fab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key",
                Khoanthu_fragment.this, new Dialog_Khoanthuchi_Fragments(){
                    @Override
                    public void onFragmentResult(String requestKey, Bundle result) {
                        super.onFragmentResult(requestKey, result);

                        List<Income_Expense> _data = (List<Income_Expense>)
                                (new Income_ExpenseDAO(getContext())).get(Constaints.income);
                        adapter.updateData(_data);
                        total(_data);
                    }
                });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoanthu_layout,container,false);
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.add_khoanthu);
        total = view.findViewById(R.id.totalkhoanthu);
        data = (List<Income_Expense>) (new Income_ExpenseDAO(getContext())).get(Constaints.income);

        adapter = new Income_Expenses_Apdater(this,data,getContext(),1);
        recyclerView = (RecyclerView) view.findViewById(R.id.khoanthurv);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,false);

            recyclerView.setLayoutManager(layoutManager);
            total(data);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Dialog_Khoanthuchi_Fragments dialogFragment = Dialog_Khoanthuchi_Fragments
                        .newInstance(-1,1);
                dialogFragment.show(fragmentManager, "");

            }
        });

    }

    @Override
    public void del(List<Income_Expense> list) {
       total(list);
    }private void total(List<Income_Expense> list){
        double count = 0;
        for(int i = 0;i < list.size();i++){
            count+=list.get(i).getMoney();
        }
        total.setText("+" + NumberFormat.getInstance().format(count)+" VND");
    }
}
