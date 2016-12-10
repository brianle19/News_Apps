package thaile.com.aiw_client_finalproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import thaile.com.aiw_client_finalproject.CallContent;
import thaile.com.aiw_client_finalproject.NewsObj;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 11/29/2016.
 */

public class AdapterNewsBase extends RecyclerView.Adapter<AdapterNewsBase.ViewHolder>{
    private List<NewsObj> arrNews = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;
    private CallContent callContent;

    public AdapterNewsBase(Context mContext, List<NewsObj> arrNews){
        this.mContext = mContext;
        this.arrNews = arrNews;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsObj newsObj = arrNews.get(position);
        holder.txtv.setText(newsObj.getTitleNews());
        Picasso.with(mContext).load(newsObj.getImgNews()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView txtv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            txtv = (TextView) itemView.findViewById(R.id.txtv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (callContent!=null){
                callContent.moveToContent(pos);
            }
        }
    }

    public void setShowContent(CallContent callContent){
        this.callContent = callContent;
    }
}
