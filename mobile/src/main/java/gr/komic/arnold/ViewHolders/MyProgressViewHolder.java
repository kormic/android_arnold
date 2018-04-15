package gr.komic.arnold.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gr.komic.arnold.R;

public class MyProgressViewHolder extends RecyclerView.ViewHolder {

    public ImageView progressCardItemStatusImageView;
    public TextView progressCardItemDateTextView;
    public CardView progressCardParent;

    public MyProgressViewHolder(View itemView) {
        super(itemView);
        this.progressCardParent = itemView.findViewById(R.id.progress_card_parent);
        this.progressCardItemStatusImageView = itemView.findViewById(R.id.progress_card_item_status_image);
        this.progressCardItemDateTextView = itemView.findViewById(R.id.progress_card_item_date);
    }
}
