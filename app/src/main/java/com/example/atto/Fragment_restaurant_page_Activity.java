package com.example.atto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_restaurant_page_Activity extends Fragment{

    public Fragment_restaurant_page_Activity(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.activity_fragment_restaurant_page, container, false);

        WebView webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/maps/d/u/0/viewer?ie=UTF8&hl=ko&msa=0&ll=37.80435926012475%2C126.97654766699219&spn=0.379134%2C0.617294&z=9&mid=11rmzgn_-gXKrRKPPVEhJfScfqJ8");

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() { //뒤돌아가기 버튼 -> 마켓 페이지로 이동
                // Handle the back button event
                Intent HomeActivity = new Intent(getActivity(), HomeActivity.class);
                HomeActivity.addCategory(Intent.CATEGORY_HOME);
                HomeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(HomeActivity);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return view;
    }

}