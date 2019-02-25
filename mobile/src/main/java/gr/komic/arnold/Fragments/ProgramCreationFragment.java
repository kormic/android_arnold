package gr.komic.arnold.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import gr.komic.arnold.Models.Program;
import gr.komic.arnold.R;

public class ProgramCreationFragment extends Fragment {

    CardView chest;
    CardView back;
    CardView legs;
    CardView shoulders;
    CardView biceps;
    CardView triceps;
    ArrayList<Program> programs = new ArrayList<Program>();

    private OnFragmentInteractionListener mListener;

    public ProgramCreationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program_creation, container, false);
        setupButtons(view);
        Program program1 = new Program(0);
        program1.setIsCurrentProgram(true);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setupButtons(View view) {
        this.chest = view.findViewById(R.id.chest);
        this.back = view.findViewById(R.id.back);
        this.legs = view.findViewById(R.id.legs);
        this.triceps = view.findViewById(R.id.triceps);
        this.biceps = view.findViewById(R.id.biceps);
        this.shoulders = view.findViewById(R.id.shoulders);

        this.chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMuscleGroupFragment("chest");
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMuscleGroupFragment("back");
            }
        });

        this.legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMuscleGroupFragment("legs");
            }
        });

        this.triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMuscleGroupFragment("triceps");
            }
        });

        this.biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMuscleGroupFragment("biceps");
            }
        });

        this.shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMuscleGroupFragment("shoulders");
            }
        });
    }

    private void openMuscleGroupFragment(String group) {
        Bundle bundle = new Bundle();
        bundle.putString("group", group);
        Fragment muscleGroupFragment = new MuscleGroupFragment();
        muscleGroupFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, muscleGroupFragment, group)
                .addToBackStack(null)
                .commit();
    }
}
