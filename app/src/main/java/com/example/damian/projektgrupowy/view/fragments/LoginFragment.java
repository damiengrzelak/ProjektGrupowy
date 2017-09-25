package com.example.damian.projektgrupowy.view.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.damian.projektgrupowy.App;
import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.firebase.MyFirebaseMessagingService;
import com.example.damian.projektgrupowy.model.accounts.Person;
import com.example.damian.projektgrupowy.model.accounts.Persons;
import com.example.damian.projektgrupowy.retrofit.Rest;
import com.example.damian.projektgrupowy.view.custom.FontAwesome;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Damian on 08.04.2017.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private Button loginButton;
    private EditText login;
    private EditText password;
    private TextView eyeShowPassword;
    private boolean isClicked = false;
    private List<Person> user;
    private boolean fromNotification;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        findViews(view);
        initDataBase();
        setListeners();

        return view;
    }

    private void initDataBase() {
        Rest.init();
        Rest.getAppService().loadData().enqueue(new Callback<Persons>() {

            @Override
            public void onResponse(Call<Persons> call, Response<Persons> response) {
                user = response.body().getLoadData();

                if (user == null) {
                    Toast.makeText(getContext(), "Data is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Persons> call, Throwable t) {
                if (user == null) {
                    Toast.makeText(getContext(), "Error during download data :( ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findViews(View view) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");
        loginButton = (Button) view.findViewById(R.id.fragment_login_button);
        login = (EditText) view.findViewById(R.id.fragment_login_login);
        password = (EditText) view.findViewById(R.id.fragment_login_password);
        eyeShowPassword = (TextView) view.findViewById(R.id.fragment_login_eye_icon);
        password.setText("test");
        login.setText("test");
        eyeShowPassword.setTypeface(font);
    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
        eyeShowPassword.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActions() != null) {
            // getActions().getBottomBar().setVisibility(false);
            // getActions().enableLeftMenuSwype(false);
            getActions().getTopBar().setVisibility(false);
            getActions().getTopBar().setBackButtonVisibility(false);
            // getActions().getTopBar().setLogoClickable(false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActions() != null) {
//            getActions().getBottomBar().setVisibility(true);
            getActions().getTopBar().setVisibility(false);
//            getActions().enableLeftMenuSwype(true);
            getActions().getTopBar().setBackButtonVisibility(false);
//            getActions().getTopBar().setNotificationButtonVisibility(true);
//            getActions().getTopBar().setLogoClickable(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_login_button:
                logIn();
                break;
            case R.id.fragment_login_eye_icon:
                checkStatus(isClicked);
                showOrHidePassword(isClicked);
                break;
        }
    }

    private void logIn() {
        boolean isOk = false;

        for (Person p : user) {
            if (p.getLogin().equals(login.getText().toString()) && p.getPassword().equals(password.getText().toString())) {
                Person loggedPerson = p;

                DesktopFramgment desktopFramgment = new DesktopFramgment();
                desktopFramgment.setUser(loggedPerson);

                if(fromNotification){
                    setFragment(new MapFragment(),false);
                    isOk = true;
                } else {
                setFragment(desktopFramgment, false);
                isOk = true;}

            }

        }
        if(!isOk){
            onLogInFailed();
        }

    }

    private void onLogInFailed() {
        Toast.makeText(getContext(), R.string.wrong_login_data, Toast.LENGTH_SHORT).show();
    }

    private boolean checkStatus(boolean isClicked) {
        if (!isClicked) {
            this.isClicked = true;
        } else {
            this.isClicked = false;
        }

        return this.isClicked;
    }

    private void showOrHidePassword(boolean isClicked) {
        if (isClicked) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        password.setSelection(password.length());
    }

    public void setFromNotification(boolean showFromNotification) {
        this.fromNotification = showFromNotification;
    }
}
