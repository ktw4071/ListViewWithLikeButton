package com.example.encho.arrayadapter;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by Admin on 2016-07-08.
 */
public class TARAValueObject {
    public String memberName;
     public Drawable memberImage;
    public Drawable likeButton;
    public Integer count = 0;

    public TARAValueObject(String memberName, Drawable memberImage) {
        this.memberName = memberName;
        this.memberImage = memberImage;
    }
}
