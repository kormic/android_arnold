package gr.komic.arnold.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gr.komic.arnold.Models.Program;
import gr.komic.arnold.R;

public class PastProgramsFragment extends Fragment {

    private OnPastProgramsInteractionListener mListener;
    ArrayList<Program> pastPrograms;
    TextView tmpTextView;

    public PastProgramsFragment() {
        // Required empty public constructor
    }

    public static PastProgramsFragment newInstance() {
        PastProgramsFragment fragment = new PastProgramsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pastPrograms = getArguments().getParcelableArrayList("pastPrograms");
        View view = inflater.inflate(R.layout.fragment_past_programs, container, false);
        tmpTextView = view.findViewById(R.id.tmp_textview);
        tmpTextView.setText(String.valueOf(pastPrograms.size()));

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPastProgramsInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPastProgramsInteractionListener) {
            mListener = (OnPastProgramsInteractionListener) context;
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

    public void updatePastPrograms(ArrayList<Program> programs) {
        pastPrograms = programs;
        tmpTextView.setText(String.valueOf(pastPrograms.size()));
    }

    public interface OnPastProgramsInteractionListener {
        void onPastProgramsInteraction();
    }
}
