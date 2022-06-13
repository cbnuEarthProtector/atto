package com.example.atto;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.BrandDao;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class BrandActivity extends AppCompatActivity {

    private static int brandId;

    private LinearLayout lineartable;

    static Button prevChooseBtn = null;

    Button[] buttons;

    ImageButton backbtn;

    public void printAllProductByBrandIdAndCategory(String category) {
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        ProductDao productDao = appDatabase.productDao();

        List<ProductWithBrandName> productWithBrandNameList;
        if (category.equals("all")) productWithBrandNameList = productDao.findAllByBrandId(brandId);
        else productWithBrandNameList = productDao.findAllByBrandIdAndCategory(brandId, category);

        printProductToView(productWithBrandNameList);
    }

    public void printProductToView(List<ProductWithBrandName> productWithBrandNameList) {
        TextView numOfProduct = findViewById(R.id.numOfProduct);
        numOfProduct.setText(Integer.toString(productWithBrandNameList.size()));

        //카테고리별 상품 출력
        lineartable = (LinearLayout) findViewById(R.id.lineartable);

        lineartable.removeAllViews();
        LinearLayout horlinear = new LinearLayout(this);
        for (ProductWithBrandName productWithBrandName : productWithBrandNameList) {
            //상품 정보 vertical layout으로 출력
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setPadding(60, 0, 20, 70);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), Productdetail_page_Activity.class);
                    intent.putExtra("id", productWithBrandName.id);
                    startActivity(intent);
                }
            });


            //상품 사진
            ImageView imageView = new ImageView(this);
            String image_url = productWithBrandName.photoURL;
            Glide.with(this).load(image_url).into(imageView);
            imageView.setColorFilter(Color.parseColor("#f1f3f4"), PorterDuff.Mode.DST_OVER);
            linearLayout.addView(imageView);

            //상품 카테고리
            TextView textView = new TextView(this);
            textView.setText("[" + productWithBrandName.category + "]");
            textView.setGravity(Gravity.LEFT);
            textView.setTextSize(14);
            textView.setPadding(30, 20, 0, 0);  //패딩
            linearLayout.addView(textView);

            //상품 정보
            TextView textView2 = new TextView(this);
            textView2.setText(productWithBrandName.name);  //이름
            textView2.setGravity(Gravity.LEFT);
            textView2.setMaxLines(2);  //두 줄 출력
            textView2.setEms(9);  //한 줄에 글자 수
            textView2.setEllipsize(TextUtils.TruncateAt.END);  //말줄임표
            textView2.setTextSize(14);
            textView2.setPadding(30, 0, 0, 0);  //패딩
            linearLayout.addView(textView2);

            TextView textView3 = new TextView(this);
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
                horlinear = new LinearLayout(this);
                lineartable.addView(horlinear);

                horlinear.addView(linearLayout);
            } else {
                horlinear.addView(linearLayout);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        brandId = getIntent().getIntExtra("brandId", 0);

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        BrandDao brandDao = appDatabase.brandDao();
        TextView brandName = findViewById(R.id.brandName);
        brandName.setText(brandDao.findById(brandId).name);

        Resources res = getResources();
        String[] categories = res.getStringArray(R.array.category);
        buttons = new Button[categories.length];

        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoryKOR, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                printAllProductByBrandIdAndCategory(categories[i]);
                if (prevChooseBtn != null)
                    prevChooseBtn.setBackgroundResource(R.drawable.roundbutton);
                buttons[i].setBackgroundResource(R.drawable.selected_roundbutton);
                prevChooseBtn = buttons[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                printAllProductByBrandIdAndCategory("all");
            }
        });

        LinearLayout chooseCategoryButtons = findViewById(R.id.chooseCategoryButtons);
        for (int i = 0; i < categories.length; i++) {
            Button button = new Button(this);
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
                    printAllProductByBrandIdAndCategory(category);
                }
            });
            chooseCategoryButtons.addView(button);
            buttons[i] = button;

            if(i==0) prevChooseBtn = button;
        }

        //뒤로가기 버튼 ->브랜드 페이지로
        backbtn=(ImageButton) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}