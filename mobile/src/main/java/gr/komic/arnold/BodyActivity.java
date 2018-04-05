package gr.komic.arnold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import gr.komic.arnold.helpers.SpinnersHelper;

public class BodyActivity extends AppCompatActivity {

    Spinner genderSpinner;
    Spinner ageSpinner;
    Spinner weightSpinner;
    Spinner heightSpinner;
    Spinner workoutsPerWeekSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setupSpinners();
    }

    private void setupSpinners() {
        genderSpinner = findViewById(R.id.gender_spinner);
        ArrayAdapter<String> genderSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_center, SpinnersHelper.getGenderValues());
        genderSpinner.setAdapter(genderSpinnerAdapter);

        ageSpinner = findViewById(R.id.age_spinner);
        ArrayAdapter<Integer> ageSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getAgeValues());
        ageSpinner.setAdapter(ageSpinnerAdapter);

        weightSpinner = findViewById(R.id.weight_spinner);
        ArrayAdapter<Integer> weightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getWeightValues());
        weightSpinner.setAdapter(weightSpinnerAdapter);

        heightSpinner = findViewById(R.id.height_spinner);
        ArrayAdapter<Integer> heightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getHeightValues());
        heightSpinner.setAdapter(heightSpinnerAdapter);

        workoutsPerWeekSpinner = findViewById(R.id.workouts_per_week_spinner);
        ArrayAdapter<Integer> workoutsPerWeekSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getWorkoutsPerWeekValues());
        workoutsPerWeekSpinner.setAdapter(workoutsPerWeekSpinnerAdapter);
    }
}
