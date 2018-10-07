package gr.komic.arnold.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import gr.komic.arnold.Models.Program;
import gr.komic.arnold.R;
import gr.komic.arnold.ViewHolders.MyProgramViewHolder;

public class MyProgramRecyclerViewAdapter extends RecyclerView.Adapter<MyProgramViewHolder> {
    private static final String TAG = "MyProgramRecyclerViewAd";
    private ArrayList<Program> mProgramArrayList;
    private Context mContext;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public MyProgramRecyclerViewAdapter(Context context, ArrayList<Program> programArrayList) {
        this.mContext = context;
        this.mProgramArrayList = programArrayList;
    }

    @NonNull

    @Override
    public MyProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_card_layout, parent, false);
        MyProgramViewHolder holder = new MyProgramViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyProgramViewHolder holder, final int position) {
        if(this.mProgramArrayList.get(position).getIsCurrentProgram()) {
            holder.programType.setText("Current");
            holder.programCardParent.setBackgroundColor(Color.GREEN);
        }else {
            holder.programType.setText("Past");
            holder.programCardParent.setBackgroundColor(Color.RED);
        }
        holder.programCardItemDateTextView.setText(this.sdf.format(this.mProgramArrayList.get(position).getCreatedAt()));
        holder.programCardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyProgramRecyclerViewAdapter.this.mContext,"Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mProgramArrayList != null ? this.mProgramArrayList.size() : 0 ;
    }
}
