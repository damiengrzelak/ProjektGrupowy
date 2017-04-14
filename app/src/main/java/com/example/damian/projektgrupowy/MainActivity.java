package com.example.damian.projektgrupowy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.damian.projektgrupowy.core.BaseActivity;
import com.example.damian.projektgrupowy.view.custom.CustomDrawer;
import com.example.damian.projektgrupowy.view.fragments.navigation.TopBarFragment;
import com.inverce.mod.core.IM;
import com.inverce.mod.events.Event;

import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.interfaces.ActivityActions;
import com.example.damian.projektgrupowy.model.interfaces.ActivityInteractions;
import com.example.damian.projektgrupowy.model.interfaces.BottomBarInteractions;
import com.example.damian.projektgrupowy.model.interfaces.TopBarInteractions;
import com.example.damian.projektgrupowy.view.fragments.LoginFragment;

public class MainActivity extends BaseActivity implements ActivityActions{

    private CustomDrawer drawerLayout;
    private FrameLayout leftDrawer;
    private Fragment leftMenuFragment;
    private RelativeLayout smallProgressView, bigProgressView;

    private ViewGroup layoutRoot;

    public static Event<ActivityActions> actions() {
        return Event.Bus.event(ActivityActions.class);
    }

    TopBarInteractions topBar;
    BottomBarInteractions bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(LoginFragment.newInstance(), false);

        drawerLayout = (CustomDrawer) findViewById(R.id.drawer_layout);
        //drawerLayout.setScrimColor(ContextCompat.getColor(App.getAppContext(), R.color.transparent));

        //leftDrawer = (FrameLayout) findViewById(R.id.left_drawer);
        layoutRoot = (FrameLayout) findViewById(R.id.root_layout);

        setDrawerFullWidth();

        if (savedInstanceState != null) {
            navBarAfterSavedInstance();
        } else {
            navigationBar();
        }

        addViewTreeObserver();

//        //TODO: DODAC 2 LAYOUTY DLA TOP AND BOTTOM BAROW
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        //topBar = (TopBarInteractions) fragmentManager.findFragmentById(R.id.top_fragment);
//        //bottomBar = (BottomBarInteractions) fragmentManager.findFragmentById(R.id.bottom_fragment);
//        fragmentManager.addOnBackStackChangedListener(this);
//
//        //TODO USTAWIC NAWIGACJE DO PIERWSZEGO STARTOWEGO FRAGMENTU
//        if (savedInstanceState == null) {
//           // navigateTo(InformationFragment.newInstance(), false);
//        }
    }

    @Override
    public void setFragment(BaseFragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public void popFragmentFromBackstack() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    @Override
    public TopBarInteractions getTopBar() {
        return topBar;
    }

    @Override
    public void removeBackstack() {
        FragmentManager fm = this.getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void backPress() {
        super.onBackPressed();
        }
    @Override
    protected void onStart() {
        super.onStart();
        Event.Bus.register(ActivityActions.class, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Event.Bus.unregister(ActivityActions.class, this);
    }

    public void navigationBar() {
        TopBarFragment fragment = TopBarFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.top_bar, fragment, "nav");
        transaction.commit();
        topBar = fragment;
    }
    void setDrawerFullWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) leftDrawer.getLayoutParams();
        //params.width = metrics.widthPixels;
       // leftDrawer.setLayoutParams(params);
    }
    private void navBarAfterSavedInstance() {
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(R.id.top_bar);
        topBar = (TopBarInteractions) fragment;
    }

    public void addViewTreeObserver() {
        layoutRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            Point screen = new Point(480, 640);
            Rect rect = new Rect();
            int lastBottom = 0, lastSize = 0;

            @Override
            public void onGlobalLayout() {
                Context context = IM.activity();
                if (context instanceof Activity) {
                    Window window = ((Activity) context).getWindow();
                    View rootView = window.getDecorView();
                    if (rootView != null) {
                        rootView.getWindowVisibleDisplayFrame(rect);
                        if (lastBottom != rect.bottom) {
                            //screen = Tools.Screen.getScreenSize();

                            //Log.v("Bottom size Hidden %d / %d", (screen.y - rect.bottom), lastSize);
//                            Event.Bus
//                                    .post(OnKeyboardSizeChanged.class)
//                                    .softInputSizeChanged(screen.y - rect.bottom, lastSize);
//                            lastBottom = rect.bottom;
//                            lastSize = screen.y - rect.bottom;
                        }
                    }
                }
            }
        });
    }









    //Provide util interfaces

//     @Override
//    public TopBarInteractions topBar() {
//       return topBar;
//    }
//
//    @Override
//    public BottomBarInteractions bottomBar() {
//        return bottomBar;
//    }
//
//    @Override
//    public boolean navigateTo(BaseFragment fragment, boolean addToBackstack) {
//        FragmentManager manager = getSupportFragmentManager();
//
//        if (fragment == null || manager == null) {
//            return false;
//        }
//
//        // Prevent adding same page twice
//        if (manager.getFragments() != null) {
//            Fragment current = manager.findFragmentById(R.id.activity_main);
//            if (current != null && fragment.getClass().equals(current.getClass())) {
//                return false;
//            }
//        }
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.activity_main, fragment);
//        if (addToBackstack) {
//            transaction.addToBackStack(fragment.toString());
//        }
//        transaction.commit();
//        return true;
//    }
//
//    @Override
//    public boolean navigateBack() {
//        onBackPressed();
//        return true;
//    }
//
//    @Override
//    public void onBackStackChanged() {
//        topBar().showBackIcon(getSupportFragmentManager().getBackStackEntryCount() > 0);
//    }

}
