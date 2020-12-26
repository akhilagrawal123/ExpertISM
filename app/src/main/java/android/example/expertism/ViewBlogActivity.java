package android.example.expertism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin;
import io.noties.markwon.html.HtmlPlugin;

public class ViewBlogActivity extends AppCompatActivity {

    TextView textViewBlogContent, textViewBlogHeading;
    Button buttonOpenComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blog);

        Markwon markwon = Markwon.builder(this)
                .usePlugin(HtmlPlugin.create())
                .usePlugin(StrikethroughPlugin.create())
                .build();
        textViewBlogContent = findViewById(R.id.textViewBlogContent);
        textViewBlogHeading = findViewById(R.id.textViewBlogHeading);
        buttonOpenComments = findViewById(R.id.buttonOpenComments);
        buttonOpenComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewBlogActivity.this, CommentActivity.class));
            }
        });
        String str = "~~Dummy blog content~~ <br> _Dummy blog line 2_ <br> Dummy blog line 3 <br>";
        str = str + str;
        str = str + str;
        str = str + str;
        str = str + str;
        str = str + str;
        //textViewBlogContent.setText(str);
        markwon.setMarkdown(textViewBlogContent, str);
        markwon.setMarkdown(textViewBlogHeading, "### Heading of the blog");
    }
}