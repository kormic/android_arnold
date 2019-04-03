package gr.komic.arnold.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import gr.komic.arnold.Models.Exercise;

import gr.komic.arnold.R;
import gr.komic.arnold.helpers.SpinnersHelper;

public class MuscleGroupFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "MuscleGroupFragment";

    LinearLayout containerLayout;
    Spinner exerciseSpinner;
    TextView titleTextView;
    Button addExerciseButton;
    Button storeExercisesButton;
    ArrayList<Exercise> exercises = new ArrayList<>();

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
        ArrayAdapter<Exercise> exerciseSpinnerAdapter = new ArrayAdapter<Exercise>(getActivity(), R.layout.spinner_text_center, SpinnersHelper.getMockExercises(getArguments().getString("group")));
        exerciseSpinner.setAdapter(exerciseSpinnerAdapter);
        exerciseSpinner.setOnItemSelectedListener(this);
    }

    public void addExercise() {
        Exercise selectedExercise = (Exercise) exerciseSpinner.getSelectedItem();
        Boolean added = exerciseHasBeenAdded(selectedExercise);

        if (!added) {
            LinearLayout exerciseContainer = new LinearLayout(getActivity());
            exerciseContainer.setId((int) selectedExercise.getId());
            exerciseContainer.setOrientation(LinearLayout.HORIZONTAL);
            exerciseContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            exerciseContainer.setWeightSum(3);

            TextView textView = new TextView(getActivity());
            textView.setText(selectedExercise.toString());
            textView.setTag(selectedExercise.toString() + "_exercise_container");
            textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER_VERTICAL);

            Button button = new Button(getContext());
            button.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            button.setText("Edit Sets");
            button.setTextColor(Color.WHITE);

            exerciseContainer.addView(textView);
            exerciseContainer.addView(button);

            containerLayout.addView(exerciseContainer);

            DialogFragment dialog = new SetsDialogFragment();

            Bundle bundle = new Bundle();
            Exercise exercise = (Exercise) exerciseSpinner.getSelectedItem();
            exercise.setMuscleGroup(getArguments().getString("group").toUpperCase());
            exercises.add(exercise);
            bundle.putLong("exerciseId", exercise.getId());

            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "SetsDialog");
        }

    }

    private boolean exerciseHasBeenAdded(Exercise selectedExercise) {
//        Boolean added = false;

//        final int childCount = containerLayout.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            LinearLayout ll = (LinearLayout) containerLayout.getChildAt(i);
//            if (ll.getId() == selectedExercise.getId()) {
//                added = true;
//            }
//        }
        return exercises.contains(selectedExercise);

//        return added;
    }

    public void storeExercises() {
        mListener.onMuscleGroupInteraction(exercises);
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        void onMuscleGroupInteraction(ArrayList<Exercise> exercises);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("Calling from the dialog", TAG);
//        if (requestCode == 1) {
//            ArrayList<ExerciseSet> exerciseSets = data.getParcelableArrayListExtra("exerciseSets");
//        }
//    }
}
