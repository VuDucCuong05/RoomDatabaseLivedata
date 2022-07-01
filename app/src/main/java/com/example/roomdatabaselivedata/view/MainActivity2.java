package com.example.roomdatabaselivedata.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.base.BaseActivity;
import com.example.roomdatabaselivedata.model.Product;

public class MainActivity2 extends BaseActivity {

    private Button btAddProduct;
    private EditText edtName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        edtName = findViewById(R.id.edt_input_name);
        btAddProduct = findViewById(R.id.bt_add_product);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Product product = (Product) bundle.getSerializable("data_product");
            edtName.setText(product.getName());
        }
    }

    @Override
    protected void initEvent() {

        btAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = edtName.getText().toString().trim();
                if (!strName.equals("")) {
                    Intent intent = new Intent();
                    Bundle bundle1 = new Bundle();
                    Product product1 = new Product(strName);

                    bundle1.putSerializable("data_product_new", product1);
                    intent.putExtras(bundle1);
                    setResult(RESULT_OK, intent);
                    finish();

                } else {
                    Toast.makeText(getBaseContext(), "You not input data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}