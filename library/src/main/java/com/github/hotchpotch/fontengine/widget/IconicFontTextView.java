
package com.github.hotchpotch.fontengine.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.hotchpotch.fontengine.IconicFontEngine;

import java.util.ArrayList;

public class IconicFontTextView extends TextView {
    public static final String TAG = IconicFontTextView.class.getSimpleName();

    private ArrayList<IconicFontEngine> iconicFontEngines;
    public IconicFontTextView(Context context) {
        super(context);
    }

    public IconicFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconicFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        super.setText(IconicFontEngine.apply(text, getIconicFontEngineList()), type);
    }
}