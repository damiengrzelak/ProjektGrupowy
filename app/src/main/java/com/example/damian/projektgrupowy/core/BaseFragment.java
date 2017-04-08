package com.example.damian.projektgrupowy.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.damian.projektgrupowy.interfaces.ActivityActions;
import com.example.damian.projektgrupowy.model.interfaces.ActivityInteractions;

//public class BaseFragment extends Fragment {
//    ActivityInteractions activityInteractions;
//
//    @Nullable
//    public ActivityInteractions getActions() {
//        return activityInteractions;
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            activityInteractions = (ActivityInteractions) context;
//
//        } catch (Exception e) {
//            throw new IllegalStateException("Activity must implement correct action interface");
//        }
//    }
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        activityInteractions = null;
//    }
//    public BaseFragment addArgumentInt(String name, int value) {
//        Bundle args = getArguments() != null ? getArguments() : new Bundle();
//        args.putInt(name, value);
//        setArguments(args);
//        return this;
//    }
//
//    public BaseFragment addArgumentString(String name, String value) {
//        Bundle args = getArguments() != null ? getArguments() : new Bundle();
//        args.putString(name, value);
//        setArguments(args);
//        return this;
//    }
//
//    public int getArgumentInt(String name, int fallback) {
//        Bundle args = getArguments();
//        return args == null ? fallback : args.getInt(name, fallback);
//    }
//
//    public String getArgumentString(String name, String fallback) {
//        Bundle args = getArguments();
//        return args == null ? fallback : args.getString(name, fallback);
//    }
//}
public class BaseFragment extends com.inverce.mod.navigation.BaseFragment<ActivityActions> {
    ActivityActions actions;

    @Nullable
    @Override
    public ActivityActions getActions() {
        return actions;
    }

    @Override
    public void onResume() {
        super.onResume();

//        if (!(this instanceof TopBarFragment || this instanceof BottomBarFragment)) {
//            if (getActions() != null && getActions().getBottomBar() != null) {
//                getActions().getBottomBar().setSelection(getFragmentIcon());
//            }
//        }
    }

//    @BottomIcon
//    public int getFragmentIcon() {
//        return BottomIcon.NONE;
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.w("BaseFragments", "Attaching " + this.getClass().getSimpleName() + " to " + context.getClass().getSimpleName());
        try {
            actions = (ActivityActions) context;
        } catch (Exception ex) {
            throw new IllegalStateException("Activity not implementing interface", ex);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        actions = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActions() != null) {
            hideProgressView();
//            if (getActions().getLeftMenu() != null) {
//                getActions().getLeftMenu().setActiveFragment(LeftMenu.NONE);
//            }
//            Tools.hideSoftInput(getView());
        }
    }

    public void showProgressView(boolean hasData) {
//        if (getActions() != null) {
//            getActions().startProgress(hasData);
//        }
    }

    public void hideProgressView() {
//        if (getActions() != null) {
//            getActions().setProgressViewVisibility(false);
//            getActions().finishProgress();
//        }
    }

    public void setFragment(BaseFragment fragment, boolean addToBackstack) {
        if (getActions() != null) {
            getActions().setFragment(fragment, addToBackstack);
        }
    }

    public void popFragmentFromBackstack() {
        if (getActions() != null) {
            getActions().popFragmentFromBackstack();
        }
    }

//    public void setLeftMenuActiveIcon(@LeftMenu int leftMenu) {
//        if (getActions() != null) {
//            getActions().getLeftMenu().setActiveFragment(leftMenu);
//        }
//    }

//    public void setProgressType(Class clazz) {
//        if (SQLite.selectCountOf().from(clazz).count() > 0) {
//            showProgressView(true);
//        } else {
//            showProgressView(false);
//        }
//    }
}