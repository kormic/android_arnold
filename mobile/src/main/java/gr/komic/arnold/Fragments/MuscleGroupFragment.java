package gr.komic.arnold.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import gr.komic.arnold.R;
import gr.komic.arnold.helpers.SpinnersHelper;

public class MuscleGroupFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "MuscleGroupFragment";

    LinearLayout containerLayout;
    Spinner exerciseSpinner;
    TextView titleTextView;
    Button addExerciseButton;
    Button storeExercisesButton;

    private OnFragmentInteractionListener mListener;

    public MuscleGroupFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muscle_group, container, false);
        setupViews(view);
        addOnClickListeners(view);
        setupExerciseSpinner();

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
        exerciseSpinner = view.findViewById(R.id.exercise_spinner);
        containerLayout = view.findViewById(R.id.muscle_group_container);
        titleTextView = view.findViewById(R.id.muscle_group_title);
        titleTextView.setText(getArguments().getString("group").toUpperCase());
        addExerciseButton = view.findViewById(R.id.close_fragment);
        storeExercisesButton = view.findViewById(R.id.store_exercises_button);
    }

    private void addOnClickListeners(View view) {
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });
        storeExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeExercises();
            }
        });
    }

    private void setupExerciseSpinner() {
        ArrayAdapter<String> exerciseSpinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text_center, SpinnersHelper.getMockExercises(getArguments().getString("group")));
        exerciseSpinner.setAdapter(exerciseSpinnerAdapter);
        exerciseSpinner.setOnItemSelectedListener(this);
    }

    public void addExercise() {
        LinearLayout exerciseContainer = new LinearLayout(getActivity());
        exerciseContainer.setOrientation(LinearLayout.VERTICAL);
        exerciseContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView textView = new TextView(getActivity());
        textView.setText(exerciseSpinner.getSelectedItem().toString());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout setsContrainer = new LinearLayout(getActivity());
        setsContrainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setsContrainer.setWeightSum(4);
        ArrayAdapter<Integer> repSpinnerAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.spinner_text_center, SpinnersHelper.getReps());
        Spinner repSpinner1 = new Spinner(getActivity());
        repSpinner1.setAdapter(repSpinnerAdapter);
        repSpinner1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        Spinner repSpinner2 = new Spinner(getActivity());
        repSpinner2.setAdapter(repSpinnerAdapter);
        repSpinner2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        Spinner repSpinner3 = new Spinner(getActivity());
        repSpinner3.setAdapter(repSpinnerAdapter);
        repSpinner3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        Spinner repSpinner4 = new Spinner(getActivity());
        repSpinner4.setAdapter(repSpinnerAdapter);
        repSpinner4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        setsContrainer.addView(repSpinner1);
        setsContrainer.addView(repSpinner2);
        setsContrainer.addView(repSpinner3);
        setsContrainer.addView(repSpinner4);
        exerciseContainer.addView(textView);
        exerciseContainer.addView(setsContrainer);
        containerLayout.addView(exerciseContainer);
    }

    public void storeExercises() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
