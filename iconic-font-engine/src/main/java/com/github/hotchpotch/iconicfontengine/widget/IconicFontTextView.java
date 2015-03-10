
package com.github.hotchpotch.iconicfontengine.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.hotchpotch.iconicfontengine.IconicFontEngine;

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

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(IconicFontEngine.apply(text), type);
    }

    public void setTextWithEngines(CharSequence text, BufferType type, ArrayList<IconicFontEngine> engines) {
        super.setText(IconicFontEngine.apply(text, engines), type);
    }
}