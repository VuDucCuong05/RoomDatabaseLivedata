package com.example.roomdatabaselivedata.navigation_da.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.model.Product;
import com.example.roomdatabaselivedata.viewModel.ProductViewModel;

public class AddFragment extends Fragment {

    private ProductViewModel productViewModel;
    Button btAdd;
    EditText edtName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        btAdd = view.findViewById(R.id.bt_add);
        edtName = view.findViewById(R.id.edt_name);


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct(v);
            }
        });

        return view;
    }

    public void addProduct(View view) {
        String strName = edtName.getText().toString();
        if (strName != null) {
            Product product = new Product(strName);
            productViewModel.insertProduct(product);
            NavDirections action =
                    AddFragmentDirections.actionAddFragmentToProductFragment();
            Navigation.findNavController(view).navigate(action);
        } else {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
        }
    }

}