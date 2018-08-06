package gr.komic.arnold.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import gr.komic.arnold.R;
import gr.komic.arnold.ViewHolders.MyProgressViewHolder;

public class MyProgressRecyclerViewAdapter extends RecyclerView.Adapter<MyProgressViewHolder> {

    private ArrayList<String> mTempArray;
    private Context mContext;

    public MyProgressRecyclerViewAdapter(Context context, ArrayList<String> tempArray) {
        this.mContext = context;
        this.mTempArray = tempArray;
    }

    @NonNull
    @Override
    public MyProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_card_layout, parent, false);
        MyProgressViewHolder holder = new MyProgressViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyProgressViewHolder holder, final int position) {
        if(position == 2 || position == 3){
            holder.progressCardItemStatusImageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ic_trending_up_white_24dp));
            holder.progressCardItemStatusImageViewWrapper.setBackgroundColor(Color.GREEN);
        }else if (position == 5 || position == 7) {
            holder.progressCardItemStatusImageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ic_trending_down_white_24dp));
            holder.progressCardItemStatusImageViewWrapper.setBackgroundColor(Color.RED);
        }else {
            holder.progressCardItemStatusImageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ic_trending_flat_white_24dp));
            holder.progressCardItemStatusImageViewWrapper.setBackgroundColor(Color.BLUE);
        }
//        holder.progressCardItemStatusImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.progressCardItemDateTextView.setText(this.mTempArray.get(position));
        holder.progressCardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyProgressRecyclerViewAdapter.this.mContext,"Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.mTempArray != null ? this.mTempArray.size() : 0 ;
    }
}
