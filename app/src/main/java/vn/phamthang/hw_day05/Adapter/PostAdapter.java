package vn.phamthang.hw_day05.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.phamthang.hw_day05.Interface.IPostClickListener;
import vn.phamthang.hw_day05.Model.Post;
import vn.phamthang.hw_day05.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<Post> mList;
    private Context mContext;
    private IPostClickListener callback;
    public PostAdapter(ArrayList<Post> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }
    public IPostClickListener getCallback() {
        return callback;
    }
    public void setCallback(IPostClickListener callback) {
        this.callback = callback;
    }
    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Post post = mList.get(position);
        holder.tvUserName.setText(post.getUserName());
        holder.tvStatus.setText(post.getStatus());
        holder.tvTime.setText(post.getTime());
        holder.tvTotalReaction.setText(post.getCountReaction() + " ");

        Glide.with(mContext).load(post.getUrlAvt()).into(holder.imgAvt);
        Glide.with(mContext).load(post.getUrlImgPost()).into(holder.imgContent);
        int likeReaction = post.isLike()?R.drawable.ic_liked:R.drawable.ic_like;
        holder.imgLike.setBackgroundResource(likeReaction);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgAvt, imgContent, imgDelete, imgAdd, imgLike, imgEdit;
        TextView tvTime, tvUserName, tvStatus, tvTotalReaction;
        LinearLayout llReaction;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgAdd = itemView.findViewById(R.id.imgAdd);
            imgAvt = itemView.findViewById(R.id.circleImageView);
            imgContent = itemView.findViewById(R.id.imgContent);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTotalReaction = itemView.findViewById(R.id.tvTotalReaction);
            llReaction = itemView.findViewById(R.id.llReaction);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgEdit = itemView.findViewById(R.id.imgEdit);

            imgDelete.setOnClickListener(this);
            imgLike.setOnClickListener(this);
            imgEdit.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.imgDelete) {
                if(callback != null){
                    callback.onDelete(getAdapterPosition());
                }
            }
            if (v.getId() == R.id.llReaction) {
                if(callback != null){
                    callback.onUpdateReaction(getAdapterPosition());
                }
            }
            if (v.getId() == R.id.imgEdit) {
                if(callback != null){
                    callback.onEdit(getAdapterPosition());
                }
            }
        }
    }
}
