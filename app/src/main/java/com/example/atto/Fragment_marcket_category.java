package com.example.atto;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class Fragment_marcket_category extends Fragment {

    private LinearLayout lineartable;

    static Button prevChooseBtn = null;

    public Fragment_marcket_category() {

    }

    public void printAllProductByCategory(View fv, String category) {
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();

        List<ProductWithBrandName> productWithBrandNameList;
        if (category.equals("all")) productWithBrandNameList = productDao.getAll();
        else productWithBrandNameList = productDao.findAllByCategory(category);

        printProductToView(fv, productWithBrandNameList);
    }

    public void printProductToView(View fv, List<ProductWithBrandName> productWithBrandNameList) {
        TextView numOfProduct = fv.findViewById(R.id.numOfProduct);
        numOfProduct.setText(Integer.toString(productWithBrandNameList.size()));

        //카테고리별 상품 출력
        lineartable = (LinearLayout) fv.findViewById(R.id.lineartable);

        lineartable.removeAllViews();
        LinearLayout horlinear = new LinearLayout(getActivity().getApplicationContext());
        for (ProductWithBrandName productWithBrandName : productWithBrandNameList) {
            //상품 정보 vertical layout으로 출력
            LinearLayout linearLayout = new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(50, 10, 30, 20);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            //상품 사진
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            String image_url = productWithBrandName.photoURL;
            Glide.with(this).load(image_url).into(imageView);
            linearLayout.addView(imageView);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Productdetail_page_Activity.class);
                    intent.putExtra("id", productWithBrandName.id);
                    startActivity(intent);
                }
            });

            //상품 카테고리
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText("[" + productWithBrandName.category + "]");
            textView.setGravity(Gravity.LEFT);
            textView.setTextSize(14);
            textView.setPadding(30, 20, 0, 0);  //패딩
            linearLayout.addView(textView);

            //상품 정보
            TextView textView2 = new TextView(getActivity().getApplicationContext());
            textView2.setText(productWithBrandName.name);  //이름
            textView2.setGravity(Gravity.LEFT);
            textView2.setMaxLines(2);  //두 줄 출력
            textView2.setEms(9);  //한 줄에 글자 수
            textView2.setEllipsize(TextUtils.TruncateAt.END);  //말줄임표
            textView2.setTextSize(14);
            textView2.setPadding(30, 0, 0, 0);  //패딩
            linearLayout.addView(textView2);

            TextView textView3 = new TextView(getActivity().getApplicationContext());
            if (productWithBrandName.price == -1) textView3.setText("품절");
            else {  //가격 출력
                int thwon = productWithBrandName.price / 1000;
                int onewon = productWithBrandName.price % 1000;
                if (onewon == 0) {
                    textView3.setText(thwon + ",000 원");
                } else {
                    textView3.setText(thwon + "," + onewon + " 원");
                }
            }
            textView3.setGravity(Gravity.LEFT);
            textView3.setTextSize(14);
            textView3.setPadding(30, 40, 0, 0);  //패딩
            linearLayout.addView(textView3);

            //한 줄에 상품 세 개씩 출력
            if (productWithBrandName.id % 2 == 0) {
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
        View fv = inflater.inflate(R.layout.fragment_marcket_category, container, false);

        Resources res = getResources();
        String[] categories = res.getStringArray(R.array.category);

        Spinner spinner = (Spinner) fv.findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.categoryKOR, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                printAllProductByCategory(fv, categories[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                printAllProductByCategory(fv, "all");
            }
        });

        LinearLayout chooseCategoryButtons = fv.findViewById(R.id.chooseCategoryButtons);

        for (int i = 0; i < categories.length; i++) {
            Button button = new Button(getActivity().getApplicationContext());
            if (i == 0) {
                button.setBackgroundResource(R.drawable.selected_roundbutton);
            } else {
                button.setBackgroundResource(R.drawable.roundbutton);
            }

            button.setText(res.getStringArray(R.array.categoryKOR)[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);
            button.setLayoutParams(params);

            String category = categories[i];
            final int categoryIndex = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (prevChooseBtn != null)
                        prevChooseBtn.setBackgroundResource(R.drawable.roundbutton);
                    spinner.setSelection(categoryIndex);
                    button.setBackgroundResource(R.drawable.selected_roundbutton);
                    prevChooseBtn = button;
                    printAllProductByCategory(fv, category);
                }
            });
            chooseCategoryButtons.addView(button);
        }

        return fv;
    }
}