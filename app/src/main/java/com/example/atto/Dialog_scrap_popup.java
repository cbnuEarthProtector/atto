package com.example.atto;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductBookmark;
import com.example.atto.database.ProductBookmarkDao;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;
import com.example.atto.database.UserDatabase;

import java.util.List;

public class Dialog_scrap_popup extends Dialog {
    private TextView brand, product, price;
    private ImageView productImageView;
    private ImageButton shutdownClick;
    private Button saveBtn;
    private AppDatabase appDatabase;
    private ProductDao productDao;
    private EditText editText;

    public Dialog_scrap_popup(@NonNull Context context, int pID, String image, String Brand, String productName, String priceField, String memoField) {
        super(context);
        setContentView(R.layout.activity_scrap_popup);

        UserDatabase userDatabase = UserDatabase.getInstance(context);
        ProductBookmarkDao productBookmarkDao = userDatabase.productBookmarkDao();

        productImageView = findViewById(R.id.productImageView);
        Glide.with(context).load(image).into(productImageView);
        productImageView.setColorFilter(Color.parseColor("#f1f3f4"), PorterDuff.Mode.DST_OVER);

        brand = findViewById(R.id.brandName);
        brand.setText(Brand);

        product = findViewById(R.id.productName);
        product.setText(productName);

        price = findViewById(R.id.price);
        int productPrice = Integer.parseInt(priceField);
        //price.setText(priceField);
        if (productPrice == -1) price.setText("품절");
        else {  //가격 출력
            int thwon = productPrice / 1000;
            int onewon = productPrice % 1000;
            if (onewon == 0) {
                price.setText(thwon + ",000 원");
            } else {
                price.setText(thwon + "," + onewon + " 원");
            }
        }

        editText = findViewById(R.id.editText);
        if (memoField != null) {
            editText.setText(memoField);
        }
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String memo = editText.getText().toString();
                productBookmarkDao.insert(new ProductBookmark(pID, memo));

                dismiss();
            }
        });

        //나가기
        shutdownClick = findViewById(R.id.cancel);
        shutdownClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
