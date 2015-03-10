package com.github.hotchpotch.iconicfontengine.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.github.hotchpotch.iconicfontengine.IconicFontEngine;

import java.util.ArrayList;

/**
 * Created by yuichi-tateno on 2015/03/10.
 */
public class IconicFontEditText extends EditText {
    public IconicFontEditText(Context context) {
        super(context);
    }

    public IconicFontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconicFontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(IconicFontEngine.apply(text), type);
    }

    public void setText(CharSequence text, BufferType type, ArrayList<IconicFontEngine> engines) {
        super.setText(IconicFontEngine.apply(text, engines), type);
    }

    public void setHintWithEngines(CharSequence hint) {
        super.setHint(IconicFontEngine.apply(hint));
    }

    public void setHintWithEngines(CharSequence hint, ArrayList<IconicFontEngine> engines) {
        super.setHint(IconicFontEngine.apply(hint, engines));
    }
}
