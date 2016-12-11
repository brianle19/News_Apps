package thaile.com.aiw_client_finalproject.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

/**
 * Created by Thai Le on 12/10/2016.
 */

public class FragmentRegister extends Fragment implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private EditText edt_fullname, edt_dob, edt_username, edt_password, edt_checkpass, edt_address, edt_phone, edt_email;
    private Button btn_register_confirm;
    private String fullname, email, address, dob, username, password, checkpassword, phone;

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
        mView = inflater.inflate(R.layout.fragment_register, container, false);
        initView();
        return mView;
    }

    private void initView() {
        edt_email = (EditText) mView.findViewById(R.id.edt_email);
        edt_phone = (EditText) mView.findViewById(R.id.edt_phone);
        edt_fullname = (EditText) mView.findViewById(R.id.edt_fullname);
        edt_address = (EditText) mView.findViewById(R.id.edt_address);
        edt_password = (EditText) mView.findViewById(R.id.edt_password);
        edt_checkpass = (EditText) mView.findViewById(R.id.edt_checkpass);
        edt_dob = (EditText) mView.findViewById(R.id.edt_dob);
        edt_username = (EditText) mView.findViewById(R.id.edt_username);
        btn_register_confirm = (Button) mView.findViewById(R.id.btn_register_confirm);
        btn_register_confirm.setOnClickListener(this);

    }

    public void initData(){
        fullname = edt_fullname.getText().toString();
        dob = edt_dob.getText().toString();
        address = edt_address.getText().toString();
        phone = edt_phone.getText().toString();
        username = edt_username.getText().toString();
        password = edt_password.getText().toString();
        checkpassword = edt_checkpass.getText().toString();
        email = edt_email.getText().toString();

        checkExistData(username, password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_confirm:

                initData();

                break;

            default:
                break;
        }
    }

    private void sendData(){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                AppHelper.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        switch (response) {
                            case "Success":
                                Log.e("REGISS", response);
                                updateDisplaySuccess();
                                break;
                            case "Fail":
                                updateDisplayFail();
                                break;
                            default:
                                break;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TO DO
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("fullname", fullname);
                params.put("username",username);
                params.put("password", password);
                params.put("phone", phone);
                params.put("dob", dob);
                params.put("email", email);
                params.put("address", address);
                return params;
            }
        };

        queue.add(stringRequest);

    }

    private void updateDisplaySuccess(){
        AppHelper.showToast(mContext, "Đăng kí thành công!");
        Intent intent = new Intent(AppHelper.ACTION);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        mContext.sendBroadcast(intent);
        ((LoginActivity)getActivity()).removeRegister();
    }

    private void updateDisplayFail(){
        AppHelper.showToast(mContext, "Đăng kí thất bại, hãy thử lại!");
        edt_address.setText("");
        edt_phone.setText("");
        edt_checkpass.setText("");
        edt_password.setText("");
        edt_dob.setText("");
        edt_email.setText("");
        edt_fullname.setText("");
    }

    private void checkExistData(final String inputUsername, final String inputPassword) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                AppHelper.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String strResult = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            Log.e("MEOO", strResult+"---");
                            if (strResult.equals("Fail")){
                                Log.e("REGISS", "ok đấy");
                                btn_register_confirm.setEnabled(false);
                                sendData();
                            } else {
                                AppHelper.showToast(mContext, "Tài khoản đã tồn tại");
                                btn_register_confirm.setEnabled(true);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("username", inputUsername);
                params.put("password", inputPassword);
                return  params;
            }
        };

        queue.add(stringRequest);
    }
}
