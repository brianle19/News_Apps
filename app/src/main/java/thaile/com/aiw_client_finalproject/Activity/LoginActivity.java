package thaile.com.aiw_client_finalproject.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import thaile.com.aiw_client_finalproject.R;
import thaile.com.aiw_client_finalproject.UserObj;

/**
 * Created by Thai Le on 12/1/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_username;
    private EditText edt_password;
    private ProgressBar progressBar;
    private UserObj userObj;
    private Button btn_login, btn_movelogin;
    private CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
