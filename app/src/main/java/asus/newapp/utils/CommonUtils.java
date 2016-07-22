package asus.newapp.utils;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import asus.newapp.activity.MainActivity;

/**
 * Created by asus on 2016/7/21.
 */
public class CommonUtils extends FragmentActivity{

    public static <T extends View> void invertVisible(T t){
        if(t.getVisibility()==View.GONE){
            t.setVisibility(View.VISIBLE);
        }else if(t.getVisibility() == View.VISIBLE){
            t.setVisibility(View.GONE);
        }
    }

    //fragments.addAll(Arrays.asList(mf, sf, tf, df));
    public static <T extends Object> void addAll(List list, T...t){
        list.addAll(Arrays.asList(t));
    }


}
