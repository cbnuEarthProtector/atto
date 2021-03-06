package com.example.atto;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductBookmark;
import com.example.atto.database.ProductBookmarkDao;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;
import com.example.atto.database.UserDatabase;

import java.util.List;

// ProductDetailPageActivity
// product_detail_page_activity
public class Productdetail_page_Activity extends AppCompatActivity {
    ImageView productImage;
    ImageButton scrapBtn;
    Button productPageBtn;
    TextView productNameField, priceField;

    Button marcketbtn, restaurantbtn, scrapbtn;

    String image, brand, product, price, memo;

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
        UserDatabase userDatabase = UserDatabase.getInstance(getApplicationContext());
        ProductBookmarkDao productBookmarkDao = userDatabase.productBookmarkDao();

        productList = productDao.getAll();
        scrapBtn = findViewById(R.id.scrapBtn);
        productPageBtn = findViewById(R.id.productPageBtn);
        productNameField = findViewById(R.id.productNameField);
        priceField = findViewById(R.id.priceField);

        //??????????????? ?????? ????????? ????????? ????????????
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        Intent intent = getIntent();
        int productId = intent.getIntExtra("id", 0);
        ProductWithBrandName matchingProduct = productList.get(productId - 1); // ?????? ???????????? ?????? ??????
        ProductBookmark productBookmark = productBookmarkDao.findByProductId(productId);
        if (productId != matchingProduct.id) { // ?????? ???????????? ????????? ??? id ?????? ???????????? ?????? ???
            for (ProductWithBrandName product : productList) {
                if (product.id == productId)
                    matchingProduct = product; // Fragment_market_page_Activity?????? ???????????? id ?????? ???????????? ?????? ??????
            }
        }
        Glide.with(this).load(matchingProduct.photoURL).into(productImage);
        productImage.setColorFilter(Color.parseColor("#f1f3f4"), PorterDuff.Mode.DST_OVER);
        productNameField.setText("[" + matchingProduct.brandName + "] " + matchingProduct.name); // ????????????, ?????????
        //??????
        if (matchingProduct.price == -1) priceField.setText("??????");
        else {  //?????? ??????
            int thwon = matchingProduct.price / 1000;
            int onewon = matchingProduct.price % 1000;
            if (onewon == 0) {
                priceField.setText(thwon + ",000 ???");
            } else {
                priceField.setText(thwon + "," + onewon + " ???");
            }
        }

        image = matchingProduct.photoURL;
        brand = matchingProduct.brandName;
        product = matchingProduct.name;
        price = Integer.toString(matchingProduct.price);
        memo = "";
        if (productBookmark != null) {
            memo = productBookmark.memo;
            scrapBtn.setImageResource(R.drawable.is_bookmarked);
            scrapBtn.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.mainGreen)));
        }

        // ????????? ?????? ????????? ?????????
        scrapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_scrap_popup = new Dialog_scrap_popup(Productdetail_page_Activity.this, productId, image, brand, product, price, memo);
                dialog_scrap_popup.show();

                scrapBtn.setImageResource(R.drawable.is_bookmarked);
                scrapBtn.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.mainGreen)));
            }
        });

        // ?????? ???????????? ??????
        productPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productList.get(productId - 1).siteURL));
                startActivity(intent);
            }
        });


        //??? ?????? ?????????
        marcketbtn = (Button) findViewById(R.id.marcketbtn);
        restaurantbtn = (Button) findViewById(R.id.restaurantbtn);
        scrapbtn = (Button) findViewById(R.id.scrapbtn);

        marcketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marcketbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                Intent intent = new Intent(Productdetail_page_Activity.this, HomeActivity.class);
                intent.putExtra("productdetailbtn", "marcket");
                startActivity(intent);
            }
        });

        restaurantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                Intent intent = new Intent(Productdetail_page_Activity.this, HomeActivity.class);
                intent.putExtra("productdetailbtn", "restaurant");
                startActivity(intent);
            }
        });

        scrapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrapbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                Intent intent = new Intent(Productdetail_page_Activity.this, HomeActivity.class);
                intent.putExtra("productdetailbtn", "scrap");
                startActivity(intent);
            }
        });


    }
}
