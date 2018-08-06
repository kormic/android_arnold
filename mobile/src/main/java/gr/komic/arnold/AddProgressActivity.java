package gr.komic.arnold;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import gr.komic.arnold.Infrastructure.DateService;
import gr.komic.arnold.Services.AddProgressService;
import gr.komic.arnold.Services.UserService;
import gr.komic.arnold.helpers.Constants;

public class AddProgressActivity extends AppCompatActivity implements TextWatcher {

    TextView todayTextView;
    TextView genderTextView;
    TextView heightTextView;
    EditText neckEditText;
    EditText waistEditText;
    EditText hipsEditText;
    EditText fatPercentage;
    CardView hipsCardView;

    UserService userService;
    AddProgressService addProgressService = new AddProgressService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.userService = UserService.getInstance();
        this.setupViews();
    }

    private void setupViews() {
        this.todayTextView = findViewById(R.id.today);
        this.heightTextView = findViewById(R.id.height);
        this.neckEditText = findViewById(R.id.neck);
        this.waistEditText = findViewById(R.id.waist);
        this.hipsEditText = findViewById(R.id.hips);
        this.fatPercentage = findViewById(R.id.fat_percentage);
        genderTextView = findViewById(R.id.gender);
        if(this.userService.restoreUserInfo(this).getGender().equals("Γυναίκα")) {
            this.hipsCardView = findViewById(R.id.hips_section);
            this.hipsCardView.setVisibility(View.VISIBLE);
        }

        this.neckEditText.addTextChangedListener(this);
        this.waistEditText.addTextChangedListener(this);
        this.hipsEditText.addTextChangedListener(this);

        String today = DateService.getTodayToString("dd/MM/yyyy");

        this.todayTextView.setText(today);
        this.heightTextView.setText(String.valueOf(this.userService.restoreUserInfo(this).getHeight()));
        genderTextView.setText(this.userService.restoreUserInfo(this).getGender());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        this.calculateFatPercentage();
    }

    private void calculateFatPercentage() {
        if (this.neckEditText.getText().length() > 0 && this.waistEditText.getText().length() > 0) {
            double percentage;
            if (this.userService.restoreUserInfo(this).getGender().equals("Γυναίκα") && this.hipsEditText.getText().length() > 0) {
                percentage = this.addProgressService.calculateFatPercentageForWomen(Integer.parseInt(
                                this.waistEditText.getText().toString()),
                                Integer.parseInt(this.hipsEditText.getText().toString()),
                                Integer.parseInt(this.neckEditText.getText().toString()),
                                this.userService.restoreUserInfo(this).getHeight()
                            );
            }else {
                percentage = this.addProgressService.calculateFatPercentageForMen(
                                    Integer.parseInt(this.waistEditText.getText().toString()),
                                    Integer.parseInt(this.neckEditText.getText().toString()),
                                    this.userService.restoreUserInfo(this).getHeight()
                            );
            }
            if (Double.isNaN(percentage)) {
                this.fatPercentage.setText("");
            }else {
                this.fatPercentage.setText(String.format("%.2f", percentage));
            }
        }
    }
}
