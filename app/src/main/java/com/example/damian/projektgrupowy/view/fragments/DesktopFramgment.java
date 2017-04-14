package com.example.damian.projektgrupowy.view.fragments;

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
        fillViewsWithData();

        return view;
    }
    public void findViews(View view){
        helloText = (TextView) view.findViewById(R.id.hello_text);
    }
    public void fillViewsWithData(){
        helloText.setText(getResources().getText(R.string.hello_text)+" "+user.getName());
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
