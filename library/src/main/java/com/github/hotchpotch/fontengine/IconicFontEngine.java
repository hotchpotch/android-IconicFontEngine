package com.github.hotchpotch.fontengine;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuichi-tateno on 2015/02/28.
 */
public class IconicFontEngine {
    public static final String TAG = IconicFontEngine.class.getSimpleName();
    private static ArrayList<IconicFontEngine> defaultEngines = new ArrayList<IconicFontEngine>();

    public static ArrayList<IconicFontEngine> getDefaultEngines() {
        return defaultEngines;
    }

    public static void addDefaultEngine(IconicFontEngine engine) {
        defaultEngines.add(engine);
    }

    static public CharSequence apply(Context context, CharSequence charSequence, ArrayList<IconicFontEngine> engines) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }

        if (charSequence instanceof Spanned) {
            String text = Html.toHtml((Spanned) charSequence);
            return Html.fromHtml(render(new StringBuilder((text)), engines).toString());
        }

        return render(new StringBuilder(charSequence), engines);
    }

    private Typeface typeface;

    public Typeface getTypeface() {
        return typeface;
    }

    private IconicFontMap iconicFontMap;

    public IconicFontMap getIconicFontMap() {
        return iconicFontMap;
    }


    public IconicFontEngine(Typeface typeface, IconicFontMap iconicFontMap) {
        this.typeface = typeface;
        this.iconicFontMap = iconicFontMap;
    }

    private static CharSequence render(StringBuilder text, ArrayList<IconicFontEngine> engines) {
        int caret = 0;
        List<Pair<Integer, IconicFontEngine>> positions = new ArrayList<>();
        while (true) {
            int startBracketIndex = text.indexOf("{", caret);
            int endBracketIndex = text.indexOf("}", startBracketIndex + 1);
            if (startBracketIndex == -1 || endBracketIndex == -1) { break; }

            String iconString = text.substring(startBracketIndex + 1, endBracketIndex);
            boolean found = false;
            for (IconicFontEngine engine : engines) {
                Character fontChar = engine.getIconicFontMap().get(iconString);
                if (fontChar != null) {
                    text = text.replace(startBracketIndex, endBracketIndex + 1, String.valueOf(fontChar));
                    positions.add(new Pair<>(startBracketIndex, engine));
                    caret = startBracketIndex + 1;
                    found = true;
                    break;
                }
            }
            if (!found) {
                Log.w(TAG, "{" + iconString + "} not fount in fontMaps");
                caret = endBracketIndex + 1;
            }
        }

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text);
        for (Pair<Integer, IconicFontEngine> pair : positions) {
            setSpan(pair.second.getTypeface(), builder, pair.first);
        }
        return builder;
    }

    private static void setSpan(Typeface typeface, SpannableStringBuilder builder, int startIndex) {
        builder.setSpan(new IconicFontTypefaceSpan(typeface), startIndex, startIndex + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    }
}
