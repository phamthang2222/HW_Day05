package vn.phamthang.hw_day05.Model;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String urlAvt;
    private String userName;
    private String status;
    private String urlImgPost;
    private String time;
    private int countReaction;
    private boolean isLike;

    public Post() {
    }

    public Post(int id, String urlAvt, String userName, String status, String urlImgPost, String time, int countReaction, boolean isLike) {
        this.id = id;
        this.urlAvt = urlAvt;
        this.userName = userName;
        this.status = status;
        this.urlImgPost = urlImgPost;
        this.time = time;
        this.countReaction = countReaction;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlAvt() {
        return urlAvt;
    }

    public void setUrlAvt(String urlAvt) {
        this.urlAvt = urlAvt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlImgPost() {
        return urlImgPost;
    }

    public void setUrlImgPost(String urlImgPost) {
        this.urlImgPost = urlImgPost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCountReaction() {
        return countReaction;
    }

    public void setCountReaction(int countReaction) {
        this.countReaction = countReaction;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", urlAvt='" + urlAvt + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", urlImgPost='" + urlImgPost + '\'' +
                ", time='" + time + '\'' +
                ", countReaction=" + countReaction +
                '}';
    }
}

