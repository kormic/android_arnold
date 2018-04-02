package gr.komic.arnold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class BodyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.more_menu);
    }
}
