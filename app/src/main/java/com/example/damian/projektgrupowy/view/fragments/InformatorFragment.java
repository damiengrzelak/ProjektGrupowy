package com.example.damian.projektgrupowy.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.core.BaseFragment;

/**
 * Created by Damian on 12.09.2017.
 */

public class InformatorFragment extends BaseFragment {

    private WebView webView;
    public static InformatorFragment newInstance(){
        InformatorFragment informatorFragment = new InformatorFragment();
        return informatorFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_informator, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        webView = (WebView) view.findViewById(R.id.fragment_informator_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl("http://www.mz.gov.pl/zdrowie-i-profilaktyka/krwiodawstwo/");
    }
}
