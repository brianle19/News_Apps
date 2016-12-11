package thaile.com.aiw_client_finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import thaile.com.aiw_client_finalproject.Activity.ContentActivity;
import thaile.com.aiw_client_finalproject.Activity.MainActivity;
import thaile.com.aiw_client_finalproject.Fragment.FragmentNewsTag;

/**
 * Created by Thai Le on 11/23/2016.
 */

public abstract class AppHelper {
    public static final String KEY_OBJECT = "KEY_OBJECT";
    public static String KEY_TAG_NAME = "KEY_TAG_NAME";
    public static String BASE_URL = "http://192.168.100.18:8080/AIW_FinalProject_Server/getdata/get8lastestnews?num=";

    public static String GET8NEWS_URL= "http://192.168.100.18:8080/AIW_FinalProject_Server/getdata/get8lastestnews?num=";
    public static String TAG_URL= "http://192.168.100.18:8080/AIW_FinalProject_Server/getdata/getnewsbytag?tag=";
    public static String CATEGORY_URL= "http://192.168.100.18:8080/AIW_FinalProject_Server/getdata/getnewsbycategory?category=";
    public static String COMMENT_URL= "http://192.168.100.18:8080/AIW_FinalProject_Server/getdata/getcomments?id=";
    public static String LOGIN_URL = "http://192.168.100.18:8080/AIW_FinalProject_Server/postdata/login";
    public static String REGISTER_URL = "http://192.168.100.18:8080/AIW_FinalProject_Server/postdata/register";
    public static String ADD_COMMENT_URL = "http://192.168.100.18:8080/AIW_FinalProject_Server/postdata/comment";

    public static final String ACTION = "ACTION_RECEIVER_OBJECT";
    public static final String NAME_SHAREDPREFERENCES = "NAME_SHAREDPREFERENCES";
    public static final String KEY_SHAREDPREFERENCES = "KEY_SHAREDPREFERENCES";


    public static String readDataFromUrl(String mLink) {
        BufferedReader reader = null;
        try {
            URL url = new URL(mLink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuffer strBuffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                strBuffer.append(line);
            }
            return strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
    }


    public static List<NewsObj> parseToList(String mString){
        List<NewsObj> arrayList = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(mString);
                JSONArray jsonArray = jsonObject.getJSONArray("Newspaper");
                for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String contentNews = lineSeparator(object.getString("contentNews"));
                        String imgNews = lineSeparator(object.getString("imgNews"));
                        String dateNews = object.getString("dateNews");
                        String idNews = object.getString("idNews");
                        String titleNews = object.getString("titleNews");
                        String category = object.getString("category");
                        String authorNews = lineSeparator(object.getString("authorNews"));
                        String shortIntro = object.getString("shortIntro");

                        JSONObject arrTag = object.getJSONObject("tags");
                        String tags[] = new String[arrTag.length()];
                        for (int j = 0; j < tags.length; j++) {
                            tags[j] = arrTag.getString("tag" + j);
                        }
                        arrayList.add(new NewsObj(Integer.parseInt(idNews), titleNews, shortIntro, category, dateNews, authorNews, imgNews,
                                contentNews,
                                tags));
                    }
            } catch (Exception e) {
                Log.e("EX", e.toString());
            }
        return arrayList;
    }

    public static List<CommentObj> parseCommentToJson(String mString){
        List<CommentObj>  arrayList = new ArrayList<>();
        if (mString == null){
            return null;
        } else {
            try {
                JSONObject jsonObject = new JSONObject(mString);
                JSONArray jsonArray = jsonObject.getJSONArray("Comments");
                if (jsonArray.length() == 0){
                    return null;
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String username = object.getString("username");
                        String content = object.getString("content");
                        String datetime = object.getString("datetime");

                        arrayList.add(new CommentObj(username, content, datetime));
                    }
                }
            } catch (JSONException e) {
            }
        }
        return arrayList;
    }

    public static UserObj paraseUserInforToJson(String mString){
        UserObj userObj = null;
            try {
                JSONObject jsonObject = new JSONObject(mString);
                String usname = jsonObject.getString("usname");
                String fullname = jsonObject.getString("fullname");
                String address = jsonObject.getString("address");
                String dob = jsonObject.getString("dob");
                String email = jsonObject.getString("email");
                String phone = jsonObject.getString("phone");
                int id = jsonObject.getInt("id");
                String password = jsonObject.getString("password");

                userObj = new UserObj(id, fullname, usname, password, dob, address, email, phone);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("PARSE USER", e.toString());
            }

        return userObj;
    }

    public static void sendData(Context mContext, NewsObj obj){

        Intent intent = new Intent(mContext.getApplicationContext(), ContentActivity.class);
        intent.putExtra(AppHelper.KEY_OBJECT, obj);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void backToHome(Context mContext){

        Intent intent = new Intent(mContext.getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        ((Activity) mContext).finish();
    }

    public static void moveToTagFragment(FragmentManager fragmentManager, String tagName){
        Bundle bundle = new Bundle();
        bundle.putString(AppHelper.KEY_TAG_NAME, tagName);
        FragmentNewsTag fragmentNewsTag = new FragmentNewsTag();
        fragmentNewsTag.setArguments(bundle);

        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_content, fragmentNewsTag);
        fragmentTransaction.addToBackStack("tagfragment");
        fragmentTransaction.commit();
    }

    public static String lineSeparator(String str){
        return str.replace("\\n", "\n");
    }

    public static String encodeInput(String str){
        String getInputData = null;
        try {
            getInputData = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return getInputData;
    }

    public static void showToast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
