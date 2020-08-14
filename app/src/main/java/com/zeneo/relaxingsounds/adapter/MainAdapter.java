package com.zeneo.relaxingsounds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zeneo.relaxingsounds.model.MainItem;
import com.zeneo.relaxingsounds.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private Context context;
    private List<MainItem> items;
    private ItemClickListener listener;

    public MainAdapter(Context context, ArrayList<MainItem> items){
        this.context = context;
        this.items = items;
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text.setText(items.get(position).getTitle());
        Glide.with(this.context).load(items.get(position).getImage()).into(holder.image);

        if(position == 0){
            holder.gradientView.setBackground(context.getDrawable(R.drawable.ocean_bg));
        } else if(position == 1){
            holder.gradientView.setBackground(context.getDrawable(R.drawable.forest_bg));
        } else if(position == 2){
            holder.gradientView.setBackground(context.getDrawable(R.drawable.night_bg));
        } else if(position == 3){
            holder.gradientView.setBackground(context.getDrawable(R.drawable.rain_bg));
        } else if(position == 4){
            holder.gradientView.setBackground(context.getDrawable(R.drawable.lake_bg));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        View gradientView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.main_rv_item_image_view);
            text = itemView.findViewById(R.id.main_rv_item_text_view);
            gradientView = itemView.findViewById(R.id.main_rv_gradient_view);
        }

    }
}
