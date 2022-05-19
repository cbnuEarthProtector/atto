package com.example.atto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.atto.database.AppDatabase;
import com.example.atto.database.Product;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class Fragment_marcket_page_Activity extends Fragment {
    private TableLayout tableLayout;
    ImageButton mybtn;
    Button categorybtn, brandbtn;

    public Fragment_marcket_page_Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.activity_fragment_marcket_page, container, false);

        //마이페이지 버튼 ->마이페이지로 이동
        mybtn = (ImageButton) fv.findViewById(R.id.mybtn);
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fragment에서 intent 호출할 땐 getActivity()사용
                Intent intent = new Intent(getActivity(), MypageActivity.class);
                startActivity(intent);
            }
        });

        categorybtn = (Button) fv.findViewById(R.id.categorybtn);
        brandbtn = (Button) fv.findViewById(R.id.brandbtn);
        //카테고리 별 상품보기 fragment 호출
        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment_marcket_category categoryFragment = new Fragment_marcket_category();
                transaction.replace(R.id.marcketframe, categoryFragment);
                transaction.commit();
            }
        });

        //브랜드별 상품보기 fragment 호출
        brandbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment_marcket_brand brandFragment = new Fragment_marcket_brand();
                transaction.replace(R.id.marcketframe, brandFragment);
                transaction.commit();
            }
        });

        //인기상품 출력
        tableLayout = (TableLayout) fv.findViewById(R.id.tablelayout);

        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();

        List<ProductWithBrandName> productWithBrandNameList = productDao.getAll();

        for (ProductWithBrandName productWithBrandName : productWithBrandNameList) {
            //테이블 레이아웃에 새로운 행 추가해서 상품 출력
            TableRow tableRow = new TableRow(getActivity().getApplicationContext());
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(productWithBrandName.name);
            textView.setGravity(Gravity.LEFT);
            textView.setTextSize(14);
            tableRow.addView(textView);

            TextView textView2 = new TextView(getActivity().getApplicationContext());
            textView2.setText(productWithBrandName.category);
            textView2.setGravity(Gravity.LEFT);
            textView2.setTextSize(14);
            tableRow.addView(textView2);

            TextView textView3 = new TextView(getActivity().getApplicationContext());
            if (productWithBrandName.price == -1) textView3.setText("품절");
            else textView3.setText(productWithBrandName.price + " $");
            textView3.setGravity(Gravity.LEFT);
            textView3.setTextSize(14);
            tableRow.addView(textView3);

            tableLayout.addView(tableRow);
        }

        return fv;
    }
}