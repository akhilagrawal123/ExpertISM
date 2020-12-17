package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DevelopersActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView linkAkhil, mailAkhil, linkAkash, mailAkash, linkAbhishek, mailAbhishek, linkAditya, mailAditya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linkAkhil = (ImageView) findViewById(R.id.linkAkhil);
        linkAkash = (ImageView) findViewById(R.id.linkAkash);
        linkAbhishek = (ImageView) findViewById(R.id.linkAbhishek);
        linkAditya = (ImageView) findViewById(R.id.linkAditya);
        mailAkhil = (ImageView) findViewById(R.id.mailAkhil);
        mailAbhishek = (ImageView) findViewById(R.id.mailAbhishek);
        mailAkash = (ImageView) findViewById(R.id.mailAkash);
        mailAditya = (ImageView) findViewById(R.id.mailAditya);

        //https://www.linkedin.com/in/akhil-agrawal-070434192/
        //https://www.linkedin.com/in/akash-mahapatra-2833601a4/
        //https://www.linkedin.com/in/abhidot/
        //https://www.linkedin.com/in/aditya-sinha-5b79521a0/

        linkAkhil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.linkedin.com/in/akhil-agrawal-070434192/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        linkAkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.linkedin.com/in/akash-mahapatra-2833601a4/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        linkAbhishek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.linkedin.com/in/abhidot/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        linkAditya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.linkedin.com/in/aditya-sinha-5b79521a0/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        //Mails........
        //akhilagrawal942@gmail.com
        //aditya1186137@gmail.com
        //mahapatraakash@gmail.com
        //theabhidot@gmail.com

        mailAkhil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "akhilagrawal942@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(DevelopersActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mailAditya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "aditya1186137@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(DevelopersActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mailAkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "mahapatraakash@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(DevelopersActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mailAbhishek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "theabhidot@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(DevelopersActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}