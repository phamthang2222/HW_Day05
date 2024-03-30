package vn.phamthang.hw_day05.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.hw_day05.Adapter.PostAdapter;
import vn.phamthang.hw_day05.Interface.IPostClickListener;
import vn.phamthang.hw_day05.Model.Post;
import vn.phamthang.hw_day05.R;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Post> mListPost;
    private RecyclerView rcvListPost;
    private PostAdapter postAdapter;
    private ImageView imgAdd;
    private Post getPost;
    private int pos_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
//        initNewData();
    }

    private void initData() {
        mListPost = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Post post = new Post();
            post.setLike(true);
            post.setUrlAvt("https://gcs.tripi.vn/public-tripi/tripi-feed/img/474082Kvj/avt-de-thuong-cute_044342433.jpg");
            post.setUserName("John Cena " + i);
            post.setTime(i * 2 + "h");
            post.setStatus("John Cena tới chơi lần thứ " + i);
            post.setUrlImgPost("https://i.kym-cdn.com/entries/icons/facebook/000/044/634/johncenaheadphones1.jpg");
            post.setCountReaction(i*3+34);
            mListPost.add(post);
        }
    }

    private void initView() {
        rcvListPost = findViewById(R.id.rcvListPost);
        postAdapter = new PostAdapter(mListPost, MainActivity.this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        rcvListPost.setLayoutManager(layoutManager);

        postAdapter.setCallback(clickListener);
        rcvListPost.setAdapter(postAdapter);

        imgAdd = findViewById(R.id.imgAdd);
        imgAdd.setOnClickListener(v -> {
//            startActivity(new Intent(MainActivity.this,AddPostActivity.class));
//            finish();

            Post newPost = new Post();
            newPost.setUserName("userName");
            newPost.setUrlAvt("https://gcs.tripi.vn/public-tripi/tripi-feed/img/474082Kvj/avt-de-thuong-cute_044342433.jpg");
            newPost.setUrlImgPost("https://gcs.tripi.vn/public-tripi/tripi-feed/img/474082Kvj/avt-de-thuong-cute_044342433.jpg");
            newPost.setTime("now");
            newPost.setStatus("status new");
            newPost.setCountReaction(1345);
            newPost.setLike(false);

            mListPost.add(newPost);

            postAdapter.notifyDataSetChanged();
        });
    }

    private void initNewData() {
        Post newPost = (Post) getIntent().getSerializableExtra("NEWPOST");

        if(newPost != null){
            Log.d("Initnewdata",newPost.toString());
            mListPost.add(newPost);
            postAdapter.notifyDataSetChanged();
        }
    }
    private IPostClickListener clickListener =  new IPostClickListener() {
        @Override
        public void onDelete(int pos) {
            mListPost.remove(pos);
            postAdapter.notifyItemRemoved(pos);
        }

        @Override
        public void onUpdateReaction(int pos) {
            Post post = mListPost.get(pos);
            post.setLike(!post.isLike());
            mListPost.set(pos,post);

            postAdapter.notifyItemChanged(pos);
        }

        @Override
        public void onEdit(int pos) {
            pos_edit = pos;
            Post editPost = mListPost.get(pos);
            Intent intent;

            intent = new Intent(MainActivity.this,EditPostActivity.class);
            intent.putExtra("EditPost",editPost);

            startActivity(intent);


        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && resultCode == RESULT_OK) {
            getPost = (Post) data.getSerializableExtra("EditPost");
            mListPost.add(pos_edit,getPost);
            postAdapter.notifyDataSetChanged();
        }
    }
}