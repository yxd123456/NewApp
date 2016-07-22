package asus.newapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import asus.newapp.R;
import asus.newapp.fragment.DetailFragment;
import asus.newapp.fragment.MainFragment;
import asus.newapp.fragment.SecondFragment;
import asus.newapp.fragment.ThirdFragment;
import asus.newapp.utils.CommonUtils;
import asus.newapp.utils.FragmentUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 托管所有Fragment的Activity，程序的入口
 */
public class MainActivity extends CommonUtils {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static int BACKFLAG = 1;

    public MainFragment mf;
    public SecondFragment sf;
    public ThirdFragment tf;
    public DetailFragment df;
    public FragmentUtils fragmentUtils;

    @InjectView(R.id.rg_bottom)
    public RadioGroup rgBottom;//底部切换栏
    //收藏所有Fragment的集合
    public List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //切换Fragment的RadioButton
    public void rb1(View v) {
        fragmentUtils.switchFragment(mf);
    }
    public void rb2(View v) {
        fragmentUtils.switchFragment(sf);
    }
    public void rb3(View v) {
        fragmentUtils.switchFragment(tf);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();//将最上层Fragment弹出回退栈
        switch (BACKFLAG){//根据回退标志来决定返回到哪个Fragment
            case 1:fragmentUtils.switchFragment(mf);break;
            case 2:fragmentUtils.switchFragment(sf);break;
            case 3:fragmentUtils.switchFragment(tf);break;
        }
        invertVisible(rgBottom);//重新显示底部切换栏
    }

    private void init() {
        ButterKnife.inject(this);
        mf = new MainFragment();
        sf = new SecondFragment();
        tf = new ThirdFragment();
        df = new DetailFragment();
        addAll(fragments, mf, sf, tf, df);
        fragmentUtils = new FragmentUtils(this, fragments, R.id.fragment_content);
    }

}
