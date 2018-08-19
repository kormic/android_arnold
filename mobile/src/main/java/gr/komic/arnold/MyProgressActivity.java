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
import java.util.List;

import gr.komic.arnold.Adapters.MyProgressRecyclerViewAdapter;
import gr.komic.arnold.Models.Progress;
import gr.komic.arnold.Services.ProgressDataSource;

public class MyProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyProgressActivity";

    FloatingActionButton addProgressFab;
    ProgressDataSource progressDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.addProgressFab = findViewById(R.id.add_progress_fab);
        this.addProgressFab.setOnClickListener(this);

        this.progressDataSource = new ProgressDataSource(this);
        this.progressDataSource.open();

        ArrayList<Progress> progresses = this.progressDataSource.findAll();
        Log.i(TAG, "Progresses count: " + progresses.size());

        RecyclerView recyclerView = findViewById(R.id.my_progress_recycler_view);
        MyProgressRecyclerViewAdapter adapter = new MyProgressRecyclerViewAdapter(this, progresses);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.progressDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.progressDataSource.close();
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
