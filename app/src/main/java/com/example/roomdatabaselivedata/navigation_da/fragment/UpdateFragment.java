package com.example.roomdatabaselivedata.navigation_da.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.model.Product;
import com.example.roomdatabaselivedata.viewModel.ProductViewModel;

public class UpdateFragment extends Fragment {

    private ProductViewModel productViewModel;
    private EditText edtName;
    private Button btUpdate, btDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        btUpdate = view.findViewById(R.id.bt_update);
        edtName = view.findViewById(R.id.edt_name);
        btDelete = view.findViewById(R.id.bt_delete);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct(v);
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvName = view.findViewById(R.id.text_update);

        Product product = UpdateFragmentArgs.fromBundle(getArguments()).getProduct();
        if (product != null) {
            edtName.setText(product.getName());
        }

        Product product1 = (Product) getArguments().getSerializable("product");
        if (product1 != null) {
            tvName.setText(product1.getName());
        }
    }

    public void updateProduct(View v) {
        String strName = edtName.getText().toString();
        if (!TextUtils.isEmpty(strName)) {
            int id = UpdateFragmentArgs.fromBundle(getArguments()).getProduct().getId();
            Product product = new Product(id, strName);
            productViewModel.updateProduct(product);
            Navigation.findNavController(v).navigate(R.id.action_updateFragment_to_productFragment);
        } else {
            Toast.makeText(getContext(), "No update", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete(View v) {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getActivity());
        alBuilder.setTitle("Delete all product")
                .setCancelable(false)
                .setMessage("Are you sure to remove")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Product product = UpdateFragmentArgs.fromBundle(getArguments()).getProduct();
                        productViewModel.deleteProduct(product);
                        Navigation.findNavController(v).navigate(R.id.action_updateFragment_to_productFragment);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }

}