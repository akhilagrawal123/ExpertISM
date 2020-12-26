package android.example.expertism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import io.noties.markwon.Markwon;
import io.noties.markwon.core.spans.StrongEmphasisSpan;
import io.noties.markwon.editor.AbstractEditHandler;
import io.noties.markwon.editor.MarkwonEditor;
import io.noties.markwon.editor.MarkwonEditorTextWatcher;
import io.noties.markwon.editor.MarkwonEditorUtils;
import io.noties.markwon.editor.PersistedSpans;
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin;
import io.noties.markwon.html.HtmlPlugin;

public class CreateBlogActivity extends AppCompatActivity {

    TextInputEditText editTextBlogContent;
    TextView textViewPreview;
    ChipGroup chipGroupSubCategory;
    private static final String TAG = "CreateBlogActivity";

    ArrayList<SubCategory> subCategoryList;
    ChipGroup.OnCheckedChangeListener chipCheckChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blog);
        editTextBlogContent = findViewById(R.id.editTextBlogContent);
        textViewPreview = findViewById(R.id.textViewPreview);
        chipGroupSubCategory = findViewById(R.id.chipGroupSubCategory);

        //Set TextView scrollabilty
        textViewPreview.setMovementMethod(new ScrollingMovementMethod());

        // obtain Markwon instance
        Markwon markwon = Markwon.builder(this)
                .usePlugin(HtmlPlugin.create())
                .usePlugin(StrikethroughPlugin.create())
                .build();

        prepareSubCategoryList();

        chipCheckChangeListener = new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                filterByCategory(checkedId);
            }
        };

        chipGroupSubCategory.setOnCheckedChangeListener(chipCheckChangeListener);

        prepareChips();

        editTextBlogContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d(TAG, "onTextChanged: New text is "+s);
                markwon.setMarkdown(textViewPreview, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void prepareSubCategoryList() {
        subCategoryList = new ArrayList<>();
        subCategoryList.add(new SubCategory(0, "All"));
        subCategoryList.add(new SubCategory(1, "Mess Refund"));
        subCategoryList.add(new SubCategory(2, "Mess Food"));
        subCategoryList.add(new SubCategory(3, "Movies"));
        subCategoryList.add(new SubCategory(4, "Shopping"));
        subCategoryList.add(new SubCategory(5, "Rooms"));
        subCategoryList.add(new SubCategory(6, "Clubs"));
        subCategoryList.add(new SubCategory(7, "Campus Life"));
    }

    void prepareChips(){
        for(SubCategory sub: subCategoryList){
            Chip chip = new Chip(this);
            chip.setId(sub.ID);
            chip.setText(sub.subCategoryName);
            chip.setClickable(true);
            chip.setCheckable(true);
            if(sub.ID==0) chip.setChecked(true);
            chipGroupSubCategory.addView(chip);
        }
    }

    void filterByCategory(int subCategoryID){
        Toast.makeText(this, ""+subCategoryID, Toast.LENGTH_SHORT).show();
    }
}