package android.example.expertism;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

public class MainCommentAdapter extends RecyclerView.Adapter<MainCommentAdapter.MainCommentViewHolder>{

    /*List<PreviewItem> list;
    PreviewListener listener;*/

    Context context;

    public MainCommentAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MainCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main_comment, parent, false);
        return new MainCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCommentViewHolder holder, int position) {
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

    public class MainCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //PreviewListener previewListener;
        TextView textViewUserName, textViewComment;
        RecyclerView recyclerViewReplyComment;
        TextView buttonAddReply;
        public MainCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewComment = itemView.findViewById(R.id.textViewComment);
            recyclerViewReplyComment = itemView.findViewById(R.id.recyclerViewReplyComments);
            buttonAddReply = itemView.findViewById(R.id.buttonAddReply);
            //textViewPreview = itemView.findViewById(R.id.textViewPreview);
            //previewListener = listener;
            buttonAddReply.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //previewListener.onDomainClick(getAdapterPosition());
            //TODO: Open dialog box for creating reply
            //Get comment ID using list.get(getAdapterPosition())._id
            Toast.makeText(context, "Add a new reply", Toast.LENGTH_SHORT).show();
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
