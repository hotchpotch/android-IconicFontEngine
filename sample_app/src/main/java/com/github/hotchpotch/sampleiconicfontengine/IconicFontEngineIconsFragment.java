package com.github.hotchpotch.sampleiconicfontengine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.github.hotchpotch.iconicfontengine.IconicFontEngine;
import com.github.hotchpotch.iconicfontengine.widget.IconicFontTextView;

/**
* Created by yuichi-tateno on 2015/03/06.
*/
public class IconicFontEngineIconsFragment extends Fragment {
    private static final String ENGINE_POSITION = "engine_position";

    public static IconicFontEngineIconsFragment newInstance(int position) {
        IconicFontEngineIconsFragment fragment = new IconicFontEngineIconsFragment();
        Bundle args = new Bundle();
        args.putInt(ENGINE_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public IconicFontEngineIconsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView rootView = (ScrollView) inflater.inflate(R.layout.iconic_font_engine_icons_fragment, container, false);
        LinearLayout iconicContainer = (LinearLayout) rootView.findViewById(R.id.iconic_container);
        int enginePosition = getArguments().getInt(ENGINE_POSITION);
        IconicFontEngine engine = IconicFontEngine.getDefaultEngines().get(enginePosition);
        int position = 0;
        for (String iconName: engine.getIconicFontMap().allNames()) {
            LinearLayout view = (LinearLayout) inflater.inflate(R.layout.iconic_view_layout, container, false);
            IconicFontTextView tv = (IconicFontTextView) view.findViewById(R.id.iconic_font_text_view);
            tv.setText("{" + iconName + "} " + iconName);
            view.setBackgroundResource(position % 2 == 0 ? R.color.background_even : R.color.background_odd);
            iconicContainer.addView(view);
            position++;
        }
        return rootView;
    }
}
