package com.example.atto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;
import java.util.List;

public class Productdetail_page_Activity extends AppCompatActivity {
    ImageView productImage;
    ImageButton scrapBtn;
    Button productPageBtn;
    TextView productNameField, priceField;

    Button marcketbtn, restaurantbtn, scrapbtn;

    private List<ProductWithBrandName> productList;

    public Productdetail_page_Activity() {
        //productList = new Product();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail_page);

        productImage = findViewById(R.id.productImageView);
        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        ProductDao productDao = appDatabase.productDao();
        productList = productDao.getAll();
        scrapBtn = findViewById(R.id.scrapBtn);
        productPageBtn = findViewById(R.id.productPageBtn);
        productNameField = findViewById(R.id.productNameField);
        priceField = findViewById(R.id.priceField);

        Intent intent = getIntent();
        int productId = intent.getIntExtra("id", 0);
        ProductWithBrandName matchingProduct = productList.get(productId - 1); // 배열 인덱스로 상품 찾기
        if(productId != matchingProduct.id) { // 배열 인덱스로 찾았을 때 id 값이 일치하지 않을 때
            for (ProductWithBrandName product : productList) {
                if (product.id == productId) matchingProduct = product; // Fragment_market_page_Activity에서 전달받은 id 값과 일치하는 상품 찾기
            }
        }
        Glide.with(this).load(matchingProduct.photoURL).into(productImage);
        productImage.setColorFilter(Color.parseColor("#f1f3f4"), PorterDuff.Mode.DST_OVER);
        productNameField.setText("["+matchingProduct.brandName+"] "+matchingProduct.name); // 브랜드명, 상품명
        //가격
        int thwon =matchingProduct.price/1000;
        int onewon=matchingProduct.price%1000;
        if (onewon == 0) {
            priceField.setText(thwon + ",000 원");
        } else {
            priceField.setText(thwon+","+onewon+" 원");
        }

        // 스크랩 버튼 -> 스크랩 페이지로 연결
        scrapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent .putExtra("productDetailPage", "scrap");
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


        //맨 아래 버튼들
        marcketbtn = (Button) findViewById(R.id.marcketbtn);
        restaurantbtn = (Button) findViewById(R.id.restaurantbtn);
        scrapbtn = (Button) findViewById(R.id.scrapbtn);

        marcketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marcketbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                Intent intent=new Intent(Productdetail_page_Activity.this, HomeActivity.class);
                intent.putExtra("productdetailbtn", "marcket");
                startActivity(intent);
            }
        });

        restaurantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                Intent intent=new Intent(Productdetail_page_Activity.this, HomeActivity.class);
                intent.putExtra("productdetailbtn", "restaurant");
                startActivity(intent);
            }
        });

        scrapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrapbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                Intent intent=new Intent(Productdetail_page_Activity.this, HomeActivity.class);
                intent.putExtra("productdetailbtn", "scrap");
                startActivity(intent);
            }
        });


    }
}
