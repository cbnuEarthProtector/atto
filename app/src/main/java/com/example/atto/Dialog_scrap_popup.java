package com.example.atto;

import android.app.Dialog;
import android.content.Context;
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
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class Dialog_scrap_popup extends Dialog {
    private TextView brand, product, price;
    private ImageView productImageView;
    private ImageButton shutdownClick;
    private Button saveBtn;
    private AppDatabase appDatabase;
    private ProductDao productDao;
    private EditText editText;

    public Dialog_scrap_popup(@NonNull Context context,int pID, String image, String Brand, String productName, String priceField){
        super(context);
        setContentView(R.layout.activity_scrap_popup);

        AppDatabase appDatabase = AppDatabase.getInstance(context);
        ProductDao productDao = appDatabase.productDao();

        productImageView=findViewById(R.id.productImageView);
        Glide.with(context).load(image).into(productImageView);

        brand = findViewById(R.id.brandName);
        brand.setText(Brand);

        product = findViewById(R.id.productName);
        product.setText(productName);

        price = findViewById(R.id.price);
        price.setText(priceField);

        editText = findViewById(R.id.editText);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String memo = editText.getText().toString();
                productDao.addBookmark(pID, memo);

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
