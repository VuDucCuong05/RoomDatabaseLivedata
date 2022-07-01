package com.example.roomdatabaselivedata.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.adapter.AdapterProduct;
import com.example.roomdatabaselivedata.adapter.ItemProduct;
import com.example.roomdatabaselivedata.base.BaseActivity;
import com.example.roomdatabaselivedata.database.ProductDataBase;
import com.example.roomdatabaselivedata.model.Product;
import com.example.roomdatabaselivedata.viewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private EditText edtNameProduct;
    private Button btAddProduct, btDeleteAllProduct;
    private RecyclerView rcProduct;
    private AdapterProduct adapterProduct;

    private ProductViewModel mProductViewModel;
    int[] id = new int[1];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        edtNameProduct = findViewById(R.id.edt_name);
        btAddProduct = findViewById(R.id.bt_add_product);
        btDeleteAllProduct = findViewById(R.id.bt_delete_all_product);
        rcProduct = findViewById(R.id.rc_product);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(),
                1, RecyclerView.VERTICAL, false);
        rcProduct.setLayoutManager(layoutManager);
        adapterProduct = new AdapterProduct(this);

        mProductViewModel = new ViewModelProvider(MainActivity.this).get(ProductViewModel.class);

    }

    @Override
    protected void initData() {
        rcProduct.setAdapter(adapterProduct);
        mProductViewModel.getAllProduct().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapterProduct.setProduct(products);
            }
        });

    }

    @Override
    protected void initEvent() {

        btAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = edtNameProduct.getText().toString().trim();
                mProductViewModel.insertProduct(new Product(strName));
            }
        });
        adapterProduct.setItemProduct(new ItemProduct() {
            @Override
            public void getProduct(Product product) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confrim delete product")
                        .setMessage("Are you ok")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mProductViewModel.deleteProduct(new Product(product.getName()));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }

            @Override
            public void getProductUdapte(Product product) {
//                id[0] = product.getId();
//                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data_product",product);
//                intent.putExtras(bundle);
//                startActivityForResult(intent,1);

                mProductViewModel.deleteProduct(new Product(product.getName()));
                Product product1 = new Product(product.getName());
                mProductViewModel.insertProduct(product1);
                Log.e("prox", "ok");

            }
        });

        btDeleteAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confrim delete all product")
                        .setMessage("Are you ok")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mProductViewModel.deleteAllProduct();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            Product product = (Product) bundle.getSerializable("data_product_new");
            product.setId(id[0]);
            mProductViewModel.updateProduct(product);
            Toast.makeText(getBaseContext(), "Update product succse", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Update product error", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}