package gr.komic.arnold.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import gr.komic.arnold.R;

public class PastProgramViewHolder extends RecyclerView.ViewHolder {
    public CardView pastProgramCard;
    public LinearLayout pastProgramCardWrapper;
    public TextView pastProgramId;
    public TextView pastProgramTitle;

    public PastProgramViewHolder(View itemView) {
        super(itemView);
        pastProgramCard = itemView.findViewById(R.id.past_program_card_wrapper);
        pastProgramCardWrapper = itemView.findViewById(R.id.past_program_card);
        pastProgramId = itemView.findViewById(R.id.past_program_id);
        pastProgramTitle = itemView.findViewById(R.id.past_program_title);
    }
}
