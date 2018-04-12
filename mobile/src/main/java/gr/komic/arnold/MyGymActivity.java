package gr.komic.arnold;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class MyGymActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient mGoogleApiClient;
    PlaceAutocompleteFragment autocompleteFragment;

    EditText mGymNameEditext;
    EditText mGymAddressEditText;
    EditText mGymPhoneEditText;
    EditText mGymRatingEditText;
    EditText mGymWebsiteEditText;
    TextView mGymRegistrationTextView;
    ImageButton mRegistrationDateImageButton;
    EditText mSubscriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gym);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setupViews();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        this.autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("OH JESUS", "Place: " + place.getName());
                Log.i("OH 2", "Address " + place.getAddress());
                Log.i("OH 3", "PhoneNumber " + place.getPhoneNumber());
                Log.i("OH 4", "Rating " + place.getRating());
                Log.i("OH 5", "Website " + place.getWebsiteUri());



            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("OH MY GOD!", "An error occurred: " + status);
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // TODO: Please implement GoogleApiClient.OnConnectionFailedListener to
    // handle connection failures.

    private void setupViews() {
        this.mGymNameEditext = findViewById(R.id.my_gym_name);
        this.mGymAddressEditText = findViewById(R.id.my_gym_address);
        this.mGymPhoneEditText = findViewById(R.id.my_gym_phone);
        this.mGymRatingEditText = findViewById(R.id.my_gym_rating);
        this.mGymWebsiteEditText = findViewById(R.id.my_gym_website);
        this.mGymRegistrationTextView = findViewById(R.id.my_gym_registration);
        this.mRegistrationDateImageButton = findViewById(R.id.my_gym_registration_date_picker);
        this.mSubscriptionEditText = findViewById(R.id.my_gym_subscription);

        this.mRegistrationDateImageButton.setOnClickListener(this);
        this.mGymRegistrationTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.my_gym_registration || view.getId() == R.id.my_gym_registration_date_picker) {
            Toast.makeText(MyGymActivity.this, "Registration clicked",Toast.LENGTH_SHORT).show();
        }
    }
}
