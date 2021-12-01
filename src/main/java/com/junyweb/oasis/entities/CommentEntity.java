package com.junyweb.oasis.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentEntity {
    private int index;
    private String userEmail;
    private String userNickname;
    private Date createdAt;
    private String content;
    private int albumPage;
    private boolean isDeleted;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAlbumPage() {
        return albumPage;
    }

    public void setAlbumPage(int albumPage) {
        this.albumPage = albumPage;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String formatCreatedAt() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.createdAt);
    }
}
