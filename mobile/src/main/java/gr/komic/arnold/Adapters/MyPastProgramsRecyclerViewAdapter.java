package gr.komic.arnold.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gr.komic.arnold.Models.Program;
import gr.komic.arnold.MyProgramActivity;
import gr.komic.arnold.R;
import gr.komic.arnold.ViewHolders.PastProgramViewHolder;

public class MyPastProgramsRecyclerViewAdapter extends RecyclerView.Adapter<PastProgramViewHolder> {
    private static final String TAG = "MyPastProgramsRecyclerViewAd";
    private ArrayList<Program> mPastProgramsArrayList;
    private Context mContext;

    public MyPastProgramsRecyclerViewAdapter(Context context, ArrayList<Program> pastPrograms) {
        mContext = context;
        mPastProgramsArrayList = pastPrograms;
    }

    @NonNull
    @Override
    public PastProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_program_card, parent, false);
        PastProgramViewHolder holder = new PastProgramViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PastProgramViewHolder holder, final int position) {
        final Program currentProgram = mPastProgramsArrayList.get(position);
        holder.pastProgramId.setText(String.format("id: %s", String.valueOf(currentProgram.getId())));
        holder.pastProgramTitle.setText(currentProgram.getTitle());
        holder.pastProgramCardWrapper.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MyProgramActivity)mContext).removeProgram(currentProgram.getId());
                mPastProgramsArrayList.remove(position);
                notifyDataSetChanged();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPastProgramsArrayList != null ? mPastProgramsArrayList.size() : 0;
    }
}
