package com.example.intern06.lifereminder.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by intern06 on 18.05.2017.
 */

public class ButtonFont extends android.support.v7.widget.AppCompatButton {
    public ButtonFont(Context context) {
        super(context);
        setfont(context);
    }

    public ButtonFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setfont(context);
    }

    public ButtonFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setfont(context);
    }

   public void setfont(Context context){
       Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf");
       this.setTypeface(typeface);
   }
}
