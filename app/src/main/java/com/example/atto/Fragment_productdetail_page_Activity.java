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
import java.util.HashMap;
import java.util.List;

public class Fragment_productdetail_page_Activity extends Fragment {
    ImageView productImage;
    ImageButton scrapBtn;
    Button productPageBtn;
    TextView brandField, productNameField, priceField;

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
        productList = productDao.getAll();
        scrapBtn = fv.findViewById(R.id.scrapBtn);
        productPageBtn = fv.findViewById(R.id.productPageBtn);
        brandField = fv.findViewById(R.id.brandField);
        productNameField = fv.findViewById(R.id.productNameField);
        priceField = fv.findViewById(R.id.priceField);

        int productId = getArguments().getInt("id");
        ProductWithBrandName matchingProduct = productList.get(productId - 1); // 배열 인덱스로 상품 찾기
        if(productId != matchingProduct.id) { // 배열 인덱스로 찾았을 때 id 값이 일치하지 않을 때
            for (ProductWithBrandName product : productList) {
                if (product.id == productId) matchingProduct = product; // Fragment_market_page_Activity에서 전달받은 id 값과 일치하는 상품 찾기
            }
        }
        Glide.with(getActivity()).load(matchingProduct.photoURL).into(productImage);
        brandField.setText(matchingProduct.brandName); // 브랜드명
        productNameField.setText(matchingProduct.name); // 상품명
        priceField.setText(Integer.toString(matchingProduct.price) + "원"); // 가격

        // 스크랩 버튼 페이지로 연결
        scrapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MypageScrapActivity.class);
                //intent.putExtra() // 스크랩 페이지로 전달해줄 값 설정
                startActivity(intent);
            }
        });

        // 상품 페이지로 연결
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
