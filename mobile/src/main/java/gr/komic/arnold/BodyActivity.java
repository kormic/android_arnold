package gr.komic.arnold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import gr.komic.arnold.helpers.SpinnersHelper;

public class BodyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner genderSpinner;
    Spinner ageSpinner;
    Spinner weightSpinner;
    Spinner heightSpinner;
    Spinner workoutsPerWeekSpinner;
    TextView bmiResult;
    TextView bmiResultText;
    TextView idealBMI;
    int age = 25;
    double height;
    int weight;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.bmiResult = findViewById(R.id.bmi_result);
        this.bmiResultText = findViewById(R.id.bmi_result_text);
        this.idealBMI = findViewById(R.id.ideal_bmi);
        this.setupSpinners();
        this.setBMIResultText();
        this.setIdealBMI();

    }

    private void setupSpinners() {
        genderSpinner = findViewById(R.id.gender_spinner);
        ArrayAdapter<String> genderSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_center, SpinnersHelper.getGenderValues());
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setOnItemSelectedListener(this);
        genderSpinner.setSelection(0);

        ageSpinner = findViewById(R.id.age_spinner);
        ArrayAdapter<Integer> ageSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getAgeValues());
        ageSpinner.setAdapter(ageSpinnerAdapter);
        ageSpinner.setOnItemSelectedListener(this);
        ageSpinner.setSelection(24);

        weightSpinner = findViewById(R.id.weight_spinner);
        ArrayAdapter<Integer> weightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getWeightValues());
        weightSpinner.setAdapter(weightSpinnerAdapter);
        weightSpinner.setOnItemSelectedListener(this);
        weightSpinner.setSelection(35);

        heightSpinner = findViewById(R.id.height_spinner);
        ArrayAdapter<Integer> heightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getHeightValues());
        heightSpinner.setAdapter(heightSpinnerAdapter);
        heightSpinner.setOnItemSelectedListener(this);
        heightSpinner.setSelection(90);

        workoutsPerWeekSpinner = findViewById(R.id.workouts_per_week_spinner);
        ArrayAdapter<Integer> workoutsPerWeekSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getWorkoutsPerWeekValues());
        workoutsPerWeekSpinner.setAdapter(workoutsPerWeekSpinnerAdapter);
        workoutsPerWeekSpinner.setOnItemSelectedListener(this);
        workoutsPerWeekSpinner.setSelection(3);
    }

    private void calculateBMI() {
        this.bmi = this.weight / (this.height * this.height);
        this.bmiResult.setText(String.format("%.2f",this.bmi));
        this.setBMIResultText();
    }

    private void setBMIResultText() {
        if(this.bmi < 18.5) {
            this.bmiResultText.setText("Ελλιποβαρής");
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }else if(this.bmi < 25){
            this.bmiResultText.setText("Κανονικό βάρος");
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
        }else if (this.bmi < 30){
            this.bmiResultText.setText("Υπέρβαρος");
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }else {
            this.bmiResultText.setText("Παχυσαρκία");
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }
    }

    private void setIdealBMI() {
        if(this.age >= 19 && this.age < 24) {
            this.idealBMI.setText("19-24");
        }else if(this.age > 24 && this.age <= 34) {
            this.idealBMI.setText("20-25");
        }else if(this.age >= 35 && this.age <= 44) {
            this.idealBMI.setText("21-26");
        }else if(this.age >=45 && this.age <=54) {
            this.idealBMI.setText("22-27");
        }else if(this.age >=55 && this.age <=64) {
            this.idealBMI.setText("23-28");
        }else if(this.age >= 65) {
            this.idealBMI.setText("24-29");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.gender_spinner:
                this.calculateBMI();
                break;
            case R.id.age_spinner:
                this.age = i;
                this.calculateBMI();
                this.setIdealBMI();
                break;
            case R.id.weight_spinner:
                this.weight = i + 40;
                this.calculateBMI();
                break;
            case R.id.height_spinner:
                this.height = ((i + 90) / 100.00);
                this.calculateBMI();
                break;
            default:
                Log.d("Workouts ", String.valueOf(i + 1));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("Nothing selected ", "---");
    }
}
