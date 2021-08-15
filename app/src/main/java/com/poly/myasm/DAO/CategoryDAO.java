package com.poly.myasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.myasm.DTO.Category;
import com.poly.myasm.Database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.poly.myasm.Utilities.Constaints.*;



public class CategoryDAO implements ICategoryDAO {
    MyDatabase db;
    public CategoryDAO(Context c){
        db = MyDatabase.getInstance(c);
    }

    @Override
    public List<Category> get() {
        List<Category> list = new ArrayList<>();
        String q = "select "+ column_Category_id + ", " + column_Category_Name
                + " from "  + table_Category;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q,null);
        try{
            if(    cursor.moveToFirst()){
                while(cursor.isAfterLast() == false){
                    int id = cursor.getInt(cursor.getColumnIndex(column_Category_id));
                    String name = cursor.getString(cursor.getColumnIndex(column_Category_Name));
                    Category category = new Category(id,name);
                    list.add(category);
                    cursor.moveToNext();
                }
            }
        }catch(Exception e ){
                    Log.e("Get error",e.getMessage());

        }finally {
                if(cursor!= null && !cursor.isClosed()){
                    cursor.close();
                }
        }

        return list;
    }

    public Category get(int _id) {
        Category category = null;
        String q = "select "+ column_Category_id + ", " + column_Category_Name
                + " from "  + table_Category
                + " where " + column_Category_id + " = ?";
                ;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(q,new String[]{_id+""});
        try{
            if(    cursor.moveToFirst()){
                while(cursor.isAfterLast() == false){
                    int id = cursor.getInt(cursor.getColumnIndex(column_Category_id));
                    String name = cursor.getString(cursor.getColumnIndex(column_Category_Name));
                    category = new Category(id,name);

                    cursor.moveToNext();
                }
            }
        }catch(Exception e ){
            Log.e("Get error",e.getMessage());

        }finally {
            if(cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return category;
    }
    public void insert(Category category){
        SQLiteDatabase database = db.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put(column_Category_Name,category.getName());
            database.insertOrThrow(table_Category,null,values);

        }catch(Exception e){
            Log.e("Insert get error",e.getMessage());
        }finally {

        }
    }
    public void update(Category category){
        SQLiteDatabase database = db.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put(column_Category_Name,category.getName());
            database.update(table_Category,values , column_Category_id + " = ? ",new String[]{String.valueOf(category.getId())});


        }catch(Exception e){


        }
    }
    public void delete(int categoryId){
        SQLiteDatabase database = db.getWritableDatabase();

        try{
             database.delete(table_Category,column_Category_id + " = ?",new String[]{String.valueOf(categoryId)});

        }catch(Exception e){
                Log.e("Delete er",e.getMessage());
        }finally {

        }
    }

}
