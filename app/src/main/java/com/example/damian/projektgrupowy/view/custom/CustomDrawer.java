package com.example.damian.projektgrupowy.view.custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.damian.projektgrupowy.MainActivity;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Screen;

/**
 * Created by Damian on 08.04.2017.
 */

public class CustomDrawer extends DrawerLayout {

    DrawerListener drawerListener;
    Rect drawerSize;

    public CustomDrawer(Context context) {
        super(context);
        init();
    }

    public CustomDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        drawerListener = new DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                bringToFront();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
               // Tools.hideSoftInput(drawerView);
                bringToFront();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //noinspection WrongConstant
               // MainActivity.actions().post().getBottomBar().setSelection(BottomBarFragment.getSavedBottomIcon());
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        };
        this.addDrawerListener(drawerListener);
        drawerSize = new Rect(Screen.dpToPx(0), Screen.dpToPx(50), Screen.getScreenSize().x, Screen.getScreenSize().y - Screen.dpToPx(50));
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!drawerSize.contains((int) ev.getX(), (int) ev.getY()) /*&& MainActivity.actions().post().getLeftMenuVisibility()*/) {
           // IM.onUi().execute(this::closeDrawers);
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }
}
