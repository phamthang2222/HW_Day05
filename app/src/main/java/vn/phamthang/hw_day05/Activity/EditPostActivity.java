package vn.phamthang.hw_day05.Activity;

import android.content.Intent;
import android.os.Bundle;
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

public class EditPostActivity extends AppCompatActivity {
    private EditText edtUrlAvt, edtUrlImage, edtUserName, edtStatus;
    private ImageView imgAvt,imgContent,imgBack;
    private Button btEdit;

    private Post editPost ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        editPost = (Post) getIntent().getSerializableExtra("EditPost");

        initView();
        initData();
        imgBack.setOnClickListener(v -> {
            finish();
        });
        btEdit.setOnClickListener(v -> {
            String urlAvt,urlImage,userName,status;
            urlAvt = edtUrlAvt.getText().toString();
            urlImage = edtUrlImage.getText().toString();
            userName = edtUserName.getText().toString();
            status = edtStatus.getText().toString();


            editPost.setUserName(userName);
            editPost.setUrlImgPost(urlImage);
            editPost.setStatus(status);
            editPost.setUrlAvt( urlAvt);

            Intent intent = new Intent();
            intent.putExtra("EditPost",editPost);
            setResult(RESULT_OK,intent);
            finish();

        });
    }


    private void initView() {
        edtUrlAvt = findViewById(R.id.edtUrlAvt);
        edtUrlImage = findViewById(R.id.edtLinkImgContent);
        edtUserName = findViewById(R.id.edtUserName);
        edtStatus = findViewById(R.id.edtStatus);

        imgAvt = findViewById(R.id.imgAvt);
        imgContent = findViewById(R.id.imgContent);
        imgBack = findViewById(R.id.imgBack);
        btEdit = findViewById(R.id.btAdd);
    }
    private void initData() {

        edtUrlAvt.setText(editPost.getUrlAvt());
        edtStatus.setText(editPost.getStatus());
        edtUserName.setText(editPost.getUserName());
        edtUrlImage.setText(editPost.getUrlImgPost());

        Glide.with(EditPostActivity.this)
                .load(editPost.getUrlAvt())
                .into(imgAvt);
        Glide.with(EditPostActivity.this)
                .load(editPost.getUrlImgPost())
                .into(imgContent);
    }


}