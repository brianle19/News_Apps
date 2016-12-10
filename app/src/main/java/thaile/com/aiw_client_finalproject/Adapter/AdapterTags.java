package thaile.com.aiw_client_finalproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import thaile.com.aiw_client_finalproject.R;

/**
 * Created by Thai Le on 11/25/2016.
 */

public class AdapterTags extends RecyclerView.Adapter<AdapterTags.ViewHolder> {

    private List<String> arrTag = new ArrayList<>();
    private LayoutInflater inflater;
    private CallFragmentNewsTag callFragmentNewsTag;

    public AdapterTags(List<String> arrTag, Context mContext){
        this.arrTag = arrTag;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public AdapterTags.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_tag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterTags.ViewHolder holder, int position) {
        String nameTag = arrTag.get(position);
        holder.btn.setText(nameTag);
    }

    @Override
    public int getItemCount() {
        if (arrTag != null) {
            return arrTag.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btn;
        public ViewHolder(View itemView) {
            super(itemView);
            btn = (Button)itemView.findViewById(R.id.btn_tag);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            String tagName = arrTag.get(pos);
            if (callFragmentNewsTag!=null){
                callFragmentNewsTag.showFragment(tagName);
            }
        }
    }

    public void setShowFragmentNewsTag(CallFragmentNewsTag callFragmentNewsTag){
        this.callFragmentNewsTag = callFragmentNewsTag;

    }

    public interface CallFragmentNewsTag{
        public void showFragment(String tagName);
    }


}
