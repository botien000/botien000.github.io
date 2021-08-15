package com.poly.myasm.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.myasm.Adapter.Category_adapter;
import com.poly.myasm.Adapter.Income_Expenses_Apdater;
import com.poly.myasm.DAO.CategoryDAO;
import com.poly.myasm.DAO.Income_ExpenseDAO;
import com.poly.myasm.DTO.Category;
import com.poly.myasm.DTO.Income_Expense;
import com.poly.myasm.R;
import com.poly.myasm.Utilities.Constaints;

import java.util.List;

public class Loaithu_fragment extends Fragment {
    private List<Category> data;
    private RecyclerView   recyclerView;
    private Category_adapter category_adapter;
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("keyy",
                Loaithu_fragment.this, new Dialog_Loaithuchi_fragments(){
                    @Override
                    public void onFragmentResult(String requestKey, Bundle result) {
                        super.onFragmentResult(requestKey, result);

                        List<Category> _data = (List<Category>)
                            new CategoryDAO(getContext()).get();
                        Log.e("aa","abc");
                        category_adapter.updateData(_data);
                    }
                });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaithuchi_layout,container,false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.addloaithuchi);
        recyclerView = view.findViewById(R.id.loaithuchi_rv);
        data = (new CategoryDAO(getContext())).get();
        category_adapter = new Category_adapter(data,getContext());
        recyclerView.setAdapter(category_adapter);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Dialog_Loaithuchi_fragments dialogFragment = Dialog_Loaithuchi_fragments
                        .newInstance(-1);
                dialogFragment.show(fragmentManager, "");
            }
        });

    }

}
