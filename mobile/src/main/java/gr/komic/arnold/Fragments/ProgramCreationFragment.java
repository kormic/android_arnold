package gr.komic.arnold.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import gr.komic.arnold.Models.Exercise;
import gr.komic.arnold.Models.Program;
import gr.komic.arnold.Models.Set;
import gr.komic.arnold.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgramCreationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgramCreationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgramCreationFragment extends Fragment {

    CardView chest;
    CardView back;
    CardView legs;
    CardView shoulders;
    CardView biceps;
    CardView triceps;
    ArrayList<Program> programs = new ArrayList<Program>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProgramCreationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgramCreationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgramCreationFragment newInstance(String param1, String param2) {
        ProgramCreationFragment fragment = new ProgramCreationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program_creation, container, false);
        setupButtons(view);

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
                Exercise exercise1 = new Exercise(0, "Chest Press");
                Set set = new Set(12, 0);
                exercise1.addSet(set);

                Program program1 = new Program(0);
                program1.addExerciseToProgram(exercise1.getId());
                program1.setIsCurrentProgram(true);

                Program program2 = new Program(1);
                program2.addExerciseToProgram(exercise1.getId());
                programs.add(program1);
                programs.add(program2);
                Toast.makeText(getActivity(), "Chest", Toast.LENGTH_SHORT).show();
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Back", Toast.LENGTH_SHORT).show();
            }
        });

        this.legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Legs", Toast.LENGTH_SHORT).show();
            }
        });

        this.triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Triceps", Toast.LENGTH_SHORT).show();
            }
        });

        this.biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Biceps", Toast.LENGTH_SHORT).show();
            }
        });

        this.shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Shoulders", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
