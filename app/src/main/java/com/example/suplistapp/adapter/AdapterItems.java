package com.example.suplistapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suplistapp.R;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.repository.ItemRepository;
import com.example.suplistapp.view.DailyActivity;
import com.example.suplistapp.view.MonthlyActivity;
import com.example.suplistapp.view.SignUpActivity;
import com.example.suplistapp.view.WeeklyActivity;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder>{

  List<Item> itemsList;
  Context context;

  public AdapterItems(List<Item> itemsList, Context context) {
    this.itemsList = itemsList;
    this.context = context;
  }

  @NonNull
  @Override
  public AdapterItems.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_adapter, parent, false);
    return new ViewHolder(view);
  }

  private Integer idOnClick;

  public Integer getIdOnClick(){
    return this.idOnClick;
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterItems.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.itemName.setText(itemsList.get(position).getProductName());
    holder.itemPrice.setText(itemsList.get(position).getProductPrice().toString());
    holder.itemQty.setText(itemsList.get(position).getQuantity().toString());
    holder.expiration.setText(itemsList.get(position).getExpirationDate());
    holder.delete.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        ItemRepository itemRepository = new ItemRepository(context);
        idOnClick = itemsList.get(position).getId();
        Item item = itemRepository.getItemById(idOnClick);
        String listType = item.getListType();
        itemRepository.deleteItem(item);
        if (listType.equals("daily")) {
          Intent intent = new Intent(view.getContext(), DailyActivity.class);
          context.startActivity(intent);
        }
        if (listType.equals("weekly")) {
          Intent intent = new Intent(view.getContext(), WeeklyActivity.class);
          context.startActivity(intent);
        }
        if (listType.equals("monthly")) {
          Intent intent = new Intent(view.getContext(), MonthlyActivity.class);
          context.startActivity(intent);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return itemsList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView itemName;
    TextView itemPrice;
    TextView itemQty;
    TextView expiration;
    TextView delete;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      itemName = itemView.findViewById(R.id.itemName);
      itemPrice = itemView.findViewById(R.id.itemPrice);
      itemQty = itemView.findViewById(R.id.itemQty);
      expiration = itemView.findViewById(R.id.expiration);
      delete = itemView.findViewById(R.id.delete);
    }
  }
}
