package gr.komic.arnold;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_home);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.options);
        mToolbar.inflateMenu(R.menu.more_menu);

    }

    public void navigate(View view) {
        Log.d("Navigate to view: ",String.valueOf(view.getId()));
        switch (view.getId()) {
            case R.id.myBody_button:
                Intent intentBody = new Intent(this, BodyActivity.class);
                startActivity(intentBody);
                break;
            case R.id.myGym_Button:
                Intent intentGym = new Intent(this, MyGymActivity.class);
                startActivity(intentGym);
                break;
        }
    }
}
