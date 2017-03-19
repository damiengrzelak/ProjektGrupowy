package com.example.damian.projektgrupowy.model.interfaces;

/**
 * Created by Damian on 18.03.2017.
 */

public interface BottomBarInteractions {
    void select(@BottomIcon int icon);

    @BottomIcon
    int getSelected();
}
