package android.example.expertism;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReplyCommentAdapter extends RecyclerView.Adapter<ReplyCommentAdapter.PreviewViewHolder>{

    /*List<PreviewItem> list;
    PreviewListener listener;*/

    /*public ReplyCommentAdapter(List<PreviewItem> list, PreviewListener listener) {
        this.list = list;
        this.listener = listener;
    }*/

    @NonNull
    @Override
    public PreviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_reply_comment, parent, false);
        return new PreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviewViewHolder holder, int position) {
        /*holder.textViewHeading.setText(list.get(position).heading);
        holder.textViewWriter.setText(list.get(position).writerName);
        holder.textViewPreview.setText(list.get(position).previewText);*/
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class PreviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //PreviewListener previewListener;
        TextView textViewHeading, textViewWriter, textViewPreview;
        public PreviewViewHolder(@NonNull View itemView) {
            super(itemView);
            /*textViewHeading = itemView.findViewById(R.id.textViewHeading);
            textViewWriter = itemView.findViewById(R.id.textViewWriter);
            textViewPreview = itemView.findViewById(R.id.textViewPreview);
            previewListener = listener;
            itemView.setOnClickListener(this);*/
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
