package com.example.atto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;
import java.util.List;

public class Productdetail_page_Activity extends AppCompatActivity {
    ImageView productImage;
    ImageButton scrapBtn;
    Button productPageBtn;
    TextView brandField, productNameField, priceField;

    private List<ProductWithBrandName> productList;
    private Dialog_scrap_popup dialog_scrap_popup;
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
        brandField = findViewById(R.id.brandField);
        productNameField = findViewById(R.id.productNameField);
        priceField = findViewById(R.id.priceField);

        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        Intent intent = getIntent();
        int productId = intent.getIntExtra("id", 0);
        ProductWithBrandName matchingProduct = productList.get(productId - 1); // 배열 인덱스로 상품 찾기
        if(productId != matchingProduct.id) { // 배열 인덱스로 찾았을 때 id 값이 일치하지 않을 때
            for (ProductWithBrandName product : productList) {
                if (product.id == productId) matchingProduct = product; // Fragment_market_page_Activity에서 전달받은 id 값과 일치하는 상품 찾기
            }
        }
        Glide.with(this).load(matchingProduct.photoURL).into(productImage);
        String image = matchingProduct.photoURL;
        brandField.setText(matchingProduct.brandName); // 브랜드명
        String brand = matchingProduct.brandName;
        productNameField.setText(matchingProduct.name); // 상품명
        String product = matchingProduct.name;
        priceField.setText(Integer.toString(matchingProduct.price) + "원"); // 가격
        String price = Integer.toString(matchingProduct.price) + "원";

        // 스크랩 버튼 팝업창 띄우기
        scrapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MypageScrapActivity.class);
                dialog_scrap_popup = new Dialog_scrap_popup(Productdetail_page_Activity.this,productId, image, brand, product, price);
                dialog_scrap_popup.show();
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
    }
}
