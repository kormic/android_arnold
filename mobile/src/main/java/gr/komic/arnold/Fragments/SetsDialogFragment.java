package gr.komic.arnold.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import gr.komic.arnold.Models.ExerciseSet;
import gr.komic.arnold.R;
import gr.komic.arnold.helpers.SpinnersHelper;

public class SetsDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SetsDialogFragment";
    private OnFragmentInteractionListener mListener;

    LinearLayout containerLayout;
    Spinner noOfSetsSpinner;
    Button storeExercisesButton;
    ArrayList<ExerciseSet> exerciseSets = new ArrayList<>();
    Long exerciseId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sets_dialog, container, false);
        exerciseId = getArguments().getLong("exerciseId");
        setupViews(view);

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

    private void setupViews(View view) {
        containerLayout = view.findViewById(R.id.sets_container);
        storeExercisesButton = view.findViewById(R.id.store_sets_button);
        storeExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeSets(v);
            }
        });
        noOfSetsSpinner = view.findViewById(R.id.set_spinner);
        setupNoOfSetsSpinner();
    }

    private void setupNoOfSetsSpinner() {
        ArrayAdapter<Integer> noOfSetsSpinnerAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.spinner_text_center, SpinnersHelper.getNoOfSets());
        noOfSetsSpinner.setAdapter(noOfSetsSpinnerAdapter);
        noOfSetsSpinner.setOnItemSelectedListener(this);
        noOfSetsSpinner.setSelection(3);
    }

    public void storeSets(View view) {
        Log.d(TAG, "storeSets: Storing sets " + exerciseSets.size());
        mListener.onExerciseSetsSet(exerciseSets);
        dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, parent.getItemAtPosition(position).toString());
        containerLayout.removeAllViews();
        exerciseSets.clear();

        for (int i = 0; i <= position; i++) {
            LinearLayout repsContainer = new LinearLayout(getActivity());
            repsContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            repsContainer.setOrientation(LinearLayout.HORIZONTAL);
            repsContainer.setGravity(Gravity.CENTER);

            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText("Set " + (i + 1));

            ArrayAdapter<Integer> repSpinnerAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.spinner_text_center, SpinnersHelper.getReps());
            Spinner repSpinner = new Spinner(getActivity());

            repSpinner.setAdapter(repSpinnerAdapter);
            repSpinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            repSpinner.setGravity(Gravity.END);
            repSpinner.setId(i);
            repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    exerciseSets.get(parent.getId()).setReps(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Space space = new Space(getActivity());
            space.setLayoutParams(new LinearLayout.LayoutParams(30, ViewGroup.LayoutParams.MATCH_PARENT));

            repsContainer.addView(textView);
            repsContainer.addView(space);
            repsContainer.addView(repSpinner);

            containerLayout.addView(repsContainer);

            ExerciseSet exerciseSet = new ExerciseSet(1, 0);
            exerciseSet.setSequence(i);
            exerciseSet.setExerciseId(exerciseId);
            exerciseSets.add(exerciseSet);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        void onExerciseSetsSet(ArrayList<ExerciseSet> exerciseSets);
    }
}
