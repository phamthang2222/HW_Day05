package vn.phamthang.hw_day05.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import vn.phamthang.hw_day05.Model.Post;
import vn.phamthang.hw_day05.R;

public class AddPostActivity extends AppCompatActivity {
    private EditText edtUrlAvt, edtUrlImage, edtUserName, edtStatus;
    private ImageView imgAvt,imgContent,imgBack;
    private Button btAdd;
    private String urlAvt,urlImage,userName,status;
//    private Post newPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        initView();
        initData();
        onClick();
    }

    private void onClick() {
        imgBack.setOnClickListener(v -> {
            startActivity(new Intent(AddPostActivity.this,MainActivity.class));
            finish();
        });
        btAdd.setOnClickListener(v ->{
            Intent intent = new Intent();

            Post newPost = new Post();
            newPost.setUserName(userName);
            newPost.setUrlAvt(urlAvt);
            newPost.setUrlImgPost(urlImage);
            newPost.setTime("now");
            newPost.setStatus(status);
            newPost.setCountReaction(1345);
            newPost.setLike(false);

            Log.d("newPost:",newPost.toString());
            intent.putExtra("NEWPOST",newPost);
            startActivity(new Intent(AddPostActivity.this,MainActivity.class));
            finish();
        });
    }

    private void initData() {

        edtUrlAvt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                urlAvt = edtUrlAvt.getText().toString().trim();
                Glide.with(AddPostActivity.this)
                        .load(urlAvt)
                        .into(imgAvt);
            }
        });
        edtUrlImage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                urlImage = edtUrlAvt.getText().toString().trim();
                Glide.with(AddPostActivity.this)
                        .load(urlImage)
                        .into(imgContent);
            }
        });
        userName = edtUserName.getText().toString().trim();
        status = edtStatus.getText().toString().trim();
    }

    private void initView() {
        edtUrlAvt = findViewById(R.id.edtUrlAvt);
        edtUrlImage = findViewById(R.id.edtLinkImgContent);
        edtUserName = findViewById(R.id.edtUserName);
        edtStatus = findViewById(R.id.edtStatus);

        imgAvt = findViewById(R.id.imgAvt);
        imgContent = findViewById(R.id.imgContent);
        imgBack = findViewById(R.id.imgBack);
        btAdd = findViewById(R.id.btAdd);
    }
}