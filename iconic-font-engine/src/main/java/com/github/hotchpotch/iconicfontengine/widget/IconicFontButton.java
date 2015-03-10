package com.github.hotchpotch.iconicfontengine.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.github.hotchpotch.iconicfontengine.IconicFontEngine;

import java.util.ArrayList;

/**
 * Created by yuichi-tateno on 2015/03/06.
 */
public class IconicFontButton extends Button {
    private ArrayList<IconicFontEngine> iconicFontEngines;
    public IconicFontButton(Context context) {
        super(context);
    }
    public IconicFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public IconicFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IconicFontButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setIconicFontEngines(ArrayList<IconicFontEngine> engines) {
        this.iconicFontEngines = engines;
    }

    public ArrayList<IconicFontEngine> getIconicFontEngineList() {
        if (this.iconicFontEngines == null) {
            return IconicFontEngine.getDefaultEngines();
        } else {
            return this.iconicFontEngines;
        }
    }
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(IconicFontEngine.apply(text, getIconicFontEngineList()), TextView.BufferType.SPANNABLE);
    }
}
