package gr.komic.arnold;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import gr.komic.arnold.Adapters.ViewPagerAdapter;
import gr.komic.arnold.Fragments.MuscleGroupFragment;
import gr.komic.arnold.Fragments.MyProgramFragment;
import gr.komic.arnold.Fragments.PastProgramsFragment;
import gr.komic.arnold.Fragments.ProgramCreationFragment;
import gr.komic.arnold.Fragments.SetsDialogFragment;
import gr.komic.arnold.Models.AvailableExercise;
import gr.komic.arnold.Models.Exercise;
import gr.komic.arnold.Models.ExerciseSet;
import gr.komic.arnold.Models.Program;
import gr.komic.arnold.Services.ExerciseDBDataSource;
import gr.komic.arnold.Services.ExerciseSetDBDataSource;
import gr.komic.arnold.Services.ProgramDBDataSource;


public class MyProgramActivity extends AppCompatActivity implements
        MyProgramFragment.OnFragmentInteractionListener,
        ProgramCreationFragment.OnFragmentInteractionListener,
        PastProgramsFragment.OnPastProgramsInteractionListener,
        MuscleGroupFragment.OnFragmentInteractionListener,
        SetsDialogFragment.OnFragmentInteractionListener {

    private static final String TAG = "MyProgramActivity";

    ProgramDBDataSource programDBDataSource;
    ExerciseDBDataSource exerciseDBDataSource;
    ExerciseSetDBDataSource exerciseSetDBDataSource;
    TabLayout tabLayout;
    ViewPager vp;
    Program selectedProgram;
    ArrayList<AvailableExercise> selectedAvailableExcercises = new ArrayList<>();
    ArrayList<ExerciseSet> selectedExerciseSets = new ArrayList<>();
    ArrayList<Program> pastPrograms = new ArrayList<>();
    PastProgramsFragment pastProgramsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_program_container);

        programDBDataSource = new ProgramDBDataSource(this);
        exerciseDBDataSource = new ExerciseDBDataSource(this);
        exerciseSetDBDataSource = new ExerciseSetDBDataSource(this);

        openDBs();
        restorePastPrograms();

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabs);
        vp = findViewById(R.id.viewpager);
        setupViewPager(vp);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openDBs();
    }

    @Override
    protected void onPause() {
        super.onPause();
        programDBDataSource.close();
        exerciseDBDataSource.close();
        exerciseSetDBDataSource.close();
    }

    private void openDBs() {
        programDBDataSource.open();
        exerciseDBDataSource.open();
        exerciseSetDBDataSource.open();
        restorePastPrograms();
    }

    private void restorePastPrograms() {
        pastPrograms = programDBDataSource.findAll();
        for(Program program: pastPrograms) {
            ArrayList<Exercise> exercises = exerciseDBDataSource.findExercisesByProgramId(program.getId());
            Log.d(TAG, "program retrieved: " + program.getTitle());
            for(Exercise exercise: exercises) {
                Log.d(TAG, "added exercise id: " + exercise.getId());
                ArrayList<ExerciseSet> exerciseSets = exerciseSetDBDataSource.getByProgramAndExerciseId(program.getId(), exercise.getId());
                for(ExerciseSet exerciseSet: exerciseSets) {
                    exercise.addExerciseSet(exerciseSet);
                }
                program.addExerciseToProgram(exercise);
            }
        }
        Log.d(TAG, "PastPrograms: " + pastPrograms.size());
    }

    public void setupViewPager(ViewPager vp) {
        final ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.addFragment(new MyProgramFragment(), "Current");
        vpa.addFragment(new ProgramCreationFragment(), "New");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("pastPrograms", pastPrograms);
        pastProgramsFragment = new PastProgramsFragment();
        pastProgramsFragment.setArguments(bundle);
        vpa.addFragment(pastProgramsFragment, "Past");
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fr = vpa.getItem(position);
                if(!fr.getClass().equals(ProgramCreationFragment.class)) {
                    ProgramCreationFragment programCreationFragment = (ProgramCreationFragment) vpa.getItem(1);

                    if (programCreationFragment.addingExercises) {
                        programCreationFragment.getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setAdapter(vpa);
    }

    @Override
    public void onProgramCreate() {
        if (selectedAvailableExcercises.size() > 0) {
            Program program = new Program();
            program.setTitle("Test program");
            program.setIsCurrentProgram(false);
            program.setCreatedAt(new Date());

            Program insertedProgram = programDBDataSource.insert(program);

            for (AvailableExercise availableExercise: selectedAvailableExcercises) {
                Exercise exercise = new Exercise(availableExercise.getName());
                exercise.setMuscleGroup(availableExercise.getMuscleGroup());
                exercise.setProgramId(insertedProgram.getId());
                exercise = exerciseDBDataSource.insert(exercise);

                for (ExerciseSet exerciseSet: selectedExerciseSets) {
                    if (availableExercise.getId() == exerciseSet.getExerciseId()) {
                        exerciseSet.setExerciseId(exercise.getId());
                        Log.d(TAG, "Exercise Muscle Group: " + exercise.getMuscleGroup());
                        exercise.addExerciseSet(exerciseSet);
                    }
                }
                insertedProgram.addExerciseToProgram(exercise);
            }

            for(ExerciseSet exerciseSet: selectedExerciseSets) { ;
                exerciseSet.setProgramId(insertedProgram.getId());
                exerciseSetDBDataSource.insert(exerciseSet);
            }

            selectedProgram = insertedProgram;
            pastProgramsFragment.updatePastPrograms(programDBDataSource.findAll());
        } else {
            Toast.makeText(this, "No exercises selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeProgram(Long programId) {
        programDBDataSource.delete(programId);
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
    public void onMuscleGroupInteraction(ArrayList<AvailableExercise> availableExercises) {
        for (AvailableExercise availableExercise : availableExercises) {
            if (!selectedAvailableExcercises.contains(availableExercise)) {
                Log.d(TAG, "onMuscleGroupInteraction: " + availableExercise.getName());
                selectedAvailableExcercises.add(availableExercise);
            }
        }
    }

    @Override
    public void onProgramInteraction() {
    }

    @Override
    public void onPastProgramsInteraction() {
    }
}
