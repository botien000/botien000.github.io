package com.poly.myasm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.myasm.DAO.CategoryDAO;
import com.poly.myasm.DAO.Income_ExpenseDAO;
import com.poly.myasm.DTO.Income_Expense;
import com.poly.myasm.Database.MyDatabase;
import com.poly.myasm.Fragment.Dialog_Khoanthuchi_Fragments;
import com.poly.myasm.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Income_Expenses_Apdater extends RecyclerView.Adapter<Income_Expenses_Apdater.ViewHolder> {
    private Context contextt;
    Income_Expense income_expense;
    IDelete iDelete;
    int _thu_chi ;
    private List<Income_Expense> data;
    public Income_Expenses_Apdater(IDelete i,List<Income_Expense> _data, Context context,int thu_chi){
        data = _data;
        _thu_chi = thu_chi;
        contextt = context;
        iDelete = i;
    }

    public void updateData (List<Income_Expense> _data){
        data.clear();
        data.addAll(_data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      if(_thu_chi == 1) { View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_khoanthu, parent, false);
        ViewHolder holder = new ViewHolder(view, new ViewHolder.IMyViewHolderLongClicks() {
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
                                Dialog_Khoanthuchi_Fragments dialogFragment = Dialog_Khoanthuchi_Fragments
                                        .newInstance(data.get(viewType).getId(),data.get(viewType).getFlag());
                                dialogFragment.show(fragmentManager, "");
                                return true;
                            case R.id.del:
                                Income_ExpenseDAO dao = new Income_ExpenseDAO(contextt);
                                dao.detele(data.get(viewType).getId());
                                List<Income_Expense> _data = dao.get(data.get(viewType).getFlag());
                                updateData(_data);
                                iDelete.del(_data);
                                return true;
                            default:
                                return false;
                        }

                    }
                });
            }
        });
          return holder;
      }else {
          View view = LayoutInflater.from(parent.getContext())
                  .inflate(R.layout.recyclerview_khoanchi, parent, false);
          ViewHolder holder = new ViewHolder(view, new ViewHolder.IMyViewHolderLongClicks() {
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
                                  Dialog_Khoanthuchi_Fragments dialogFragment = Dialog_Khoanthuchi_Fragments
                                          .newInstance(data.get(viewType).getId(),data.get(viewType).getFlag());
                                  dialogFragment.show(fragmentManager, "");
                                  return true;
                              case R.id.del:
                                  Income_ExpenseDAO dao = new Income_ExpenseDAO(contextt);
                                  dao.detele(data.get(viewType).getId());
                                  List<Income_Expense> _data = dao.get(data.get(viewType).getFlag());
                                  updateData(_data);
                                  iDelete.del(_data);
                                  return true;
                              default:
                                  return false;
                          }

                      }
                  });
              }
          });
          return holder;
      }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder( Income_Expenses_Apdater.ViewHolder holder, int position) {

        Income_Expense income_expense = data.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        TextView textViewName = holder.getTextViewName();
        TextView textViewDate = holder.getTextViewDate();
        textViewName.setText(new CategoryDAO(holder.itemView.getContext()).get(income_expense.getCategory_id()).getName());
        textViewDate.setText(simpleDateFormat.format(income_expense.getDate()));
      if(_thu_chi == 1) { holder.getTextViewMoney().setText("+"+NumberFormat.getInstance().format(income_expense.getMoney()));}
      else{ holder.getTextViewMoney().setText("-"+NumberFormat.getInstance().format(income_expense.getMoney()));}
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final TextView textViewName,textViewDate,textViewMoney;
        public IMyViewHolderLongClicks listener;

        //     private final ConstraintLayout constraintLayout;
        public ViewHolder(View itemView,IMyViewHolderLongClicks _listener) {
            super(itemView);

            listener = _listener;
            textViewName = (TextView) itemView.findViewById(R.id.txtCategory);
            textViewDate = (TextView) itemView.findViewById(R.id.txtDate);
            textViewMoney = (TextView) itemView.findViewById(R.id.txtmoney);
            itemView.setOnLongClickListener(this);
            //    constraintLayout = itemView.findViewById(R.id.myRecycler);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewDate() {
            return textViewDate;
        }
        public TextView getTextViewMoney() {
            return textViewMoney;
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
    public interface IDelete{
        public void del(List<Income_Expense> list);
    }
}
