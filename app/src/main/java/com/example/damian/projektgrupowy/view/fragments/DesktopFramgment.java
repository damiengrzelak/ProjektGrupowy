package com.example.damian.projektgrupowy.view.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.model.accounts.Person;

import org.w3c.dom.Text;

/**
 * Created by Damian on 14.04.2017.
 */

public class DesktopFramgment extends BaseFragment {

    Person user = new Person();
    public static DesktopFramgment newInstance(){
        return BaseFragment.newBuilder(DesktopFramgment.class)
                .done();
    }

    TextView helloText;
    TextView informatorButton, myProfile, myAchivemenets, mapButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void setUser(Person loggedUser){
        this.user = loggedUser;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        findViews(view);
        setTypeface();
        fillViewsWithData();

        return view;
    }
    public void findViews(View view){
        helloText = (TextView) view.findViewById(R.id.hello_text);
        informatorButton = (TextView) view.findViewById(R.id.desktop_fragment_heart);
        myProfile = (TextView) view.findViewById(R.id.desktop_fragment_my_profile);
        myAchivemenets = (TextView) view.findViewById(R.id.desktop_fragment_achivements);
        mapButton = (TextView) view.findViewById(R.id.desktop_fragment_map);
    }
    public void setTypeface(){
        Typeface font = Typeface.createFromAsset( getContext().getAssets(), "fonts/fontawesome-webfont.ttf" );

        informatorButton.setTypeface(font);
        myProfile.setTypeface(font);
        myAchivemenets.setTypeface(font);
        mapButton.setTypeface(font);
    }
    public void fillViewsWithData(){
        helloText.setText(getResources().getText(R.string.hello_text)+" "+user.getName());
        informatorButton.setText(getResources().getText(R.string.font_awesome_heart));
        myProfile.setText(getResources().getText(R.string.font_awesome_profile));
        myAchivemenets.setText(getResources().getText(R.string.font_awesome_achivements));
        mapButton.setText(getResources().getText(R.string.font_awesome_map));
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getActions() != null) {
            getActions().getTopBar().setVisibility(true);
            getActions().getTopBar().setBackButtonVisibility(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActions() != null) {
            getActions().getTopBar().setVisibility(false);
            getActions().getTopBar().setBackButtonVisibility(true);
        }
    }
}
