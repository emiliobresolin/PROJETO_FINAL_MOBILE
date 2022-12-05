package com.example.recyclerviewativ.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suplistapp.R;
import com.example.suplistapp.model.Item;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder>{

  List<Item> itemsList;

  public AdapterItems(List<Item> itemsList) {
    this.itemsList = itemsList;
  }

  @NonNull
  @Override
  public AdapterItems.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_adapter, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterItems.ViewHolder holder, int position) {
    holder.itemName.setText(itemsList.get(position).getProductName());
    holder.itemPrice.setText(itemsList.get(position).getProductPrice().toString());
    holder.itemQty.setText(itemsList.get(position).getQuantity().toString());
  }

  @Override
  public int getItemCount() {
    return itemsList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView itemName;
    TextView itemPrice;
    TextView itemQty;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      itemName = itemView.findViewById(R.id.itemName);
      itemPrice = itemView.findViewById(R.id.itemPrice);
      itemQty = itemView.findViewById(R.id.itemQty);
    }
  }
}
