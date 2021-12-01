package com.junyweb.oasis.vos.album;

import com.junyweb.oasis.entities.MusicEntity;
import com.junyweb.oasis.enums.album.MusicResult;

public class MusicVo extends MusicEntity {
    private MusicResult musicResult;

    private MusicEntity[] musicList;

    private int musicLimit;

    private int queryLimit;

    public MusicEntity[] getMusicList() {
        return musicList;
    }

    public void setMusicList(MusicEntity[] musicList) {
        this.musicList = musicList;
    }

    public MusicResult getMusicResult() {
        return musicResult;
    }

    public void setMusicResult(MusicResult musicResult) {
        this.musicResult = musicResult;
    }

    public int getQueryLimit() {
        return queryLimit;
    }

    public void setQueryLimit(int queryLimit) {
        this.queryLimit = queryLimit;
    }

    public int getMusicLimit() {
        return musicLimit;
    }

    public void setMusicLimit(int musicLimit) {
        this.musicLimit = musicLimit;
    }
}
