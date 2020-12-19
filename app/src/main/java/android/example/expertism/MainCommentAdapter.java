package android.example.expertism;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainCommentAdapter extends RecyclerView.Adapter<MainCommentAdapter.PreviewViewHolder>{

    /*List<PreviewItem> list;
    PreviewListener listener;*/

    Context context;

    public MainCommentAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public PreviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main_comment, parent, false);
        return new PreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviewViewHolder holder, int position) {
        /*holder.textViewHeading.setText(list.get(position).heading);
        holder.textViewWriter.setText(list.get(position).writerName);
        holder.textViewPreview.setText(list.get(position).previewText);*/
        holder.recyclerViewReplyComment.setAdapter(new ReplyCommentAdapter());
        holder.recyclerViewReplyComment.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PreviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //PreviewListener previewListener;
        TextView textViewUserName, textViewComment;
        RecyclerView recyclerViewReplyComment;
        public PreviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewComment = itemView.findViewById(R.id.textViewComment);
            recyclerViewReplyComment = itemView.findViewById(R.id.recyclerViewReplyComments);
            //textViewPreview = itemView.findViewById(R.id.textViewPreview);
            //previewListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //previewListener.onDomainClick(getAdapterPosition());
        }
    }

    public static class PreviewItem{
        String heading;
        String writerName;
        String previewText;
        HashSet<Integer> subCategorySet;

        public PreviewItem(String heading, String writerName, String previewText, HashSet<Integer> subCategorySet) {
            this.heading = heading;
            this.writerName = writerName;
            this.previewText = previewText;
            this.subCategorySet = subCategorySet;
        }
    }

    /*public void updateData(List<PreviewItem> newList){
        list = newList;
        notifyDataSetChanged();
    }

    public interface PreviewListener{
        void onDomainClick(int position);
    }*/
}
