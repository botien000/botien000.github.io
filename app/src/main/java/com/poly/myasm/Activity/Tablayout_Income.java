package com.poly.myasm.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.poly.myasm.Adapter.MyFragmentPagerAdapter;
import com.poly.myasm.R;

public class Tablayout_Income extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_income_layout);
        viewPager2 = findViewById(R.id.my_viewpaper);
        tabLayout = findViewById(R.id.my_tab);
        viewPager2.setAdapter( new MyFragmentPagerAdapter(this,1));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab( TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setIcon(R.drawable.salary);
                        tab.setText("Loại Thu");
                        break;
                    case 1:
                        tab.setIcon(R.drawable.wallet);
                        tab.setText("Khoản thu");
                        break;
                }
            }
        });tabLayoutMediator.attach();
      /*  viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);
            }
        });*/
    }
    public void clickBack(View view){
        finish();
    }

}
