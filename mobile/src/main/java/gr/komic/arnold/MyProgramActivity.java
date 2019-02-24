package gr.komic.arnold;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import gr.komic.arnold.Adapters.ViewPagerAdapter;
import gr.komic.arnold.Fragments.MyProgramFragment;
import gr.komic.arnold.Fragments.ProgramCreationFragment;


public class MyProgramActivity extends AppCompatActivity implements MyProgramFragment.OnFragmentInteractionListener, ProgramCreationFragment.OnFragmentInteractionListener {
    private static final String TAG = "MyProgramActivity";

    TabLayout tabLayout;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_program_container);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabs);
        vp = findViewById(R.id.viewpager);
        setupViewPager(vp);
        tabLayout.setupWithViewPager(vp);
    }

    public void setupViewPager(ViewPager vp) {
        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.addFragment(new MyProgramFragment(), "Current");
        vpa.addFragment(new ProgramCreationFragment(), "New");
        vp.setAdapter(vpa);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction: " + uri.getEncodedPath());
    }
}
