package thaile.com.aiw_client_finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thaile.com.aiw_client_finalproject.NewsObj;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 12/16/2016.
 */

public class AdapterRelatedArticles extends BaseAdapter {
    private List<NewsObj> newsObjList;
    private Context mContext;
    private LayoutInflater inflater;

    public AdapterRelatedArticles(Context mContext,List<NewsObj> newsObjList){
        this.newsObjList = newsObjList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return newsObjList.size();
    }

    @Override
    public NewsObj getItem(int position) {
        return newsObjList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_relatedarticles, parent, false);
            viewHolder.txtv_title = (TextView) convertView.findViewById(R.id.txtv_title);
            viewHolder.img_lastest_icon = (ImageView) convertView.findViewById(R.id.img_lastest_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewsObj obj = newsObjList.get(position);
        viewHolder.txtv_title.setText(obj.getTitleNews());
        return convertView;
    }

    public class ViewHolder{
        TextView txtv_title;
        ImageView img_lastest_icon;
    }
}
