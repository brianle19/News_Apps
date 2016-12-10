package thaile.com.aiw_client_finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import thaile.com.aiw_client_finalproject.NewsObj;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 11/25/2016.
 */

public class AdapterNewsTag extends BaseAdapter {
    private List<NewsObj> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;

    public AdapterNewsTag(List<NewsObj> mList, Context mContext){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public NewsObj getItem(int position) {
        return mList.get(position);
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
            convertView = inflater.inflate(R.layout.item_listnews_tag, parent, false);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.txtv_shortintro = (TextView) convertView.findViewById(R.id.txtv_shortintro);
            viewHolder.txtv_title = (TextView) convertView.findViewById(R.id.txtv_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewsObj obj = mList.get(position);
        viewHolder.txtv_shortintro.setText(obj.getShortIntro());
        viewHolder.txtv_title.setText(obj.getTitleNews());
        Picasso.with(mContext).load(obj.getImgNews()).into(viewHolder.img);
        return convertView;
    }

    public class ViewHolder{
        ImageView img;
        TextView txtv_title;
        TextView txtv_shortintro;
    }
}
