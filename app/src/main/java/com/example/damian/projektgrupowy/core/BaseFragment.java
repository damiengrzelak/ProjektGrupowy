package com.example.damian.projektgrupowy.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.damian.projektgrupowy.model.interfaces.ActivityInteractions;

public class BaseFragment extends Fragment {
    ActivityInteractions activityInteractions;

    @Nullable
    public ActivityInteractions getActions() {
        return activityInteractions;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activityInteractions = (ActivityInteractions) context;

        } catch (Exception e) {
            throw new IllegalStateException("Activity must implement correct action interface");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        activityInteractions = null;
    }
    public BaseFragment addArgumentInt(String name, int value) {
        Bundle args = getArguments() != null ? getArguments() : new Bundle();
        args.putInt(name, value);
        setArguments(args);
        return this;
    }

    public BaseFragment addArgumentString(String name, String value) {
        Bundle args = getArguments() != null ? getArguments() : new Bundle();
        args.putString(name, value);
        setArguments(args);
        return this;
    }

    public int getArgumentInt(String name, int fallback) {
        Bundle args = getArguments();
        return args == null ? fallback : args.getInt(name, fallback);
    }

    public String getArgumentString(String name, String fallback) {
        Bundle args = getArguments();
        return args == null ? fallback : args.getString(name, fallback);
    }
}
