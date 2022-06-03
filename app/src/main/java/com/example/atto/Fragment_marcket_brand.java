package com.example.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        for (Brand brand : brandList) {

            //브랜드 출력
            TextView btn = new TextView(getActivity().getApplicationContext(), null, android.R.attr.textAppearanceMedium);
            btn.setBackgroundColor(Color.LTGRAY);
            btn.setText("[" + brand.name + "]");
            btn.setGravity(Gravity.CENTER);
            btn.setTextSize(14);
            btn.setPadding(16, 16, 16, 16);  //패딩

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15, 15, 15, 0);
            btn.setLayoutParams(params);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), BrandActivity.class);
                    intent.putExtra("brandId", brand.id);
                    startActivity(intent);
                }
            });

            lineartable.addView(btn);
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