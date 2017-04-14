package com.example.damian.projektgrupowy.interfaces;

import com.example.damian.projektgrupowy.core.BaseFragment;
import com.example.damian.projektgrupowy.model.interfaces.TopBarInteractions;
import com.inverce.mod.events.annotation.Listener;

/**
 * Created by Damian on 08.04.2
 */

public interface ActivityActions extends Listener {
    void setFragment(BaseFragment fragment, boolean addToBackstack);
    void popFragmentFromBackstack();
    void removeBackstack();
    void backPress();

    TopBarInteractions getTopBar();

   // BottomBarInteractions getBottomBar();

  //  LeftMenuInteractions getLeftMenu();

   // void setProgressViewVisibility(boolean visible);
  //  void startProgress(boolean hasData);
  //  void finishProgress();
  //  void hideLeftMenu();
 //   void showLeftMenu();
  //  boolean getLeftMenuVisibility();
  // void enableLeftMenuSwype(boolean enable);
}
