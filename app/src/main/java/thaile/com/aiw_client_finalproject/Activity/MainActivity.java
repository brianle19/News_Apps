package thaile.com.aiw_client_finalproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import thaile.com.aiw_client_finalproject.AppHelper;
import thaile.com.aiw_client_finalproject.MyPagerAdapter;
import thaile.com.aiw_client_finalproject.R;
import thaile.com.aiw_client_finalproject.UserObj;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager pager;
    private TabLayout tab;
    private MyPagerAdapter myPagerAdapter;
    private ImageView img_home;
    private Button btn_movelogin;
    private TextView txtv_hello_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        txtv_hello_user = (TextView) findViewById(R.id.txtv_hello_user);
        btn_movelogin = (Button) findViewById(R.id.btn_movelogin);
        img_home = (ImageView) findViewById(R.id.img_home);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        tab = (TabLayout) findViewById(R.id.mytab);
        pager = (ViewPager) findViewById(R.id.myviewpager);
        pager.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(pager);

        img_home.setOnClickListener(this);
        btn_movelogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_home:
                AppHelper.backToHome(this);
                break;
            case R.id.btn_movelogin:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.txtv_hello_user:
                //TO DO SOMETHING
            default:
                break;
        }

    }

    private void initUserData() {
        SharedPreferences preferences = getSharedPreferences(AppHelper.NAME_SHAREDPREFERENCES, MODE_PRIVATE);
        String mJson = preferences.getString(AppHelper.KEY_SHAREDPREFERENCES, "");
        UserObj userObj = AppHelper.paraseUserInforToJson(mJson);
        if (userObj != null) {
            txtv_hello_user.setVisibility(View.VISIBLE);
            txtv_hello_user.setText("Xin chào, \n" + userObj.getFullname());
            btn_movelogin.setVisibility(View.INVISIBLE);
        } else {
            btn_movelogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserData();
    }

}
