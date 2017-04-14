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

    TextView test;

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

        test = (TextView) view.findViewById(R.id.textView);
        test.setText("Witaj "+user.getName());

        return view;
    }
}
