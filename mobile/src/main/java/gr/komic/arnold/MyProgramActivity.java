package gr.komic.arnold;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MyProgramActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyProgramActivity";

    FloatingActionButton addProgramFab;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_prograrm_fab:
                Log.d(TAG, "Add program clicked");
        }
    }
}
