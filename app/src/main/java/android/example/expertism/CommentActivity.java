package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CommentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabAddMainComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        recyclerView = findViewById(R.id.recyclerViewMainComments);
        fabAddMainComment = findViewById(R.id.fabAddMainComment);

        recyclerView.setAdapter(new MainCommentAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        fabAddMainComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open dialog box for adding main comment
                Toast.makeText(CommentActivity.this, "Add a new comment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}