package thaile.com.aiw_client_finalproject.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import thaile.com.aiw_client_finalproject.Fragment.FragmentLogin;
import thaile.com.aiw_client_finalproject.Fragment.FragmentRegister;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 12/1/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private FragmentLogin fragmentLogin;
    private FragmentRegister fragmentRegister;
    private TextView txtv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        txtv_title = (TextView) findViewById(R.id.txtv_type);
        fragmentLogin = new FragmentLogin();
        fragmentRegister = new FragmentRegister();

        showLogin();
    }

    public void showLogin(){
        txtv_title.setText("Đăng nhập");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_layout, fragmentLogin)
                .addToBackStack("")
                .commit();
    }

    public void showRegister(){
        txtv_title.setText("Đăng kí");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_layout, fragmentRegister)
                .addToBackStack("")
                .commit();
    }

    public void removeRegister(){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragmentRegister)
                .commit();

        showLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("DES", "desLogin");
    }
}
