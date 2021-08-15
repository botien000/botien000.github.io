package com.poly.myasm.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.poly.myasm.DAO.CategoryDAO;

import com.poly.myasm.DAO.Income_ExpenseDAO;
import com.poly.myasm.DTO.Income_Expense;
import com.poly.myasm.R;

import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
TextView ti,te,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);
        drawerLayout = findViewById(R.id.drawerlayout);
        ti = findViewById(R.id.totalI);
        te = findViewById(R.id.totalE);
        total = findViewById(R.id.total);
        total();
    }
    private void total(){
        double count1 = 0 ,count2 = 0;
        List<Income_Expense> listE = ( new Income_ExpenseDAO(this).get(0));
        List<Income_Expense> listI = ( new Income_ExpenseDAO(this).get(1));
        for(int i = 0;i < listE.size();i++){
            count1+=listE.get(i).getMoney();
        }
        for(int i = 0;i < listI.size();i++){
            count2+=listI.get(i).getMoney();
        }
        ti.setText("+" + NumberFormat.getInstance().format(count2)+ " VND");
        te.setText("-" + NumberFormat.getInstance().format(count1)+ " VND");
        total.setText(NumberFormat.getInstance().format((count2 - count1))+ " VND");
    }

    public void clickMenu(View view){
        openDrawer(drawerLayout);
    }
    public  static void openDrawer(DrawerLayout drawerlayout){
        drawerlayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerlayout){
        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);

        }
    }
    public void clickIncome(View view){
    drawerLayout.closeDrawers();
        
        redirectActivity(MainActivity.this, Tablayout_Income.class);


    }
    public void clickExpenses(View view){
        drawerLayout.closeDrawers();
        redirectActivity(MainActivity.this, Tablayout_Expenses.class);
    }
    public void clickAbout(View view){
        closeDrawer(drawerLayout);
        return;
    }
    public void clickStats(View view){
        drawerLayout.closeDrawers();
        redirectActivity(MainActivity.this, activity_charts.class);
    }
    public void  clickLogout(View view){
        logout(MainActivity.this);
    }
    public static void logout(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Logout");
        //set message
        builder.setMessage("Are you sure you want to logout?");
        //positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss();
            }
        });
        // show dialog
        builder.show();

    }

    public static void redirectActivity(Activity activity, Class aclass) {
            Intent intent = new Intent(activity,aclass);
            activity.startActivity(intent);

    }
}