package com.andytenholder.comradetrump;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Andy Tenholder on 3/28/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;


    //,"Grafik", "Apparatchiks"
    private String tabTitles[] = new String[] { "News","Timeline"};

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View view= LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView textView= (TextView) view.findViewById(R.id.custom_tab_tv);
        textView.setText(getPageTitle(position));
        Typeface backInUSSRTypeface = Typefaces.get(context,"Back_In_the_USSR_DL_k");
        textView.setTypeface(backInUSSRTypeface);

        return view;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewsFragment();
        } //else if (position == 1) {
            //return new ApparatchiksFragment();
        else {
            return new TimelineFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
