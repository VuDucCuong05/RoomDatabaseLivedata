package com.example.roomdatabaselivedata.navigation_da.fragment;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.Repository.ProductRepository;
import com.example.roomdatabaselivedata.model.Product;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Product> mListProducts;

    public AdapterProduct(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mListProducts.get(position);
        holder.textName.setText(product.getName());
        holder.layoutBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductFragmentDirections.ActionProductFragmentToUpdateFragment action =
                        ProductFragmentDirections.actionProductFragmentToUpdateFragment(product);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListProducts != null) {
            return mListProducts.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        LinearLayout layoutBook;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name_book);
            layoutBook = itemView.findViewById(R.id.layout_book);
        }
    }

    public void setListProduct(List<Product> list) {
        mListProducts = list;
        notifyDataSetChanged();
    }
}
