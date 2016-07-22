package asus.newapp.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.Arrays;
import java.util.List;

import asus.newapp.R;
import asus.newapp.activity.MainActivity;
import asus.newapp.adapter.CommonAdapter;
import asus.newapp.adapter.ViewHolder;
import asus.newapp.view.LocalImageHolderView;
import asus.newapp.view.views.StaggeredGridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by asus on 2016/7/19.
 */
public class MainFragment extends BaseFragment {

    @InjectView(R.id.convenientBanner_mainFragment)
    ConvenientBanner banner;//广告栏
    @InjectView(R.id.staggeredGridView1)
    StaggeredGridView gridView;
    private MainActivity activity;

    @Override
    protected void onCreateView(View v) {
        activity = (MainActivity) getActivity();
        dealBanner();
        dealGridView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    /**
     * 处理GridView
     */
    private void dealGridView() {
        gridView.setItemMargin(10); // 设置列表项外边距
        gridView.setPadding(10, 0, 10, 0); // have the margin on the sides as well
        final List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        gridView.setAdapter(new CommonAdapter(getActivity(),
                list,
                R.layout.cell_gridview,
                (holder, position) -> {
                    TextView tv = holder.getView(R.id.tv_cell);
                    ImageView iv = holder.getView(R.id.iv_cell);
                    if(position%2==0){
                        iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                300));
                    }
                    iv.setImageResource(R.mipmap.ic_launcher);
                    tv.setText(list.get(position));
                }));
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    activity.fragmentUtils.switchFragmentWithStack(activity.df);
                    activity.rgBottom.setVisibility(View.GONE);
                    break;
                case 1:
                    ComponentName componentName = new ComponentName(
                            "com.example.myapp",
                            "com.example.myapp.MainActivity");
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    intent.setComponent(componentName);
                    startActivity(intent);
                    break;
            }
        });
    }

    /**
     * 处理广告栏
     */
    private void dealBanner() {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        banner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, Arrays.asList(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.point_unfocued, R.drawable.point_focued})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//              convenientBanner.setManualPageable(false);//设置不能手动影响

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
