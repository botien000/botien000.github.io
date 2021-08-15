package com.poly.myasm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.myasm.DAO.CategoryDAO;

import com.poly.myasm.DTO.Category;


import com.poly.myasm.Fragment.Dialog_Loaithuchi_fragments;
import com.poly.myasm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Category_adapter extends RecyclerView.Adapter<Category_adapter.ViewHolder> {
    private Context contextt;
    Category category;
    private List<Category> data;
    public Category_adapter(List<Category> _data, Context context){
        data = _data;
        contextt = context;
    }

    public void updateData (List<Category> _data){
        data.clear();
        data.addAll(_data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycleview_category, parent, false);
            Category_adapter.ViewHolder holder = new Category_adapter.ViewHolder(view,new ViewHolder.IMyViewHolderLongClicks() {
                @Override
                public void onItemLongClick(View caller) {
                    PopupMenu menu = new PopupMenu(parent.getContext(), caller);
                    menu.inflate(R.menu.menupopup);
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.edit:
                                    FragmentManager fragmentManager = ((AppCompatActivity)contextt).getSupportFragmentManager();
                                   Dialog_Loaithuchi_fragments dialogFragment = Dialog_Loaithuchi_fragments
                                            .newInstance(data.get(viewType).getId());
                                    dialogFragment.show(fragmentManager, "");
                                    return true;
                                case R.id.del:
                                   CategoryDAO dao = new CategoryDAO(contextt);
                                    dao.delete(data.get(viewType).getId());
                                    List<Category> _data = dao.get();
                                    updateData(_data);
                                    return true;
                                default:
                                    return false;


                        }
                    }
                    });}
            });
            return holder;


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(Category_adapter.ViewHolder holder, int position) {
        Category category = data.get(position);

        TextView textViewName = holder.getTextViewName();
        textViewName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final TextView textViewName;
        public Category_adapter.ViewHolder.IMyViewHolderLongClicks listener;

        //     private final ConstraintLayout constraintLayout;
        public ViewHolder(View itemView, Category_adapter.ViewHolder.IMyViewHolderLongClicks _listener) {
            super(itemView);

            listener = _listener;
            textViewName = (TextView) itemView.findViewById(R.id.textCate);

            itemView.setOnLongClickListener(this);
            //    constraintLayout = itemView.findViewById(R.id.myRecycler);
        }

        public TextView getTextViewName() {
            return textViewName;
        }


        @Override
        public boolean onLongClick(View v) {
            listener.onItemLongClick(v);
            return true;
        }

        public static interface IMyViewHolderLongClicks {
            public void onItemLongClick(View caller);
        }

    }
}
