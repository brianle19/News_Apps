package thaile.com.aiw_client_finalproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import thaile.com.aiw_client_finalproject.Activity.MainActivity;

/**
 * Created by Thai Le on 12/12/2016.
 */

public class DialogInfor extends Dialog implements View.OnClickListener {
    private Button btn_editinfor, btn_changepass, btn_exit, btn_logout;
    private Context mContext;
    private TextView txtv_fullname, txtv_dob, txtv_username, txtv_email, txtv_phone, txtv_address;
    private UserObj userObj;
    public DialogInfor(Context context, UserObj userObj) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_infor);
        this.mContext = context;
        this.userObj = userObj;
        initView();
        initData();
        setCancelable(false);
        setOwnerActivity((Activity) context);
    }

    private void initView() {
        btn_changepass = (Button)findViewById(R.id.btn_changepass);
        btn_editinfor = (Button)findViewById(R.id.btn_editinfor);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        txtv_address = (TextView) findViewById(R.id.txtv_address);
        txtv_fullname = (TextView) findViewById(R.id.txtv_fullname);
        txtv_email = (TextView) findViewById(R.id.txtv_email);
        txtv_username = (TextView) findViewById(R.id.txtv_username);
        txtv_phone = (TextView) findViewById(R.id.txtv_phone);
        txtv_dob = (TextView) findViewById(R.id.txtv_dob);


        btn_exit.setOnClickListener(this);
        btn_editinfor.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_changepass.setOnClickListener(this);

    }

    private void initData(){
        txtv_address.setText(userObj.getUserAddress());
        txtv_dob.setText(userObj.getDob());
        txtv_fullname.setText(userObj.getFullname());
        txtv_phone.setText(userObj.getUserPhone());
        txtv_email.setText(userObj.getUserEmail());
        txtv_username.setText(userObj.getUserName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_changepass:
                break;
            case R.id.btn_editinfor:
                break;
            case R.id.btn_logout:
                SharedPreferences preferences = mContext.getSharedPreferences(AppHelper.NAME_SHAREDPREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                AppHelper.showToast(mContext, "Đăng xuất thành công");
                ((MainActivity)getOwnerActivity()).initUserData();
                dismiss();

                break;
            case R.id.btn_exit:
                ((MainActivity)getOwnerActivity()).initUserData();
                dismiss();
                break;

            default:
                break;
        }
    }
}
