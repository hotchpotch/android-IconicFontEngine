package com.github.hotchpotch.fontengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuichi-tateno on 2015/02/28.
 */
public class IconicFontMap {
    private HashMap<String, Character> fontMap;

    public IconicFontMap() {
        super();
        fontMap = new HashMap<String, Character>();
    }

    public IconicFontMap put(String key, Character value) {
        fontMap.put(key, value);
        return this;
    }

    public Character get(String key) {
        return fontMap.get(key);
    }

    public List<String> list() {
        return new ArrayList<String>(fontMap.keySet());
    }
}
