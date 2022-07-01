package com.example.roomdatabaselivedata.navigation_da.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.model.Product;
import com.example.roomdatabaselivedata.viewModel.ProductViewModel;

import java.util.List;

public class ProductFragment extends Fragment {

    private ProductViewModel productViewModel;
    private RecyclerView rcFragmentProduct;
    private AdapterProduct adapterProduct;
    Button btProduc, btDeleteAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);

        btProduc = view.findViewById(R.id.bt_add);
        btDeleteAll = view.findViewById(R.id.bt_delete_all);
        rcFragmentProduct = view.findViewById(R.id.rc_product);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),
                1, RecyclerView.VERTICAL, false);
        adapterProduct = new AdapterProduct(getContext());
        rcFragmentProduct.setAdapter(adapterProduct);
        rcFragmentProduct.setLayoutManager(layoutManager);
        btProduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_productFragment_to_addFragment);
            }
        });
        btDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btDeleteAll();
            }
        });


        productViewModel.getAllProduct().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapterProduct.setListProduct(products);
            }
        });
        return view;
    }

    private void btDeleteAll() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getActivity());
        alBuilder.setTitle("Delete all product")
                .setCancelable(false)
                .setMessage("Are you sure to remove")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productViewModel.deleteAllProduct();
                        Toast.makeText(getContext(), "Delete All Data Ok", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }


    public void sendDataGradle(View view, Product product) {
        ProductFragmentDirections.ActionProductFragmentToUpdateFragment action = ProductFragmentDirections.actionProductFragmentToUpdateFragment(product);
        Navigation.findNavController(view).navigate(action);
    }

    public void sendDataBundle(View view, Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        Navigation.findNavController(view).navigate(R.id.action_productFragment_to_updateFragment, bundle);
    }
}