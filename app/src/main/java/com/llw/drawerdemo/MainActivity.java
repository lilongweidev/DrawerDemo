package com.llw.drawerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;//滑动菜单
    private Toolbar toolbar;//工具栏
    private NavigationView navView;//导航视图
    private RecyclerView rvMenu;//列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明状态栏
        transparentStatusBar();

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        navView = findViewById(R.id.nav_view);
        //工具栏按钮点击
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        //获取头部视图
        View headerView = navView.getHeaderView(0);
        //头像点击
        headerView.findViewById(R.id.iv_avatar).setOnClickListener(v -> showMsg("头像"));
        //导航菜单点击
        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_setting:
                    showMsg("设置");
                    break;
                case R.id.item_logout:
                    showMsg("退出");
                    break;
                default:
                    break;
            }
            //关闭滑动菜单
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        //绑定列表控件
        rvMenu = headerView.findViewById(R.id.rv_menu);
        //显示菜单列表
        showMenuList();
    }

    /**
     * 显示菜单列表
     */
    private void showMenuList() {
        //解析JSON数据
        MenuResponse menuResponse = new Gson().fromJson(Contanst.JSON, MenuResponse.class);
        if (menuResponse.getCode() == Contanst.SUCCESS) {
            //为空处理
            if(menuResponse.getData() ==null){
                showMsg("返回菜单数据为空");
                return;
            }
            List<MenuResponse.DataBean> data = menuResponse.getData();
            //设置适配器的布局和数据源
            MenuAdapter menuAdapter = new MenuAdapter(R.layout.item_menu, data);
            //item点击事件
            menuAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                    showMsg(data.get(position).getName());
                    //关闭滑动菜单
                    drawerLayout.closeDrawer(GravityCompat.START);
            });
            //设置线性布局管理器，默认是纵向
            rvMenu.setLayoutManager(new LinearLayoutManager(this));
            //设置适配器
            rvMenu.setAdapter(menuAdapter);
            //添加item的分隔线
            rvMenu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        } else {
            //错误提示
            showMsg(menuResponse.getMsg());
        }
    }


    /**
     * 透明状态栏
     */
    private void transparentStatusBar() {
        //改变状态栏颜色为透明
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * Toast提示
     *
     * @param msg 内容
     */
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}