package com.example.damian.projektgrupowy.model.interfaces;

import android.support.annotation.StringRes;

public interface TopBarInteractions {
    void showBackIcon(boolean visible);
    void showLogoutIcon(boolean visible);
    void setTitle(@StringRes int res);
    void setVisibility(boolean visibility);
    void setBackButtonVisibility(boolean visibility);
}
