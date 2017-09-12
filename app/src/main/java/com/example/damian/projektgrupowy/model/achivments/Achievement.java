package com.example.damian.projektgrupowy.model.achivments;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;

/**
 * Created by Damian on 12.09.2017.
 */

public class Achievement {
    @Column
    @SerializedName("firstBlood")
    int firstBlood;

    @Column
    @SerializedName("firstLitr")
    int firstLitr;

    @Column
        @SerializedName("helpingHand")
    int helpingHand;

    @Column
    @SerializedName("honorPerson")
    int honorPerson;

    public int getFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(int firstBlood) {
        this.firstBlood = firstBlood;
    }

    public int getFirstLitr() {
        return firstLitr;
    }

    public void setFirstLitr(int firstLitr) {
        this.firstLitr = firstLitr;
    }

    public int getHelpingHand() {
        return helpingHand;
    }

    public void setHelpingHand(int helpingHand) {
        this.helpingHand = helpingHand;
    }

    public int getHonorPerson() {
        return honorPerson;
    }

    public void setHonorPerson(int honorPerson) {
        this.honorPerson = honorPerson;
    }
}
