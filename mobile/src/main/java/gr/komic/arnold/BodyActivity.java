package gr.komic.arnold;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;

import gr.komic.arnold.Models.UserBodyInfo;
import gr.komic.arnold.helpers.Constants;
import gr.komic.arnold.helpers.SpinnersHelper;

public class BodyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Gson gson = new Gson();
    String userInfoJson;
    SharedPreferences mSharedPrefs;
    SharedPreferences.Editor mEditor;

    Spinner genderSpinner;
    Spinner ageSpinner;
    Spinner weightSpinner;
    Spinner heightSpinner;
    Spinner workoutsPerWeekSpinner;
    TextView bmiResult;
    TextView bmiResultText;
    TextView idealBMI;

    private double bmi;
    private UserBodyInfo userBodyInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.restoreUserInfo();

        this.bmiResult = findViewById(R.id.bmi_result);
        this.bmiResultText = findViewById(R.id.bmi_result_text);
        this.idealBMI = findViewById(R.id.ideal_bmi);

        this.setupSpinners();
        this.setBMIResultText();
        this.setIdealBMI();
    }

    private void restoreUserInfo() {
        this.mSharedPrefs = this.getSharedPreferences(Constants.BODY_INFO, MODE_PRIVATE);
        this.mEditor = mSharedPrefs.edit();

        String storedUserInfoJson = this.mSharedPrefs.getString(Constants.USER_INFO_OBJECT, null);
        if(storedUserInfoJson != null) {
            this.userBodyInfo = gson.fromJson(storedUserInfoJson, UserBodyInfo.class);
        }else {
            this.userBodyInfo = new UserBodyInfo();
        }
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

    private void storeUserBodyInfo() {
        this.userInfoJson = gson.toJson(this.userBodyInfo);
        this.mEditor.putString(Constants.USER_INFO_OBJECT, this.userInfoJson);
        this.mEditor.commit();
    }

    private void calculateBMI() {
        double height = this.userBodyInfo.getHeight() / 100.0;
        this.bmi = this.userBodyInfo.getWeight() / (height * height);
        this.bmiResult.setText(String.format("%.2f",this.bmi));
        this.setBMIResultText();
        this.storeUserBodyInfo();
    }

    private void setBMIResultText() {
        if(this.bmi < 18.5) {
            this.bmiResultText.setText(R.string.lessWeight);
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }else if(this.bmi < 25){
            this.bmiResultText.setText(R.string.normalWeight);
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
        }else if (this.bmi < 30){
            this.bmiResultText.setText(R.string.overweight);
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }else {
            this.bmiResultText.setText(R.string.obese);
            this.bmiResultText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }
    }

    private void setIdealBMI() {
        if(this.userBodyInfo.getAge() >= 19 && this.userBodyInfo.getAge() < 24) {
            this.idealBMI.setText(R.string.idealBMI1924);
        }else if(this.userBodyInfo.getAge() > 24 && this.userBodyInfo.getAge() <= 34) {
            this.idealBMI.setText(R.string.idealBMI2025);
        }else if(this.userBodyInfo.getAge() >= 35 && this.userBodyInfo.getAge() <= 44) {
            this.idealBMI.setText(R.string.idealBMI2126);
        }else if(this.userBodyInfo.getAge() >=45 && this.userBodyInfo.getAge() <=54) {
            this.idealBMI.setText(R.string.idealBMI2227);
        }else if(this.userBodyInfo.getAge() >=55 && this.userBodyInfo.getAge() <=64) {
            this.idealBMI.setText(R.string.idealBMI2328);
        }else if(this.userBodyInfo.getAge() >= 65) {
            this.idealBMI.setText(R.string.idealBMI2429);
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
                this.userBodyInfo.setWorkoutsPerWeek(SpinnersHelper.getWorkoutsPerWeekValues()[i]);
                this.storeUserBodyInfo();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("Nothing selected ", "---");
    }
}
