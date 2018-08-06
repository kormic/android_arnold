package gr.komic.arnold;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import gr.komic.arnold.Adapters.MyProgressRecyclerViewAdapter;

public class MyProgressActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton addProgressFab;

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
        ArrayList<String> tempDates = new ArrayList<String>();

        int tempDay = 14;
        for(int i = 0; i < 10; i++){
            tempDates.add(tempDay + i + "/4/2018");
        }

        RecyclerView recyclerView = findViewById(R.id.my_progress_recycler_view);
        MyProgressRecyclerViewAdapter adapter = new MyProgressRecyclerViewAdapter(this, tempDates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
