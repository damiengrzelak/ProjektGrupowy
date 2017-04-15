package com.example.damian.projektgrupowy.model.interfaces;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Damian on 14.04.2017.
 */

public interface TitleCardViewListener {
    void onItemSelected(AdapterView<?> parent, View view, int position, long id);
    void onNothingSelected(AdapterView<?> parent);
}
