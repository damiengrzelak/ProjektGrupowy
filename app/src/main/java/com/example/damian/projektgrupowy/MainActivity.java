package com.example.damian.projektgrupowy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.model.interfaces.ActivityInteractions;
import com.example.damian.projektgrupowy.model.interfaces.BottomBarInteractions;
import com.example.damian.projektgrupowy.model.interfaces.TopBarInteractions;

public class MainActivity extends AppCompatActivity implements ActivityInteractions, FragmentManager.OnBackStackChangedListener{

    TopBarInteractions topBar;
    BottomBarInteractions bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: DODAC 2 LAYOUTY DLA TOP AND BOTTOM BAROW
        FragmentManager fragmentManager = getSupportFragmentManager();
        //topBar = (TopBarInteractions) fragmentManager.findFragmentById(R.id.top_fragment);
        //bottomBar = (BottomBarInteractions) fragmentManager.findFragmentById(R.id.bottom_fragment);
        fragmentManager.addOnBackStackChangedListener(this);

        //TODO USTAWIC NAWIGACJE DO PIERWSZEGO STARTOWEGO FRAGMENTU
        if (savedInstanceState == null) {
           // navigateTo(InformationFragment.newInstance(), false);
        }
    }

    //Provide util interfaces

     @Override
    public TopBarInteractions topBar() {
       return topBar;
    }

    @Override
    public BottomBarInteractions bottomBar() {
        return bottomBar;
    }

    @Override
    public boolean navigateTo(BaseFragment fragment, boolean addToBackstack) {
        FragmentManager manager = getSupportFragmentManager();

        if (fragment == null || manager == null) {
            //Log.w("BaseActivity", "Fragment navigation failed for %s", String.valueOf(fragment));
            return false;
        }

        // Prevent adding same page twice
        if (manager.getFragments() != null) {
            Fragment current = manager.findFragmentById(R.id.activity_main);
            if (current != null && fragment.getClass().equals(current.getClass())) {
                //Log.w("BaseActivity", "Fragment navigation failed, possible duplicate entry %s", String.valueOf(fragment));
                return false;
            }
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_main, fragment);
        if (addToBackstack) {
            transaction.addToBackStack(fragment.toString());
        }
        transaction.commit();
        return true;
    }

    @Override
    public boolean navigateBack() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackStackChanged() {
        topBar().showBackIcon(getSupportFragmentManager().getBackStackEntryCount() > 0);
    }
}
