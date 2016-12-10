package thaile.com.aiw_client_finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thaile.com.aiw_client_finalproject.NewsObj;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 11/23/2016.
 */

public class AdapterHome extends BaseAdapter {
    private Context mContext;
    private List<NewsObj> mNewsList;
    private LayoutInflater inflater;

    public AdapterHome(Context mContext, List<NewsObj> mNewsList){
        this.mContext = mContext;
        this.mNewsList = mNewsList;
        inflater = LayoutInflater.from(mContext);
    }

    public void addListItemToAdapter(List<NewsObj> list){
        mNewsList.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public NewsObj getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_recyclerview, parent, false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.txtv_title = (TextView) convertView.findViewById(R.id.txtv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsObj obj = mNewsList.get(position);
        Picasso.with(mContext).load(obj.getImgNews()).into(holder.img);
        holder.txtv_title.setText(obj.getTitleNews());

        return convertView;
    }

    private class ViewHolder{
        ImageView img;
        TextView txtv_title;
    }
}
