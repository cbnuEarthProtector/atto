package com.example.atto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
            //브랜드 정보 vertical layout으로 출력
            LinearLayout linearLayout = new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(100, 50, 30, 20);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            //브랜드 상품들 출력하는 intent
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), BrandActivity.class);
                    intent.putExtra("brandId", brand.id);
                    startActivity(intent);
                }
            });

            //브랜드 이미지 출력
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            if(brandList.indexOf(brand)==0){
                imageView.setImageResource(R.drawable.tone_28);
            }else if(brandList.indexOf(brand)==1){
                imageView.setImageResource(R.drawable.dr_noah);
            } else if (brandList.indexOf(brand) == 2) {
                imageView.setImageResource(R.drawable.nature_store);
            } else {
                imageView.setImageResource(R.drawable.dog);
            }
            ViewGroup.LayoutParams imgparams=new ViewGroup.LayoutParams(200, 200);
            imageView.setLayoutParams(imgparams);
            imageView.setBackgroundResource(R.drawable.imgview_border);

            linearLayout.addView(imageView);

            //브랜드 출력
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(brand.name);
            textView.setPaintFlags(textView.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setPadding(10, 20, 0, 0);  //패딩

            linearLayout.addView(textView);

            //한 줄에 상품 세 개씩 출력
            if (brand.id % 3 == 0) {
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