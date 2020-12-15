package android.example.expertism;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.HomeViewHolder>{

    public static final String ANDROID = "android";
    public static final String WEB = "web";
    public static final String ML = "ml";
    public static final String AI = "ai";
    public static final String VIDEO = "video";
    public static final String UIUX = "uiux";
    public static final String CONTENT = "content";
    public static final String OTHERS = "others";

    ArrayList<HomeItem> list;
    HomeListener listener;

    public HomeCategoryAdapter(ArrayList<HomeItem> list, HomeListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_category, parent, false);
        return new HomeViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.textView.setText(list.get(position).itemName);
        holder.imageView.setImageResource(list.get(position).itemDrawable);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        HomeListener homeListener;
        ImageView imageView;
        TextView textView;
        public HomeViewHolder(@NonNull View itemView, HomeListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.description);
            homeListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            homeListener.onDomainClick(getAdapterPosition());
        }
    }

    public static class HomeItem{
        String itemName;
        int itemDrawable;
        String domainArg;

        public HomeItem(String itemName, int itemDrawable, String domainArg) {
            this.itemName = itemName;
            this.itemDrawable = itemDrawable;
            this.domainArg = domainArg;
        }
    }

    public interface HomeListener{
        void onDomainClick(int position);
    }
}
