package thaile.com.aiw_client_finalproject.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 12/10/2016.
 */

public class FragmentRegister extends Fragment implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private EditText edt_fullname, edt_dob, edt_username, edt_password, edt_checkpass, edt_address, edt_phone, edt_email;
    private Button btn_register_confirm;

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
        edt_email = (EditText)mView.findViewById(R.id.edt_email);
        edt_phone= (EditText)mView.findViewById(R.id.edt_phone);
        edt_fullname = (EditText)mView.findViewById(R.id.edt_fullname);
        edt_address = (EditText)mView.findViewById(R.id.edt_address);
        edt_password = (EditText)mView.findViewById(R.id.edt_password);
        edt_checkpass = (EditText)mView.findViewById(R.id.edt_checkpass);
        edt_dob = (EditText)mView.findViewById(R.id.edt_dob);
        edt_username = (EditText)mView.findViewById(R.id.edt_username);
        btn_register_confirm = (Button)mView.findViewById(R.id.btn_register_confirm);
        btn_register_confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_confirm:
                break;

            default:
                break;
        }
    }

    private void sendData(){

    }
}
