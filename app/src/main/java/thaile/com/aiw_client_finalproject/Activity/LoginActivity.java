package thaile.com.aiw_client_finalproject.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thaile.com.aiw_client_finalproject.Fragment.FragmentLogin;
import thaile.com.aiw_client_finalproject.Fragment.FragmentRegister;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 12/1/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentLogin fragmentLogin;
    private FragmentRegister fragmentRegister;
    private TextView txtv_title;
    private ImageView img_home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        txtv_title = (TextView) findViewById(R.id.txtv_type);
        img_home = (ImageView) findViewById(R.id.img_home);
        img_home.setOnClickListener(this);
        fragmentLogin = new FragmentLogin();
        fragmentRegister = new FragmentRegister();

        showLogin();
    }

    public void showLogin(){
        txtv_title.setText("Đăng nhập");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_layout, fragmentLogin)
                .commit();
    }

    public void showRegister(){
        txtv_title.setText("Đăng kí");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_layout, fragmentRegister)
                .addToBackStack("")
                .commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_home:
                finish();
                break;
            default:
                break;
        }
    }
}
