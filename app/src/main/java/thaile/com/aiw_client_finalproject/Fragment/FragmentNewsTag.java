package thaile.com.aiw_client_finalproject.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import thaile.com.aiw_client_finalproject.Adapter.AdapterNewsTag;
import thaile.com.aiw_client_finalproject.AppHelper;
import thaile.com.aiw_client_finalproject.NewsObj;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 11/25/2016.
 */

public class FragmentNewsTag extends Fragment implements AdapterView.OnItemClickListener {
    private Context mContext;
    private View mView;
    private ProgressBar progressBar;
    private TextView txtvTitle;
    private ListView listView;
    private AdapterNewsTag adapter;
    private List<NewsObj> listNews;
    private String tagName;

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
        mView = inflater.inflate(R.layout.fragment_tag, container, false);
        initView();
        initData();
        return mView;
    }

    private void initData() {
        tagName = this.getArguments().getString(AppHelper.KEY_TAG_NAME);

        txtvTitle.setText("Tin tức về chủ đề \""+tagName+"\"");

        new MyAsync().execute(AppHelper.TAG_URL + AppHelper.encodeInput(tagName));
    }

    private void initView() {
        txtvTitle = (TextView) mView.findViewById(R.id.title);
        listView = (ListView) mView.findViewById(R.id.lv_listnewstag);
        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsObj obj = listNews.get(position);
        AppHelper.sendData(mContext, obj);
    }

    private class MyAsync extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return AppHelper.readDataFromUrl(params[0]);
        }

        @Override
        protected void onPostExecute(String strResult) {
            super.onPostExecute(strResult);
            listNews = AppHelper.parseToList(strResult);
            if (listNews.size() == 0){
                return;
            } else {
                adapter = new AdapterNewsTag(listNews, mContext);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
