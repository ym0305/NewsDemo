package com.example.yamin.jsontest.aty;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yamin.jsontest.JsonDate;
import com.example.yamin.jsontest.adapter.NewsAdapter;
import com.example.yamin.jsontest.R;
import com.example.yamin.jsontest.RecyclerViewDivider;
import com.example.yamin.jsontest.util.Util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.yamin.jsontest.JsonDate.newsList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private Button navButton;
    private ImageButton setting;
    private RecyclerView recyclerView;
    private JsonDate jsonDate = new JsonDate();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private static final String TAG = "MainActivity";
    private SwipeRefreshLayout swip;
    public String url;
    public String type;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_button:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.right_button:
                Intent intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        change("top","头条");

        navButton.setOnClickListener(this);
        setting.setOnClickListener(this);

        swip.setColorSchemeResources(R.color.colorPrimary);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsList.clear();
                getJsonDate(url);
                swip.setRefreshing(false);

            }
        });


        Log.d(TAG, "onCreate: " + url);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.top:
                        type = "top";
                        change(type,"头条");
                        break;
                    case R.id.shehui:
                        type = "shehui";
                        change(type,"社会");
                        break;
                    case R.id.guonei:
                        type = "guonei";
                        change(type,"国内");
                        break;
                    case R.id.guoji:
                        type = "guoji";
                        change(type,"国际");
                        break;
                    case R.id.yule:
                        type = "yule";
                        change(type,"娱乐");
                        break;
                    case R.id.tiyu:
                        type = "tiyu";
                        change(type,"体育");
                        break;
                    case R.id.junshi:
                        type = "junshi";
                        change(type,"军事");
                        break;
                    case R.id.keji:
                        type = "keji";
                        change(type,"科技");
                        break;
                    case R.id.caijing:
                        type = "caijing";
                        change(type,"财经");
                        break;
                    case R.id.shishang:
                        type = "shishang";
                        change(type,"时尚");
                        break;
                    default:
                        break;

                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }


    public void init(){

        swip = (SwipeRefreshLayout)findViewById(R.id.swip_flesh);
        title = (TextView)findViewById(R.id.title);
        title.setSelected(true);
        navButton = (Button)findViewById(R.id.left_button);
        setting = (ImageButton)findViewById(R.id.right_button);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        navigationView = (NavigationView)findViewById(R.id.navi_View);
        recyclerView = (RecyclerView)findViewById(R.id.newList);


        LinearLayoutManager linearLayout = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.addItemDecoration(new RecyclerViewDivider(MainActivity.this,LinearLayoutManager.VERTICAL,10, R.color.black));


    }

    public void change(String type , String tTitle){
        newsList.clear();
        url = "http://v.juhe.cn/toutiao/index?type=" + type + "&key=cbde0cd9db0fedef46882d1b6d72755a";
        getJsonDate(url);
        title.setText(tTitle);
    }





    public void getJsonDate(String url){

        Util.sendHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //newsList.add(new News("mei",null,null,null,null));
                        //NewsAdapter adapter = new NewsAdapter(newsList);
                        //recyclerView.setAdapter(adapter);
                        Toast.makeText(MainActivity.this, "MEI WANG LUO", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseDate = response.body().string();

                jsonDate.parseJSONDate(responseDate);

                Log.d(TAG, "onCreate: test allo");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        NewsAdapter adapter = new NewsAdapter(newsList);
                        recyclerView.setAdapter(adapter);
                    }
                });

            }
        });
    }


}
