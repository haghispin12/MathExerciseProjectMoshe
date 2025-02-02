package com.example.mathexerciseproject.mathproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathexerciseproject.R;

import java.util.ArrayList;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    public interface OnItemClickListener{
        void onItemClick(Fruit item);
    }

    public ArrayList<Fruit> fruits;
    private OnItemClickListener listener;

    public FruitAdapter(ArrayList<Fruit> fruits, OnItemClickListener listener){
        this.fruits = fruits;
        this.listener = listener;
    }

    //
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(fruits.get(position), listener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return fruits.size();
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFruitName;
        ImageView ivFruitImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.tvFruitName);
            ivFruitImg = itemView.findViewById(R.id.ivFruitImg);
        }

        public void bind(Fruit fruit, OnItemClickListener listener) {
            tvFruitName.setText(fruit.getName());
            ivFruitImg.setImageResource(fruit.getImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        listener.onItemClick(fruit);
                }
            });
        }
    }
}
