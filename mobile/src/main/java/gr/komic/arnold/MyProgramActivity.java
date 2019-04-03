package gr.komic.arnold;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import gr.komic.arnold.Adapters.ViewPagerAdapter;
import gr.komic.arnold.Fragments.MuscleGroupFragment;
import gr.komic.arnold.Fragments.MyProgramFragment;
import gr.komic.arnold.Fragments.ProgramCreationFragment;
import gr.komic.arnold.Fragments.SetsDialogFragment;
import gr.komic.arnold.Models.Exercise;
import gr.komic.arnold.Models.ExerciseSet;
import gr.komic.arnold.Models.Program;
import gr.komic.arnold.Services.ExerciseSetDBDataSource;


public class MyProgramActivity extends AppCompatActivity implements
        MyProgramFragment.OnFragmentInteractionListener,
        ProgramCreationFragment.OnFragmentInteractionListener,
        MuscleGroupFragment.OnFragmentInteractionListener,
        SetsDialogFragment.OnFragmentInteractionListener {

    private static final String TAG = "MyProgramActivity";

    ExerciseSetDBDataSource exerciseSetDBDataSource;
    TabLayout tabLayout;
    ViewPager vp;
    Program selectedProgram;
    ArrayList<Exercise> selectedExercises = new ArrayList<>();
    ArrayList<ExerciseSet> selectedExerciseSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_program_container);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabs);
        vp = findViewById(R.id.viewpager);
        setupViewPager(vp);
        tabLayout.setupWithViewPager(vp);

        exerciseSetDBDataSource = new ExerciseSetDBDataSource(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        exerciseSetDBDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        exerciseSetDBDataSource.close();
    }

    public void setupViewPager(ViewPager vp) {
        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.addFragment(new MyProgramFragment(), "Current");
        vpa.addFragment(new ProgramCreationFragment(), "New");
        vp.setAdapter(vpa);
    }

    @Override
    public void onProgramCreate(Program program) {
        selectedProgram = program;

        for (Exercise exercise : selectedExercises) {
            for (ExerciseSet exerciseSet : selectedExerciseSets) {
                if (exercise.getId() == exerciseSet.getExerciseId()) {
                    exercise.addExerciseSet(exerciseSet);
                    Log.d(TAG, "Exercise Muscle Group: " + exercise.getMuscleGroup());
                }
            }
            selectedProgram.addExerciseToProgram(exercise.getId());
        }

        Log.d(TAG, "onProgramCreate: " + selectedProgram.getExerciseIds());

    }

    @Override
    public void onExerciseSetsSet(ArrayList<ExerciseSet> exerciseSets) {
        for (ExerciseSet exerciseSet : exerciseSets) {
            if (!selectedExerciseSets.contains(exerciseSet)) {
                selectedExerciseSets.add(exerciseSet);
            }
        }
    }

    @Override
    public void onMuscleGroupInteraction(ArrayList<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            if (!selectedExercises.contains(exercise)) {
                Log.d(TAG, "onMuscleGroupInteraction: " + exercise.getName());
                selectedExercises.add(exercise);
            }
        }
    }

    @Override
    public void onProgramInteraction() {
    }
}
