package com.poly.myasm.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.poly.myasm.Utilities.Constaints.*;

public class MyDatabase extends SQLiteOpenHelper {
    private static MyDatabase dbInstance;
    public static synchronized MyDatabase getInstance(Context ctx){
        if(dbInstance == null){
            dbInstance = new MyDatabase(ctx);

        } return dbInstance;
    }
    public MyDatabase(Context context) {
        super(context, db_Name,null,db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "create table if not exists  "
                +table_Category
                +"("
                + column_Category_id + " integer primary key autoincrement,"
                + column_Category_Name + " text"
                +")";
        db.execSQL(q);
        q = "create table if not exists  "
                +table_income_expense
                +"("
                + column_income_expense_id+ " integer primary key autoincrement,"
                + column_income_expense_name+ " text,"
                + column_income_expense_description + " text,"
                + column_income_expense_amount + " real,"
                + column_income_expense_create_date + " integer,"
                + Column_income_expense_category_id + " integer,"
                + column_flag + " text,"
                + "foreign key"
                + "("
                +  Column_income_expense_category_id
                + ")"
                + "references "
                + table_Category + "(" + column_Category_id + ")"
                + ")";
        db.execSQL(q);
    q = "insert into " + table_Category + " ( " + column_Category_Name +" ) values ('Shopping'), ('CLOTHES'),('FOOD'),('DRINKING') ";
     db.execSQL(q);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int upgrade = oldVersion + 1;
        while(upgrade <= newVersion)
        {
            switch(upgrade)
            {
                case 2:

                    break;
                case 3:
                    break;
                default:
                    break;
            }upgrade++;
        }
    }
}
