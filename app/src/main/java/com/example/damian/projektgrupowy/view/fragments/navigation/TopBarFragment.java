package com.example.damian.projektgrupowy.view.fragments.navigation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.model.interfaces.TopBarInteractions;
import com.example.damian.projektgrupowy.view.fragments.DesktopFramgment;

/**
 * Created by Damian on 14.04.2017.
 */

public class TopBarFragment extends BaseFragment implements TopBarInteractions, View.OnClickListener {
    RelativeLayout root;
    RelativeLayout backIcon;
    private TextView goBackIcon;
    private TextView title;
    private ImageView logo;
    private boolean clickable;

    public static TopBarFragment newInstance() {
        return new TopBarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_bar, container, false);
        findViews(view);
        setListeners();

        return view;
    }
    private void findViews(View view) {
        Typeface font = Typeface.createFromAsset( getContext().getAssets(), "fonts/fontawesome-webfont.ttf" );
        logo = (ImageView) view.findViewById(R.id.fragment_top_bar_logo);
        root = (RelativeLayout) view.findViewById(R.id.fragment_top_bar_root);
        backIcon = (RelativeLayout) view.findViewById(R.id.fragment_top_bar_menu_wrapper);
        goBackIcon = (TextView) view.findViewById(R.id.fragment_top_bar_menu_back);
        title = (TextView) view.findViewById(R.id.fragment_top_bar_title);
        goBackIcon.setTypeface(font);
    }

    private void setListeners() {
        logo.setOnClickListener(this);
        backIcon.setOnClickListener(this);
        title.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (getActions() == null) return;

        switch (v.getId()) {
            case R.id.fragment_top_bar_menu_wrapper:
                getActions().backPress();
                break;
            case R.id.fragment_top_bar_logo:
                if (clickable) {
                    setFragment(DesktopFramgment.newInstance(), true);
                }
                break;
        }

    }

    @Override
    public void showBackIcon(boolean visible) {

    }

    @Override
    public void showLogoutIcon(boolean visible) {

    }

    @Override
    public void setTitle(@StringRes int res) {

    }

    @Override
    public void setVisibility(boolean visibility) {
        if (visibility) {
            root.setVisibility(View.VISIBLE);
        } else {
            root.setVisibility(View.GONE);
        }

    }

    @Override
    public void setBackButtonVisibility(boolean visibility) {
        if (visibility) {
            backIcon.setVisibility(View.VISIBLE);
        } else {
            backIcon.setVisibility(View.GONE);
        }
    }
}
