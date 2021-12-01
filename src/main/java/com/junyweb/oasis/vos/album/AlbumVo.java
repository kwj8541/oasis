package com.junyweb.oasis.vos.album;

import com.junyweb.oasis.entities.AlbumEntity;
import com.junyweb.oasis.entities.CommentEntity;
import com.junyweb.oasis.entities.MusicEntity;
import com.junyweb.oasis.enums.album.CommentDeleteResult;

import java.util.stream.Stream;

public class AlbumVo extends AlbumEntity {
    AlbumEntity[] albums;
    CommentEntity[] comments;
    private int albumPage;
    CommentDeleteResult commentDeleteResult;

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    private int page;

    MusicEntity music;

    public AlbumEntity[] getAlbums() {
        return albums;
    }

    public void setAlbums(AlbumEntity[] albums) {
        this.albums = albums;
    }

    public MusicEntity getMusic() {
        return music;
    }

    public void setMusic(MusicEntity music) {
        this.music = music;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public CommentEntity[] getComments() {
        return comments;
    }

    public void setComments(CommentEntity[] comments) {
        this.comments = comments;
    }

    public int getAlbumPage() {
        return albumPage;
    }

    public void setAlbumPage(int albumPage) {
        this.albumPage = albumPage;
    }

    public CommentDeleteResult getCommentDeleteResult() {
        return commentDeleteResult;
    }

    public void setCommentDeleteResult(CommentDeleteResult commentDeleteResult) {
        this.commentDeleteResult = commentDeleteResult;
    }
}
