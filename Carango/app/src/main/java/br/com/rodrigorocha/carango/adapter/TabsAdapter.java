package br.com.rodrigorocha.carango.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.rodrigorocha.carango.fragments.CarrosFragment;

/**
 * Created by rm48236 on 19/10/2016.
 */
public class TabsAdapter extends FragmentStatePagerAdapter {

    public static final int TOTAL_TABS = 2 ;
    public TabsAdapter (FragmentManager fm) {
        super(fm);
    }

    //Nesse método definimos qual fragment deverá ser exibido no ViewPager
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle() ;
        if (position == 0 ) {
            args.putInt( "tipo" , 1 ) ;
        } else if (position == 1 ) {
            args.putInt( "tipo" , 2 ) ;
        }
        Fragment f = new CarrosFragment();
        f.setArguments(args) ;
        return f;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
