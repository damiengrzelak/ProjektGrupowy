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

/**
 * Created by Damian on 12.09.2017.
 */

public class MyAchivementsFragment extends BaseFragment {
    private static final String USER_KEY = "user_key";

    private TextView fbIcon, flIcon, hhIcon, hmIcon, fbAchive, flAchive, hhAchive, hmAchive, fbTitle, flTitle, hhTitle, hmTitle;

    public static MyAchivementsFragment newInstance(Person user){
        MyAchivementsFragment myAchivementsFragment = new MyAchivementsFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER_KEY, user);
        myAchivementsFragment.setArguments(args);

        return myAchivementsFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_my_achivements, container, false);
        findViews(view);
        setTypeface();
        fillViewsWithData();

        return view;
    }

    public void findViews(View view){
        fbIcon = (TextView) view.findViewById(R.id.my_achivements_first_blood_icon);
        flIcon = (TextView) view.findViewById(R.id.my_achivements_first_litr_icon);
        hhIcon = (TextView) view.findViewById(R.id.my_achivements_helping_hand_icon);
        hmIcon = (TextView) view.findViewById(R.id.my_achivements_honor_icon);

        fbAchive = (TextView) view.findViewById(R.id.my_achivements_first_blood_achive);
        flAchive = (TextView) view.findViewById(R.id.my_achivements_first_litr_achive);
        hhAchive = (TextView) view.findViewById(R.id.my_achivements_helping_hand_achive);
        hmAchive = (TextView) view.findViewById(R.id.my_achivements_honor_achive);

        fbTitle = (TextView) view.findViewById(R.id.my_achivements_first_blood_text);
        flTitle = (TextView) view.findViewById(R.id.my_achivements_first_litr_text);
        hhTitle = (TextView) view.findViewById(R.id.my_achivements_helping_hand_text);
        hmTitle = (TextView) view.findViewById(R.id.my_achivements_honor_text);
    }

    public void fillViewsWithData(){
        fbIcon.setText(getResources().getText(R.string.font_awesome_first_blood));
        flIcon.setText(getResources().getText(R.string.font_awesome_first_litr));
        hhIcon.setText(getResources().getText(R.string.font_awesome_helping_hand));
        hmIcon.setText(getResources().getText(R.string.font_awesome_honor_man));

        checkAchivements();
    }

    private void checkAchivements() {
        Person p = getArgumentParcelable(USER_KEY);

        if(p.getAchievemens().getFirstBlood() >= 1){
            fbIcon.setTextColor(getResources().getColor(R.color.red_pepper));
            fbTitle.setTextColor(getResources().getColor(R.color.red_pepper));
            fbAchive.setText(getResources().getText(R.string.first_blood_achive));
        } else {
            fbAchive.setText(getResources().getText(R.string.first_blood_no_achive));
        }

        if(p.getAchievemens().getFirstLitr() >= 1000){
            flIcon.setTextColor(getResources().getColor(R.color.red_pepper));
            flTitle.setTextColor(getResources().getColor(R.color.red_pepper));
            flAchive.setText(getResources().getText(R.string.achive));
        } else {
            int current, toAchive;
            current = p.getAchievemens().getFirstLitr();
            toAchive = 1000-current;
            String text = String.format(
                    getResources().getString(R.string.no_achive),
                    current, 1000, toAchive);
            flAchive.setText(text);
        }

        if(p.getAchievemens().getFirstLitr() >= 3000){
            hhIcon.setTextColor(getResources().getColor(R.color.red_pepper));
            hhTitle.setTextColor(getResources().getColor(R.color.red_pepper));
            hhAchive.setText(getResources().getText(R.string.achive));
        } else {
            int current, toAchive;
            current = p.getAchievemens().getFirstLitr();
            toAchive = 3000-current;
            String text = String.format(
                    getResources().getString(R.string.no_achive),
                    current, 3000, toAchive);
            hhAchive.setText(text);
        }

        if(p.getAchievemens().getFirstLitr() >= 5000){
            hmIcon.setTextColor(getResources().getColor(R.color.red_pepper));
            hmTitle.setTextColor(getResources().getColor(R.color.red_pepper));
            hmTitle.setText(getResources().getText(R.string.achive));
        } else {
            int current, toAchive;
            current = p.getAchievemens().getFirstLitr();
            toAchive = 5000-current;
            String text = String.format(
                    getResources().getString(R.string.no_achive),
                    current, 5000, toAchive);
            hmAchive.setText(text);
        }

    }

    public void setTypeface(){
        Typeface font = Typeface.createFromAsset( getContext().getAssets(), "fonts/fontawesome-webfont.ttf" );

        fbIcon.setTypeface(font);
        flIcon.setTypeface(font);
        hhIcon.setTypeface(font);
        hmIcon.setTypeface(font);
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
