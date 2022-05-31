package com.example.atto;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.Brand;
import com.example.atto.database.BrandDao;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class Fragment_marcket_brand extends Fragment {

    private LinearLayout lineartable;

    public Fragment_marcket_brand() {
        super(R.layout.fragment_marcket_brand);
    }

    public void printAllCategory(View fv) {
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();
        BrandDao brandDao = appDatabase.brandDao();

        List<Brand> brandList = brandDao.getAll();

        printBrandsToView(fv, brandList);
    }

    public void printBrandsToView(View fv, List<Brand> brandList) {
        //카테고리별 상품 출력
        lineartable = (LinearLayout) fv.findViewById(R.id.lineartable);

        lineartable.removeAllViews();

        LinearLayout horlinear = new LinearLayout(getActivity().getApplicationContext());
        for (Brand brand : brandList) {
            //브랜드 별 정보 vertical layout으로 출력
            LinearLayout linearLayout = new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(50, 10, 30, 20);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            //브랜드 사진
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            String image_url = brand.photoURL;
            System.out.println("image_url = " + image_url);
            Glide.with(this).load(image_url).into(imageView);
            linearLayout.addView(imageView);

            //브랜드 명 출력
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText("[" + brand.name + "]");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setPadding(30, 20, 0, 0);  //패딩
            linearLayout.addView(textView);

            //한 줄에 브랜드 세 개씩 출력
            if (brand.id % 2 == 0) {
                horlinear = new LinearLayout(getActivity().getApplicationContext());
                lineartable.addView(horlinear);

                horlinear.addView(linearLayout);
            } else {
                horlinear.addView(linearLayout);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_marcket_brand, container, false);
        printAllCategory(fv);
        return fv;
    }
}