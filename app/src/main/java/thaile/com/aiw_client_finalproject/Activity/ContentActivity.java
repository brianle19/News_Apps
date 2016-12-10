package thaile.com.aiw_client_finalproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thaile.com.aiw_client_finalproject.Adapter.AdapterComment;
import thaile.com.aiw_client_finalproject.Adapter.AdapterTags;
import thaile.com.aiw_client_finalproject.AppHelper;
import thaile.com.aiw_client_finalproject.CommentObj;
import thaile.com.aiw_client_finalproject.Fragment.FragmentNewsTag;
import thaile.com.aiw_client_finalproject.NewsObj;
import thaile.com.aiw_client_finalproject.R;
import thaile.com.aiw_client_finalproject.UserObj;

public class ContentActivity extends AppCompatActivity implements AdapterTags.CallFragmentNewsTag, View.OnClickListener {
    private TextView txtv_title, txtv_datetime, txtv_shortintro, txtv_maincontent, txtv_author;
    private ImageView img_news;
    private NewsObj newsObj;
    private List<String> listTag;
    private AdapterTags adapterTags;
    private RecyclerView recyclerView;
    private AdapterComment adapterComment;
    private RecyclerView recyclerViewComment;
    private List<CommentObj> listComment;
    private TextView txtvType;
    private ImageView img_home;
    private LinearLayoutManager layoutManager;
    private Button btn_movelogin;
    private TextView txtv_hello_user;
    private UserObj userObj;
    private Button btn_sendcmt;
    private EditText edt_contentcmt;
    private String strCmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initView();
        initData();
        assignData();

    }

    private void assignData() {
        listTag = Arrays.asList(newsObj.getTagNews());
        txtv_title.setText(newsObj.getTitleNews());
        txtv_shortintro.setText(newsObj.getShortIntro());
        txtv_author.setText(newsObj.getAuthorNews());
        txtv_datetime.setText(newsObj.getDateNews());
        txtv_maincontent.setText(newsObj.getContentNews());
        txtvType.setText(newsObj.getCategory());
        Picasso.with(this).load(newsObj.getImgNews()).into(img_news);
        adapterTags = new AdapterTags(listTag, this);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTags);

        recyclerViewComment.setNestedScrollingEnabled(true);

        recyclerViewComment.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        adapterTags.setShowFragmentNewsTag(this);


    }

    private void initData() {
        Intent intent = getIntent();
        newsObj = (NewsObj) intent.getSerializableExtra(AppHelper.KEY_OBJECT);
    }

    private void initView() {
        edt_contentcmt = (EditText) findViewById(R.id.edt_contentcmt);
        btn_sendcmt = (Button) findViewById(R.id.btn_sendcmt);
        txtv_hello_user = (TextView) findViewById(R.id.txtv_hello_user);
        btn_movelogin = (Button) findViewById(R.id.btn_movelogin);
        img_home = (ImageView) findViewById(R.id.img_home);
        txtvType = (TextView) findViewById(R.id.txtv_type);
        recyclerViewComment = (RecyclerView) findViewById(R.id.recyclerview_comment);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_tag);
        txtv_datetime = (TextView)findViewById(R.id.txtv_datetime);
        txtv_maincontent = (TextView)findViewById(R.id.txtv_maincontent);
        txtv_shortintro = (TextView)findViewById(R.id.txtv_shortintro);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        img_news = (ImageView)findViewById(R.id.img_news);
        txtv_author = (TextView)findViewById(R.id.txtv_author);

        img_home.setOnClickListener(this);
        btn_sendcmt.setOnClickListener(this);
        btn_movelogin.setOnClickListener(this);

        btn_sendcmt.setEnabled(false);
        edt_contentcmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                strCmt = s.toString();
                if (strCmt.isEmpty()){
                    btn_sendcmt.setEnabled(false);
                } else {
                    btn_sendcmt.setEnabled(true);
                }
            }
        });

    }

    @Override
    public void showFragment(String tagName) {
        AppHelper.moveToTagFragment(getSupportFragmentManager(),tagName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentNewsTag fragmentNewsTag = new FragmentNewsTag();
        if (fragmentNewsTag.isVisible()) {
            Log.e("Meo", "BACK");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadComment();
    }

    private void loadComment() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                AppHelper.COMMENT_URL+newsObj.getIdNews(),
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
                AppHelper.showToast(ContentActivity.this, "Lỗi");
            }
        });

        queue.add(stringRequest);
    }

    private void updateDisplay(String strResult) {
        listComment = AppHelper.parseCommentToJson(strResult);
        if (listComment == null){
            return;
        } else {
            LinearLayoutManager layoutManagerComment = new LinearLayoutManager(ContentActivity.this);
            layoutManagerComment.setOrientation(LinearLayoutManager.VERTICAL);

            adapterComment = new AdapterComment(ContentActivity.this, listComment);
            recyclerViewComment.setLayoutManager(layoutManagerComment);
            recyclerViewComment.setAdapter(adapterComment);
            adapterComment.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_home:
                AppHelper.backToHome(this);
                break;

            case R.id.btn_sendcmt:
                if (userObj == null){
                    AppHelper.showToast(this, "Vui lòng đăng nhập !");
                } else {
                    btn_sendcmt.setEnabled(false);
                    addComment(strCmt);
                }
                break;

            case R.id.btn_movelogin:
                Intent intent = new Intent();
                intent.setClass(ContentActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void addComment(final String cmtContent){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                AppHelper.ADD_COMMENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            btn_sendcmt.setEnabled(true);
                            edt_contentcmt.setText("");

                            if (listComment != null){
                                listComment.clear();
                            }
                            loadComment();
                        } else if (response.equals("Fail")) {
                            AppHelper.showToast(ContentActivity.this, "Lỗi");
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppHelper.showToast(ContentActivity.this, "Lỗi");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("iduser", String.valueOf(userObj.getId()));
                params.put("idnews", String.valueOf(newsObj.getIdNews()));
                params.put("content", cmtContent);
                return  params;
            }
        };

        queue.add(stringRequest);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        initUserData();
    }

    private void initUserData() {
        SharedPreferences preferences = getSharedPreferences(AppHelper.NAME_SHAREDPREFERENCES, MODE_PRIVATE);
        String mJson = preferences.getString(AppHelper.KEY_SHAREDPREFERENCES, "");
        userObj = AppHelper.paraseUserInforToJson(mJson);
        if (userObj != null) {
            btn_movelogin.setVisibility(View.INVISIBLE);
            txtv_hello_user.setVisibility(View.VISIBLE);
            txtv_hello_user.setText("Xin chào, \n" + userObj.getFullname());
        }
    }
}
