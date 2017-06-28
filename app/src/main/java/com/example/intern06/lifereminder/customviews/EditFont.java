package com.example.intern06.lifereminder.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by intern06 on 18.05.2017.
 */

public class EditFont extends android.support.v7.widget.AppCompatEditText {
    public EditFont(Context context) {
        super(context);
        setfont(context);
    }

    public EditFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setfont(context);
    }

    public EditFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setfont(context);
    }

    public void setfont(Context context){
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf");
        this.setTypeface(typeface);
    }
}
