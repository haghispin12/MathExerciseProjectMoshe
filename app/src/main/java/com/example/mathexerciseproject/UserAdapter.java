package com.example.mathexerciseproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    public interface OnItemClickListener{
        void onItemClick(User item);
    }


    public ArrayList<User> users;
    private UserAdapter.OnItemClickListener listener;

    public UserAdapter(ArrayList<User> users, UserAdapter.OnItemClickListener listener){
        this.users = users;
        this.listener = listener;
    }

    //
    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserAdapter.ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.bind(users.get(position), listener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        ImageView ivUserPFP;
        TextView tvUserScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserScore = itemView.findViewById(R.id.tvUserScore);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivUserPFP = itemView.findViewById(R.id.ivUserPFP);

        }

        public void bind(User user, UserAdapter.OnItemClickListener listener) {
            tvUserName.setText(user.getUserName());
            tvUserScore.setText(user.getScore());
            ivUserPFP.setImageBitmap(user.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(user);
                }
            });
        }
    }}
