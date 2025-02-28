package gr.komic.arnold;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import gr.komic.arnold.Adapters.MyProgressRecyclerViewAdapter;
import gr.komic.arnold.Models.Progress;
import gr.komic.arnold.Services.ProgressDataSource;

public class MyProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyProgressActivity";

    FloatingActionButton addProgressFab;
    ProgressDataSource progressDataSource;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addProgressFab = findViewById(R.id.add_progress_fab);
        addProgressFab.setOnClickListener(this);

        progressDataSource = new ProgressDataSource(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDataSource.open();
        ArrayList<Progress> progresses = this.progressDataSource.findAllProgresses();
        Log.i(TAG, "Progresses count: " + progresses.size());

        recyclerView = findViewById(R.id.my_progress_recycler_view);
        MyProgressRecyclerViewAdapter adapter = new MyProgressRecyclerViewAdapter(this, progresses);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        progressDataSource.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_progress_fab:
                Intent intentAddProgress = new Intent(this, AddProgressActivity.class);
                startActivity(intentAddProgress);
        }
    }
}
