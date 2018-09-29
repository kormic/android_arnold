package gr.komic.arnold;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;

import gr.komic.arnold.Models.MyGymInfo;
import gr.komic.arnold.helpers.Constants;

public class MyGymActivity extends AppCompatActivity
        implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        PlaceSelectionListener {

    private static final String TAG = "MyGymActivity";

    private GoogleApiClient mGoogleApiClient;
    PlaceAutocompleteFragment autocompleteFragment;

    Gson gson = new Gson();
    String myGymInfoJson;
    SharedPreferences mSharedPrefs;
    SharedPreferences.Editor mEditor;
    private MyGymInfo myGymInfo;

    EditText mGymNameEditext;
    EditText mGymAddressEditText;
    EditText mGymPhoneEditText;
    EditText mGymRatingEditText;
    EditText mGymWebsiteEditText;
    TextView mGymRegistrationTextView;
    ImageButton mGymRegistrationDateImageButton;
    EditText mGymSubscriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gym);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.restoreMyGymInfo();
        this.setupViews();
        this.setupAutocomplete();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // TODO: Please implement GoogleApiClient.OnConnectionFailedListener to
    // handle connection failures.


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.my_gym_registration || view.getId() == R.id.my_gym_registration_date_picker) {
            DialogFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String dateString = day + "/" + (month + 1) + "/" + year;
        this.myGymInfo.setRegistrationDateString(dateString);
        this.mGymRegistrationTextView.setText(this.myGymInfo.getRegistrationDateString());
        this.storeMyGymInfo();
    }

    @Override
    public void onPlaceSelected(Place place) {
        this.myGymInfo.setName(place.getName() != null ? place.getName().toString() : "Not available");
        this.mGymNameEditext.setText(this.myGymInfo.getName());

        this.myGymInfo.setAddress(place.getAddress() != null ? place.getAddress().toString() : "Not available");
        this.mGymAddressEditText.setText(this.myGymInfo.getAddress());

        this.myGymInfo.setPhone(place.getPhoneNumber() != null ? place.getPhoneNumber().toString() : "Not available");
        this.mGymPhoneEditText.setText(this.myGymInfo.getPhone());

        this.myGymInfo.setRating(place.getRating());
        this.mGymRatingEditText.setText(String.valueOf(this.myGymInfo.getRating()));

        this.myGymInfo.setWebsite(place.getWebsiteUri() != null ? place.getWebsiteUri().toString() : "Not available");
        this.mGymWebsiteEditText.setText(this.myGymInfo.getWebsite());

        this.myGymInfo.setRegistrationDateString("");
        this.mGymRegistrationTextView.setText(this.myGymInfo.getRegistrationDateString());

        this.myGymInfo.setSubscription(0);
        this.mGymSubscriptionEditText.setText("");

        this.storeMyGymInfo();
    }

    @Override
    public void onError(Status status) {
        // TODO: Handle the error.
    }

    private void storeMyGymInfo() {
        this.myGymInfoJson = gson.toJson(this.myGymInfo);
        this.mEditor.putString(Constants.MY_GYM_INFO_OBJECT, this.myGymInfoJson);
        this.mEditor.commit();
    }

    private void restoreMyGymInfo() {
        this.mSharedPrefs = this.getSharedPreferences(Constants.MY_GYM_INFO, MODE_PRIVATE);
        this.mEditor = mSharedPrefs.edit();

        String storedMyGymInfoJson = this.mSharedPrefs.getString(Constants.MY_GYM_INFO_OBJECT, null);
        if(storedMyGymInfoJson != null) {
            this.myGymInfo = gson.fromJson(storedMyGymInfoJson, MyGymInfo.class);
            Log.d(TAG, String.valueOf(this.myGymInfo.getSubscription()));
        }else {
            this.myGymInfo = new MyGymInfo();
        }
    }

    private void setupViews() {
        this.mGymNameEditext = findViewById(R.id.my_gym_name);
        this.mGymAddressEditText = findViewById(R.id.my_gym_address);
        this.mGymPhoneEditText = findViewById(R.id.my_gym_phone);
        this.mGymRatingEditText = findViewById(R.id.my_gym_rating);
        this.mGymWebsiteEditText = findViewById(R.id.my_gym_website);
        this.mGymRegistrationTextView = findViewById(R.id.my_gym_registration);
        this.mGymRegistrationDateImageButton = findViewById(R.id.my_gym_registration_date_picker);
        this.mGymSubscriptionEditText = findViewById(R.id.my_gym_subscription);

        this.mGymRegistrationDateImageButton.setOnClickListener(this);
        this.mGymRegistrationTextView.setOnClickListener(this);
        this.mGymSubscriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if(editable.length() > 0){
                        float editableWithCommasReplacedByDots = Float.parseFloat(editable.toString().replaceAll(",", "."));
                        float subscriptionValue = editableWithCommasReplacedByDots;
                        Log.d(TAG, String.valueOf(subscriptionValue));
                        MyGymActivity.this.myGymInfo.setSubscription(subscriptionValue);
                    }else {
                        MyGymActivity.this.myGymInfo.setSubscription(0);
                    }
                }catch (NumberFormatException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    MyGymActivity.this.myGymInfo.setSubscription(0);
                }
                MyGymActivity.this.storeMyGymInfo();
            }
        });


        this.mGymNameEditext.setText(this.myGymInfo.getName());
        this.mGymAddressEditText.setText(this.myGymInfo.getAddress());
        this.mGymPhoneEditText.setText(this.myGymInfo.getPhone());
        this.mGymRatingEditText.setText(String.valueOf(this.myGymInfo.getRating()));
        this.mGymWebsiteEditText.setText(this.myGymInfo.getWebsite());
        this.mGymRegistrationTextView.setText(this.myGymInfo.getRegistrationDateString());
        this.mGymSubscriptionEditText.setText(String.format("%.2f",this.myGymInfo.getSubscription()));
    }

    private void setupAutocomplete() {
        this.mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        this.autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

}
