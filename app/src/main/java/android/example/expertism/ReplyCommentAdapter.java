package android.example.expertism;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

public class ReplyCommentAdapter extends RecyclerView.Adapter<ReplyCommentAdapter.ReplyCommentViewHolder>{

    /*List<PreviewItem> list;
    PreviewListener listener;*/

    /*public ReplyCommentAdapter(List<PreviewItem> list, PreviewListener listener) {
        this.list = list;
        this.listener = listener;
    }*/

    @NonNull
    @Override
    public ReplyCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_reply_comment, parent, false);
        return new ReplyCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyCommentViewHolder holder, int position) {
        /*holder.textViewHeading.setText(list.get(position).heading);
        holder.textViewWriter.setText(list.get(position).writerName);
        holder.textViewPreview.setText(list.get(position).previewText);*/
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ReplyCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //PreviewListener previewListener;
        TextView textViewHeading, textViewWriter, textViewPreview;
        public ReplyCommentViewHolder(@NonNull View itemView) {
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
