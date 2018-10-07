package gr.komic.arnold;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import gr.komic.arnold.Adapters.MyProgramRecyclerViewAdapter;
import gr.komic.arnold.Models.Exercise;
import gr.komic.arnold.Models.Program;
import gr.komic.arnold.Models.Set;

public class MyProgramActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyProgramActivity";

    FloatingActionButton addProgramFab;
    RecyclerView recyclerView;
    ArrayList<Program> programs = new ArrayList<Program>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_program);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.addProgramFab = findViewById(R.id.add_prograrm_fab);
        this.addProgramFab.setOnClickListener(this);

        createMockPrograms();
    }

    private void createMockPrograms() {
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.recyclerView = findViewById(R.id.my_program_recycler_view);
        MyProgramRecyclerViewAdapter adapter = new MyProgramRecyclerViewAdapter(this, programs);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_prograrm_fab:
                Log.d(TAG, "Add program clicked");
        }
    }
}
