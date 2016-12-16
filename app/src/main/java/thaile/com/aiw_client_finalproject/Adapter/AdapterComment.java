package thaile.com.aiw_client_finalproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import thaile.com.aiw_client_finalproject.CommentObj;
import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 11/30/2016.
 */

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder>{
    private LayoutInflater inflater;
    private List<CommentObj> arrList = new ArrayList<>();


    public AdapterComment(Context mContext, List<CommentObj> arrList){
        this.arrList = arrList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentObj obj = arrList.get(position);
        holder.txtv_name.setText(obj.getUsername());
        holder.txtv_content.setText(obj.getContent());
        holder.txtv_time.setText(obj.getDatetime());

    }

    @Override
    public int getItemCount() {
        if (arrList != null) {
            return arrList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtv_name;
        TextView txtv_content;
        TextView txtv_time;
        public ViewHolder(View itemView) {
            super(itemView);
            txtv_name = (TextView) itemView.findViewById(R.id.txtv_username);
            txtv_content = (TextView) itemView.findViewById(R.id.txtv_content);
            txtv_time = (TextView) itemView.findViewById(R.id.txtv_datetime);
        }
    }
}
