package com.github.hotchpotch.sampleiconicfontengine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.hotchpotch.iconicfontengine.widget.IconicFontTextView;

/**
* Created by yuichi-tateno on 2015/03/06.
*/
public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);
        IconicFontTextView textView = (IconicFontTextView) rootView.findViewById(R.id.iconic_font_text_view_html);
        textView.setText(Html.fromHtml("html example: <font color=#FF0000>{fa-github}</font><font color=#00FF00>{fa-github}</font><font color=#0000FF>{fa-github}</font>"));
        return rootView;
    }
}
