package gr.komic.arnold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import gr.komic.arnold.helpers.SpinnersHelper;

public class BodyActivity extends AppCompatActivity {

    Spinner ageSpinner;
    Spinner weightSpinner;
    Spinner heightSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ageSpinner = findViewById(R.id.age_spinner);
        ArrayAdapter<Integer> ageSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, SpinnersHelper.getAgeValues());
        ageSpinner.setAdapter(ageSpinnerAdapter);

        weightSpinner = findViewById(R.id.weight_spinner);
        ArrayAdapter<Integer> weightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, SpinnersHelper.getWeightValues());
        weightSpinner.setAdapter(weightSpinnerAdapter);

        heightSpinner = findViewById(R.id.height_spinner);
        ArrayAdapter<Integer> heightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, SpinnersHelper.getHeightValues());
        heightSpinner.setAdapter(heightSpinnerAdapter);
    }
}
