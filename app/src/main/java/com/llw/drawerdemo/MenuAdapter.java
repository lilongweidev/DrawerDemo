package com.llw.drawerdemo;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * 菜单列表适配器
 *
 * @author lonel
 */
public class MenuAdapter extends BaseQuickAdapter<MenuResponse.DataBean, BaseViewHolder> {

    private int[] iconArray = {R.drawable.icon_friend, R.drawable.icon_wallet, R.drawable.icon_location
            , R.drawable.icon_phone, R.drawable.icon_email, R.drawable.icon_share, R.drawable.icon_send};

    public MenuAdapter(int layoutResId, @Nullable List<MenuResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuResponse.DataBean item) {
        //获取控件id
        ImageView ivMenuIcon = helper.getView(R.id.iv_menu_icon);
        //设置图标
        ivMenuIcon.setImageResource(iconArray[helper.getAdapterPosition()]);
        helper.setText(R.id.tv_menu_name, item.getName());
        //添加点击事件
        helper.addOnClickListener(R.id.item_menu);
    }
}
