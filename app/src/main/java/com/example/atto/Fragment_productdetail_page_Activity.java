package com.example.atto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.Product;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Fragment_productdetail_page_Activity extends Fragment {
    ImageView productImage;
    ImageButton scrapBtn;
    Button productPageBtn;
    TextView scrapBtnText;

    private List<ProductWithBrandName> productList;

    public Fragment_productdetail_page_Activity() {
        //productList = new Product();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.activity_fragment_productdetail_page, container, false);
        productImage = fv.findViewById(R.id.productImageView);
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();
        productList = productDao.getAll(); ////
        scrapBtn = fv.findViewById(R.id.scrapBtn);
        scrapBtnText = fv.findViewById(R.id.scrapBtnText);
        productPageBtn = fv.findViewById(R.id.productPageBtn);

        int productId = getArguments().getInt("id");
        Glide.with(getActivity()).load(productList.get(productId - 1).photoURL).into(productImage);

        scrapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MypageScrapActivity.class);
                //intent.putExtra() // 스크랩 페이지로 전달해줄 값 설정
                startActivity(intent);
            }
        });

        scrapBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MypageScrapActivity.class);
                //intent.putExtra() // 스크랩 페이지로 전달해줄 값 설정
                startActivity(intent);
            }
        });


        productPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productList.get(productId - 1).siteURL));
                startActivity(intent);
            }
        });

        return fv;
    }
}
