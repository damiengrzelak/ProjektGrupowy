package com.example.damian.projektgrupowy.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.core.BaseFragment;

/**
 * Created by Damian on 18.09.2017.
 */

public class SymulationFragment extends BaseFragment implements View.OnClickListener{
    public static SymulationFragment newInstance() {
        return new SymulationFragment();
    }

    TextView no, yes;
    ImageView photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symulation, container, false);
        findViews(view);
        setListeners();

        return view;
    }

    private void setListeners() {
        no.setOnClickListener(this);
        yes.setOnClickListener(this);
    }

    private void findViews(View view) {
        no = (TextView) view.findViewById(R.id.fragment_symulation_no_content);
        yes = (TextView) view.findViewById(R.id.fragment_symulation_yes_content);
        photo = (ImageView) view.findViewById(R.id.fragment_symulation_photo);

        Glide.with(getContext())
                .load("http://fotoforum.gazeta.pl/photo/2/tb/hb/smrb/9G7I71UCclmhp6jLJB.jpg") // Image URL
                .centerCrop() // Image scale type
                .crossFade()
                .into(photo); // ImageView to display image
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_symulation_no_content:
                popFragmentFromBackstack();
                Toast.makeText(getContext(), "Mamy nadzieję, że kolejnym razem uda Ci się pomóc", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_symulation_yes_content:
                MapFragment mapFragment = new MapFragment();
                mapFragment.isFromNotification(true);
                setFragment(mapFragment, false);
                break;
        }
    }
}
