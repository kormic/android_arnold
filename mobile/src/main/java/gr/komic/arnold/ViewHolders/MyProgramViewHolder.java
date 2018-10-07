package gr.komic.arnold.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import gr.komic.arnold.R;

public class MyProgramViewHolder extends RecyclerView.ViewHolder {
    public TextView programCardItemDateTextView;
    public CardView programCardParent;
    public TextView programType;

    public MyProgramViewHolder(View itemView) {
        super(itemView);
        this.programCardParent = itemView.findViewById(R.id.program_card_parent);
        this.programType = itemView.findViewById(R.id.program_type);
        this.programCardItemDateTextView = itemView.findViewById(R.id.program_created_date);
    }
}
