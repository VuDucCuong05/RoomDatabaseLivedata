package com.example.roomdatabaselivedata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.model.Product;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private LayoutInflater mInflater;

    private List<Product> mProducts;
    private ItemProduct itemProduct;

    public AdapterProduct(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setItemProduct(ItemProduct itemProduct) {
        this.itemProduct = itemProduct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product_custom,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.tvName.setText(product.getName());


        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemProduct.getProduct(product);
            }
        });
        holder.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemProduct.getProductUdapte(product);
            }
        });
    }

    public void setProduct(List<Product> product){
        mProducts = product;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mProducts != null){
            return mProducts.size();
        }else{
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        Button btDelete,btUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_product);
            btDelete = itemView.findViewById(R.id.bt_delete_product);
            btUpdate = itemView.findViewById(R.id.bt_update_product);
        }
    }
}
