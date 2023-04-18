package com.example.foodapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.MainActivity;
import com.example.foodapp.R;
import com.example.foodapp.ShowDetailActivity;
import com.example.foodapp.model.CategoryModel;
import com.example.foodapp.model.PopularModel;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<PopularModel> popularModel;

    public PopularAdapter(ArrayList<PopularModel> popularModel) {
        this.popularModel = popularModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.popularName.setText(popularModel.get(position).getTitle());
        holder.popularFee.setText(String.valueOf(popularModel.get(position).getFee()));
        String picUrl = "";



        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularModel.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.popularPic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", popularModel.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView popularName, popularFee;
        ImageView popularPic;
        ConstraintLayout addBtn;
        ConstraintLayout mainLayout2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularName = itemView.findViewById(R.id.popularName);
            popularPic = itemView.findViewById(R.id.popularPic);
            mainLayout2 = itemView.findViewById(R.id.mainLayout2);
            popularFee = itemView.findViewById(R.id.popularFee);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
