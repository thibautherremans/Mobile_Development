package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class item extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemrestview);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String body = intent.getStringExtra("body");
        String url = intent.getStringExtra("url");

        TextView tvTitle = findViewById(R.id.portfolioItem_title);
        ImageView ivImage = findViewById(R.id.portfolioItem_image);
        TextView tvBody  = findViewById(R.id.portfolioItem_body);
        TextView bShareButton = findViewById(R.id.share_button);

        tvTitle.setText(title);
        Picasso.get().load("https://thibautherremans.be/api/portfolio").into(ivImage);
        tvBody.setText(body);
        bShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentButton = new Intent(Intent.ACTION_SEND);
                intentButton.putExtra(Intent.EXTRA_TEXT, "Share on the link!");
                intentButton.setType("text/plain");
                startActivity(intentButton);
            }
        });
    }
}