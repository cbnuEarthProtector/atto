package com.example.atto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class ScrapListActivity extends AppCompatActivity {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list_page);
        linearLayout = findViewById(R.id.linearScrap);

        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        ProductDao productDao = appDatabase.productDao();
        List<ProductWithBrandName> productsWithBookmarked = productDao.findAllWhichBookmarked();
        for(ProductWithBrandName product : productsWithBookmarked) {
            LinearLayout linearScrapElement = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(70,10,30,20);
            linearScrapElement.setLayoutParams(params);
            linearScrapElement.setOrientation(LinearLayout.VERTICAL);
            linearScrapElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 스크랩 pop up 페이지
                }
            });

            //상품 사진
            ImageView imageView= new ImageView(getApplicationContext());
            String image_url = product.photoURL;
            Glide.with(this).load(image_url).into(imageView);
            linearScrapElement.addView(imageView);

            //상품 이름
            TextView productName = new TextView(getApplicationContext());
            productName.setText("["+product.name+"]");
            productName.setGravity(Gravity.LEFT);
            productName.setTextSize(14);
            productName.setPadding(30, 20, 0, 0);
            linearScrapElement.addView(productName);

            //상품 메모
            TextView productMemo = new TextView(getApplicationContext());
            productMemo.setText(product.memo);
            productMemo.setGravity(Gravity.LEFT);
            productMemo.setMaxLines(2);  // 두 줄 출력
            productMemo.setEms(20);  // 한 줄에 글자 수
            productMemo.setEllipsize(TextUtils.TruncateAt.END); // 말줄임표
            productMemo.setTextSize(14);
            productMemo.setPadding(30, 0, 0, 0);
            linearScrapElement.addView(productMemo);

            linearLayout.addView(linearScrapElement);
        }
    }
}
