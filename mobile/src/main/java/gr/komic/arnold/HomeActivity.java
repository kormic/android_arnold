package gr.komic.arnold;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import gr.komic.arnold.helpers.Constants;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_home);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.inflateMenu(R.menu.more_menu);
    }

    public void navigate(View view) {
        Log.d(Constants.HOME_ACTIVITY, String.valueOf(view.getId()));
        switch (view.getId()) {
            case R.id.workout_cardview:
                Intent intentProgram = new Intent(this, MyProgramActivity.class);
                startActivity(intentProgram);
                break;
            case R.id.my_body_button_layout:
                Intent intentBody = new Intent(this, BodyActivity.class);
                startActivity(intentBody);
                break;
            case R.id.my_gym_button_layout:
                Intent intentGym = new Intent(this, MyGymActivity.class);
                startActivity(intentGym);
                break;
            case R.id.my_progress_button_layout:
                Intent intentProgress = new Intent(this, MyProgressActivity.class);
                startActivity(intentProgress);
                break;
        }
    }
}
