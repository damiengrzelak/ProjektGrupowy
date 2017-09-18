package com.example.damian.projektgrupowy.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.model.accounts.Person;
import com.example.damian.projektgrupowy.view.fragments.navigation.TopBarFragment;

/**
 * Created by Damian on 12.09.2017.
 */

public class MyProfileFragment extends BaseFragment {
    private static final String USER_KEY = "user_key";

    private ImageView photo;
    private TextView name, surname, email, address;

    public static MyProfileFragment newInstance(Person user){
        MyProfileFragment myProfile = new MyProfileFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER_KEY, user);
        myProfile.setArguments(args);

        return myProfile;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        findViews(view);
        fillViewsWithData();
        setTopBar();
        return view;
    }

    private void setTopBar() {
        //TopBarFragment topbar = TopBarFragment.newInstance();
        //topbar.setBackButtonVisibility(true);
    }

    public void findViews(View view){
        photo = (ImageView) view.findViewById(R.id.fragment_my_profile_user_photo);
        name = (TextView) view.findViewById(R.id.fragment_my_profile_name);
        surname = (TextView) view.findViewById(R.id.fragment_my_profile_surname);
        email = (TextView) view.findViewById(R.id.fragment_my_profile_email);
        address = (TextView) view.findViewById(R.id.fragment_my_profile_address);
    }

    public void fillViewsWithData(){
        Person p = getArgumentParcelable(USER_KEY);

        Glide.with(getContext())
                .load(p.getPhoto()) // Image URL
                .centerCrop() // Image scale type
                .crossFade()
                .into(photo); // ImageView to display image

        name.setText(name.getText()+" "+p.getName());
        surname.setText(surname.getText()+" "+p.getLastName());
        email.setText(email.getText()+" "+p.getEmail());
        address.setText(address.getText()+" "+p.getAdres());
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
