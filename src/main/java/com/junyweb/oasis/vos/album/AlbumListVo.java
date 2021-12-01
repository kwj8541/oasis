package com.junyweb.oasis.vos.album;

import com.junyweb.oasis.entities.AlbumEntity;
import com.junyweb.oasis.entities.AlbumListEntity;

public class AlbumListVo extends AlbumListEntity {
    private int page;
    private int startPage;
    private int endPage;
    private int maxPage;
    private int queryLimit;
    private int queryOffset;

    private AlbumEntity[] albums;

    private AlbumListEntity[] albumList;

    private AlbumListEntity albumListEntity;

    public AlbumListEntity getAlbumListEntity() {
        return albumListEntity;
    }

    public void setAlbumListEntity(AlbumListEntity albumListEntity) {
        this.albumListEntity = albumListEntity;
    }

    public AlbumListEntity[] getAlbumList() {
        return albumList;
    }

    public void setAlbumList(AlbumListEntity[] albumList) {
        this.albumList = albumList;
    }

    public AlbumEntity[] getAlbums() {
        return albums;
    }

    public void setAlbums(AlbumEntity[] albums) {
        this.albums = albums;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getQueryLimit() {
        return queryLimit;
    }

    public void setQueryLimit(int queryLimit) {
        this.queryLimit = queryLimit;
    }

    public int getQueryOffset() {
        return queryOffset;
    }

    public void setQueryOffset(int queryOffset) {
        this.queryOffset = queryOffset;
    }
}
