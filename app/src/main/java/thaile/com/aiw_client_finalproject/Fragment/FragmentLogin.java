package thaile.com.aiw_client_finalproject.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import thaile.com.aiw_client_finalproject.Activity.LoginActivity;
import thaile.com.aiw_client_finalproject.AppHelper;
import thaile.com.aiw_client_finalproject.R;
import thaile.com.aiw_client_finalproject.UserObj;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Thai Le on 12/10/2016.
 */

public class FragmentLogin extends Fragment implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private EditText edt_username;
    private EditText edt_password;
    private ProgressBar progressBar;
    private UserObj userObj;
    private Button btn_login;
    private CheckBox checkBox;
    private Button btn_register;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);

        initView();
        return mView;
    }

    private void initView() {
        btn_register = (Button)mView.findViewById(R.id.btn_register);
        progressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        edt_username = (EditText)mView.findViewById(R.id.edt_username);
        edt_password = (EditText) mView.findViewById(R.id.edt_password);
        btn_login = (Button) mView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String inputName = edt_username.getText().toString();
                String inputPass = edt_password.getText().toString();
                sendData(inputName, inputPass);
                break;

            case R.id.btn_register:
                ((LoginActivity) mContext).showRegister();
            default:
                break;
        }
    }
    private void sendData(final String username, final String password) {
        progressBar.setVisibility(View.VISIBLE);
        // btn_login.setEnabled(false);
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                AppHelper.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String strResult = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            updateDisplay(strResult);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return  params;
            }
        };

        queue.add(stringRequest);
    }

    private void updateDisplay(String strResult) {
        progressBar.setVisibility(View.INVISIBLE);
        userObj = AppHelper.paraseUserInforToJson(strResult);
        if (userObj == null){
            AppHelper.showToast(mContext, "Tên tài khoản và mật khẩu của bạn chưa đúng");
            btn_login.setEnabled(true);
        } else {
            AppHelper.showToast(mContext, "Đăng nhập thành công");
            SharedPreferences preferences = mContext.getSharedPreferences(AppHelper.NAME_SHAREDPREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(AppHelper.KEY_SHAREDPREFERENCES, strResult);
            editor.commit();
            ((Activity)mContext).finish();
        }
    }
}
