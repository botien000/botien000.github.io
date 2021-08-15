package com.poly.myasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.myasm.DTO.Income_Expense;
import com.poly.myasm.Database.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.poly.myasm.Utilities.Constaints.*;

public class Income_ExpenseDAO implements Inter_Income_Expense_DAO{
    MyDatabase db;
    public Income_ExpenseDAO(Context c) {
        db = MyDatabase.getInstance(c);
    }

    @Override
    public List<Income_Expense> get(int _flags) {
        List<Income_Expense> list = new ArrayList<>();
        String q = "select "+ "*"

                + "from "  + table_income_expense
                + " where " + column_flag  + "  = ? ";
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q,new String[]{String.valueOf(_flags)});
        try{
            if(    cursor.moveToFirst()){
                while(cursor.isAfterLast() == false){
                    int id = cursor.getInt(cursor.getColumnIndex(column_income_expense_id));
                    String name = cursor.getString(cursor.getColumnIndex(column_income_expense_name));
                    String desciption = cursor.getString(cursor.getColumnIndex(column_income_expense_description));
                    Long dateInt = cursor.getLong(cursor.getColumnIndex(column_income_expense_create_date));
                    Date  date = new Date(dateInt);

//                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(cursor.getColumnIndex(COLUMN_INCOME_EXPENSE_CREATED_DATE)));
                    int flag = cursor.getInt(cursor.getColumnIndex(column_flag));
                    int money = cursor.getInt(cursor.getColumnIndex(column_income_expense_amount));
                    int category_id = cursor.getInt(cursor.getColumnIndex(Column_income_expense_category_id));
                    Income_Expense income_expense = new Income_Expense(id,name,desciption,date,money,flag,category_id);
                    list.add(income_expense);
                    cursor.moveToNext();
                }
            }
        }catch(Exception e ){
            Log.e("Get income ex error",e.getMessage());

        }finally {
            if(cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return list;
    }

    @Override
    public List<Income_Expense> get() {
        List<Income_Expense> list = new ArrayList<>();
        String q = "select "+ "*"
                + "from "  + table_income_expense;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q,null);
        try{
            if(    cursor.moveToFirst()){
                while(cursor.isAfterLast() == false){
                    int id = cursor.getInt(cursor.getColumnIndex(column_income_expense_id));
                    String name = cursor.getString(cursor.getColumnIndex(column_income_expense_name));
                    String desciption = cursor.getString(cursor.getColumnIndex(column_income_expense_description));
                    Long dateInt = cursor.getLong(cursor.getColumnIndex(column_income_expense_create_date));
                    Date  date = new Date(dateInt);
                    int flag = cursor.getInt(cursor.getColumnIndex(column_flag));
                    int money = cursor.getInt(cursor.getColumnIndex(column_income_expense_amount));
                    int category_id = cursor.getInt(cursor.getColumnIndex(Column_income_expense_category_id));
                    Income_Expense income_expense = new Income_Expense(id,name,desciption,date,money,flag,category_id);
                    list.add(income_expense);
                    cursor.moveToNext();
                }
            }
        }catch(Exception e ){
            Log.e("Get income ex error",e.getMessage());

        }finally {
            if(cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return list;
    }
    public ArrayList<Float> getChart(int year,int flag){
        ArrayList<Float> list = null;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month1 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month2 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,2);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month3 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,3);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month4 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,4);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month5 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,5);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month6 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,6);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month7 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,7);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month8 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,8);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month9 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,9);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month10 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,10);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month11 = calendar.getTime().getTime();
        calendar.set(Calendar.MONTH,11);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Long month12 = calendar.getTime().getTime();
        calendar.set(Calendar.DAY_OF_MONTH,31);
        Long month12day30 = calendar.getTime().getTime();

        String q = "SELECT  * from " +
                "(select sum("+column_income_expense_amount+") as Month1 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month1+" and "+column_income_expense_create_date+" < "+month2+"), " +
                "(select sum("+column_income_expense_amount+") as Month2 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month2+" and "+column_income_expense_create_date+" < "+month3+"), " +
                "(select sum("+column_income_expense_amount+") as Month3 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month3+" and "+column_income_expense_create_date+" < "+month4+"), " +
                "(select sum("+column_income_expense_amount+") as Month4 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month4+" and "+column_income_expense_create_date+" < "+month5+"), " +
                "(select sum("+column_income_expense_amount+") as Month5 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month5+" and "+column_income_expense_create_date+" < "+month6+"), " +
                "(select sum("+column_income_expense_amount+") as Month6 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month6+" and "+column_income_expense_create_date+" < "+month7+"), " +
                "(select sum("+column_income_expense_amount+") as Month7 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month7+" and "+column_income_expense_create_date+" < "+month8+"), " +
                "(select sum("+column_income_expense_amount+") as Month8 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month8+" and "+column_income_expense_create_date+" < "+month9+"), " +
                "(select sum("+column_income_expense_amount+") as Month9 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month9+" and "+column_income_expense_create_date+" < "+month10+"), " +
                "(select sum("+column_income_expense_amount+") as Month10 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month10+" and "+column_income_expense_create_date+" < "+month11+"), " +
                "(select sum("+column_income_expense_amount+") as Month11 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month11+" and "+column_income_expense_create_date+" < "+month12+"), " +
                "(select sum("+column_income_expense_amount+") as Month12 from "+table_income_expense+"  where "+ column_flag +" = "+flag+" and "+column_income_expense_create_date+" >= "+month12+" and "+column_income_expense_create_date+" <=    "+month12day30+") ";

        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q, null);
        try {
            if (cursor.moveToFirst()){
                while (cursor.isAfterLast() == false){
                    Double m1 = cursor.getDouble(cursor.getColumnIndex("Month1"));
                    Double m2 = cursor.getDouble(cursor.getColumnIndex("Month2"));
                    Double m3 = cursor.getDouble(cursor.getColumnIndex("Month3"));
                    Double m4 = cursor.getDouble(cursor.getColumnIndex("Month4"));
                    Double m5 = cursor.getDouble(cursor.getColumnIndex("Month5"));
                    Double m6 = cursor.getDouble(cursor.getColumnIndex("Month6"));
                    Double m7 = cursor.getDouble(cursor.getColumnIndex("Month7"));
                    Double m8 = cursor.getDouble(cursor.getColumnIndex("Month8"));
                    Double m9 = cursor.getDouble(cursor.getColumnIndex("Month9"));
                    Double m10 = cursor.getDouble(cursor.getColumnIndex("Month10"));
                    Double m11 = cursor.getDouble(cursor.getColumnIndex("Month11"));
                    Double m12 = cursor.getDouble(cursor.getColumnIndex("Month12"));

                    list = new ArrayList<>();
                    list.add(m1.floatValue()/1000000);
                    list.add(m2.floatValue()/1000000);
                    list.add(m3.floatValue()/1000000);
                    list.add(m4.floatValue()/1000000);
                    list.add(m5.floatValue()/1000000);
                    list.add(m6.floatValue()/1000000);
                    list.add(m7.floatValue()/1000000);
                    list.add(m8.floatValue()/1000000);
                    list.add(m9.floatValue()/1000000);
                    list.add(m10.floatValue()/1000000);
                    list.add(m11.floatValue()/1000000);
                    list.add(m12.floatValue()/1000000);
                    cursor.moveToNext();
                }
            }
        }catch(Exception e) {
            Log.e("Get Chart expense error: ", e.getMessage());
        } finally {
            if (cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return list;
    }
    @Override
    public Income_Expense getId(int id) {
        Income_Expense income_expense = null;
        String q = "SELECT " + " * " +
                "FROM  "+table_income_expense+" WHERE  "+column_income_expense_id+" = ?  ";
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q, new String[]{String.valueOf(id)});
        try {
            if (cursor.moveToFirst()){
                while (cursor.isAfterLast() == false){
                    int _id = cursor.getInt(cursor.getColumnIndex(column_income_expense_id));
                    String name = cursor.getString(cursor.getColumnIndex(column_income_expense_name));
                    String desciption = cursor.getString(cursor.getColumnIndex(column_income_expense_description));
                    Long dateInt = cursor.getLong(cursor.getColumnIndex(column_income_expense_create_date));
                    Date  date = new Date(dateInt);
                    int flag = cursor.getInt(cursor.getColumnIndex(column_flag));
                    int money = cursor.getInt(cursor.getColumnIndex(column_income_expense_amount));
                    int category_id = cursor.getInt(cursor.getColumnIndex(Column_income_expense_category_id));
                    income_expense = new Income_Expense(id,name,desciption,date,money,flag,category_id);
                    cursor.moveToNext();
                }
            }
        }catch(Exception e) {
            Log.e("Get Income expense error: ", e.getMessage());
        } finally {
            if (cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return income_expense;

    }


    public void insert(Income_Expense income_expense) {
        SQLiteDatabase database = db.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put(column_income_expense_name,income_expense.getName());
            values.put(column_income_expense_description,income_expense.getDescription());
            values.put(column_income_expense_create_date,income_expense.getDate().getTime());
            values.put(column_flag,income_expense.getFlag());
            values.put(column_income_expense_amount,income_expense.getMoney());
            values.put(Column_income_expense_category_id,income_expense.getCategory_id());
            database.insertOrThrow(table_income_expense,null,values);

        }catch(Exception e){
            Log.e("Insert get error",e.getMessage());
        }finally {

        }
    }

    public void update(Income_Expense income_expense) {
        SQLiteDatabase database = db.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put(column_income_expense_name,income_expense.getName());
            values.put(column_income_expense_description,income_expense.getDescription());
            values.put(column_income_expense_create_date,income_expense.getDate().getTime());
            values.put(column_flag,income_expense.getFlag());
            values.put(column_income_expense_amount,income_expense.getMoney());
            values.put(Column_income_expense_category_id,income_expense.getCategory_id());
            database.update(table_income_expense,values , column_income_expense_id + " = ? ",new String[]{String.valueOf(income_expense.getId())});

        }catch(Exception e){

        }
    }

    @Override
    public void detele(int income_expense_id) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        try{
            database.delete(table_income_expense,column_income_expense_id + " = ?",new String[]{String.valueOf(income_expense_id)});
            database.setTransactionSuccessful();
        }catch(Exception e){
            Log.e("Delete er",e.getMessage());
        }finally {
            database.endTransaction();
        }
    }
}
