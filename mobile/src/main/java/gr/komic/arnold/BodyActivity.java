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

import java.util.Arrays;

import gr.komic.arnold.Models.UserBodyInfo;
import gr.komic.arnold.helpers.SpinnersHelper;

public class BodyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String BODY_INFO = "bodyInfo";

    Spinner genderSpinner;
    Spinner ageSpinner;
    Spinner weightSpinner;
    Spinner heightSpinner;
    Spinner workoutsPerWeekSpinner;
    TextView bmiResult;
    TextView bmiResultText;
    TextView idealBMI;
    double bmi;
    private UserBodyInfo userBodyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.userBodyInfo = new UserBodyInfo();

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

        ageSpinner = findViewById(R.id.age_spinner);
        ArrayAdapter<Integer> ageSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getAgeValues());
        ageSpinner.setAdapter(ageSpinnerAdapter);
        ageSpinner.setOnItemSelectedListener(this);

        weightSpinner = findViewById(R.id.weight_spinner);
        ArrayAdapter<Integer> weightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getWeightValues());
        weightSpinner.setAdapter(weightSpinnerAdapter);
        weightSpinner.setOnItemSelectedListener(this);

        heightSpinner = findViewById(R.id.height_spinner);
        ArrayAdapter<Integer> heightSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getHeightValues());
        heightSpinner.setAdapter(heightSpinnerAdapter);
        heightSpinner.setOnItemSelectedListener(this);

        workoutsPerWeekSpinner = findViewById(R.id.workouts_per_week_spinner);
        ArrayAdapter<Integer> workoutsPerWeekSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_text_center, SpinnersHelper.getWorkoutsPerWeekValues());
        workoutsPerWeekSpinner.setAdapter(workoutsPerWeekSpinnerAdapter);
        workoutsPerWeekSpinner.setOnItemSelectedListener(this);

        genderSpinner.setSelection(Arrays.asList(SpinnersHelper.getGenderValues()).indexOf(this.userBodyInfo.getGender()));
        ageSpinner.setSelection(this.userBodyInfo.getAge() - 1);
        weightSpinner.setSelection(this.userBodyInfo.getWeight() - 1);
        heightSpinner.setSelection((this.userBodyInfo.getHeight()) - 1);
        workoutsPerWeekSpinner.setSelection(this.userBodyInfo.getWorkoutsPerWeek() - 1);
    }

    private void calculateBMI() {
        double height = this.userBodyInfo.getHeight() / 100.0;
        this.bmi = this.userBodyInfo.getWeight() / (height * height);
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
        if(this.userBodyInfo.getAge() >= 19 && this.userBodyInfo.getAge() < 24) {
            this.idealBMI.setText("19-24");
        }else if(this.userBodyInfo.getAge() > 24 && this.userBodyInfo.getAge() <= 34) {
            this.idealBMI.setText("20-25");
        }else if(this.userBodyInfo.getAge() >= 35 && this.userBodyInfo.getAge() <= 44) {
            this.idealBMI.setText("21-26");
        }else if(this.userBodyInfo.getAge() >=45 && this.userBodyInfo.getAge() <=54) {
            this.idealBMI.setText("22-27");
        }else if(this.userBodyInfo.getAge() >=55 && this.userBodyInfo.getAge() <=64) {
            this.idealBMI.setText("23-28");
        }else if(this.userBodyInfo.getAge() >= 65) {
            this.idealBMI.setText("24-29");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.gender_spinner:
                this.userBodyInfo.setGender(SpinnersHelper.getGenderValues()[i]);
                this.calculateBMI();
                break;
            case R.id.age_spinner:
                this.userBodyInfo.setAge(SpinnersHelper.getAgeValues()[i]);
                this.calculateBMI();
                this.setIdealBMI();
                break;
            case R.id.weight_spinner:
                this.userBodyInfo.setWeight(SpinnersHelper.getWeightValues()[i]);
                this.calculateBMI();
                break;
            case R.id.height_spinner:
                this.userBodyInfo.setHeight(SpinnersHelper.getHeightValues()[i]);
                this.calculateBMI();
                break;
            default:
                Log.d("Workouts ", String.valueOf(i));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("Nothing selected ", "---");
    }
}
