package com.example.intern06.lifereminder.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextFont extends android.support.v7.widget.AppCompatTextView {
    public TextFont(Context context) {
        super(context);
        setfont(context);
    }

    public TextFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setfont(context);
    }

    public TextFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setfont(context);
    }

    public void setfont(Context context){
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Light.ttf");
        this.setTypeface(typeface);
    }
}
