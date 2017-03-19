package com.example.damian.projektgrupowy.model.interfaces;

import com.example.damian.projektgrupowy.core.BaseFragment;

public interface ActivityInteractions {
    boolean navigateTo(BaseFragment fragment, boolean addToBackstack);
    boolean navigateBack();
    TopBarInteractions topBar();
    BottomBarInteractions bottomBar();
}
