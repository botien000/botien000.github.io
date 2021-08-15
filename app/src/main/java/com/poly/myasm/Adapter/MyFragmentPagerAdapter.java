package com.poly.myasm.Adapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.myasm.Fragment.Khoanchi_fragment;
import com.poly.myasm.Fragment.Khoanthu_fragment;
import com.poly.myasm.Fragment.Loaichi_fragment;
import com.poly.myasm.Fragment.Loaithu_fragment;

public class MyFragmentPagerAdapter extends FragmentStateAdapter {
    final int TAB_COUNT = 2;
    int in_exx;
    public MyFragmentPagerAdapter(FragmentActivity fa,int in_ex){
        super(fa);
        in_exx = in_ex;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
        if (in_exx == 1) {
            switch (position) {
                case 0:
                    fragmentClass = Loaithu_fragment.class;

                    break;
                case 1:
                    fragmentClass = Khoanthu_fragment.class;
                    break;

            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
            }
            return fragment;
        }
    else{
            switch (position) {
                case 0:
                    fragmentClass = Loaithu_fragment.class;
                    break;
                case 1:
                    fragmentClass = Khoanchi_fragment.class;
                    break;

            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
            }
            return fragment;
    }
    }
    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
