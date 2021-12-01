package com.junyweb.oasis.services;

import com.junyweb.oasis.entities.*;
import com.junyweb.oasis.enums.album.CommentDeleteResult;
import com.junyweb.oasis.enums.album.CommentResult;
import com.junyweb.oasis.enums.album.MusicResult;
import com.junyweb.oasis.mappers.IAlbumMapper;
import com.junyweb.oasis.vos.album.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;

@Service
public class AlbumService {
    private static class Config {
        public static final int ALBUM_COUNT_PER_PAGE = 4;
        public static final int MUSIC_COUNT_TOP_5 = 5;

        public Config() {
        }
    }

    private final IAlbumMapper albumMapper;

    @Autowired
    public AlbumService(IAlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    public void albumList(AlbumListVo albumListVo) { // 앨범 목록 메서드
        int albumCount = this.albumMapper.selectAlbumCount(albumListVo);
        int maxPageOffset = albumCount % Config.ALBUM_COUNT_PER_PAGE == 0 ? 0 : 1;
        int maxPage = (albumCount / Config.ALBUM_COUNT_PER_PAGE) + maxPageOffset;
        if (maxPage > 0) { // 페이지 설정
            int startPage = (((albumListVo.getPage() / 10) + 1) * 10) - 9;
            int endPage = Math.min(startPage + 9, maxPage);
            albumListVo.setMaxPage(maxPage);
            if (albumListVo.getPage() > maxPage) {
                albumListVo.setPage(maxPage);
            }
            albumListVo.setStartPage(startPage);
            albumListVo.setEndPage(endPage);
            albumListVo.setQueryLimit(Config.ALBUM_COUNT_PER_PAGE); // 한 화면에 띄울 앨범 갯수 지정
            albumListVo.setQueryOffset((albumListVo.getPage() - 1) * Config.ALBUM_COUNT_PER_PAGE);
        }

        albumListVo.setAlbumList(this.albumMapper.selectAlbumsByList(albumListVo));
    }

    public void albums(AlbumVo albumVo, MusicVo musicVo) { // 앨범 메서드
        AlbumEntity albumEntity = this.albumMapper.selectAlbum(albumVo);
        MusicEntity[] musicEntity = this.albumMapper.selectMusicByTitle(musicVo);
        musicVo.setMusicList(musicEntity);
        albumVo.setTitle(albumEntity.getTitle());
        albumVo.setArtist(albumEntity.getArtist());

        CommentEntity[] comments = Arrays.stream(this.albumMapper.selectComments(albumVo)).filter(x -> !x.isDeleted()).toArray(CommentEntity[]::new); // stream 인터페이스의 filter 메서드를 활용 , boolean 타입 isDeleted 가 참(1)이아닌 거짓(0) 인 댓글만 추출
        albumVo.setComments(comments);
    }

    public void albumImage(AlbumImageVo imageVo) { // 앨범 이미지 메서드
        ImageEntity imageEntity = this.albumMapper.selectImage(imageVo);
        imageVo.setImageFile(imageEntity.getImageFile());
    }

    public void music(MusicVo musicVo) { // 음악 메서드
        MusicEntity musicEntity = this.albumMapper.selectMusic(musicVo);
        if (musicEntity == null) {
            musicVo.setMusicResult(MusicResult.FAILURE);
            return;
        }
        musicEntity.setView(musicEntity.getView() + 1); // 음악 선택시 선택한 음악의 조회수 + 1
        this.albumMapper.updateMusic(musicEntity);
        musicVo.setView(musicEntity.getView());
        musicVo.setMusic(musicEntity.getMusic());
        musicVo.setMusicResult(MusicResult.SUCCESS);
    }

    public void putComment(UserEntity userEntity, CommentVo commentVo) { // 댓글 달기
        if (commentVo.getContent().length() > 50) { // 댓글이 50글자 이상 -> 정규화 실패
            commentVo.setCommentResult(CommentResult.NORMALIZATION_FAILURE);
            return;
        }
        if (userEntity == null) { // 로그인 안되어있을시 --> 댓글 쓰기 권한 없음
            commentVo.setCommentResult(CommentResult.NOT_ALLOWED);
            return;
        }
        commentVo.setUserEmail(userEntity.getEmail());
        commentVo.setUserNickname(userEntity.getNickname());
        commentVo.setContent(HtmlUtils.htmlEscape(commentVo.getContent()));
        this.albumMapper.insertComment(commentVo);
        commentVo.setCommentResult(CommentResult.SUCCESS);
    }

    public void deleteComment(UserEntity userEntity,CommentDeleteVo commentDeleteVo) { // 댓글 삭제 메서드

        CommentEntity commentEntity = this.albumMapper.selectComment(commentDeleteVo);
        if (commentEntity == null) { // 댓글 없으면
            commentDeleteVo.setCommentDeleteResult(CommentDeleteResult.FAILURE); // 결과에 댓글삭제 실패 담기
            return;
        }
        if (userEntity == null || !userEntity.getEmail().equals(commentEntity.getUserEmail())) { // 본인이 쓴 댓글 아니면
            commentDeleteVo.setCommentDeleteResult(CommentDeleteResult.NOT_ALLOWED); // 결과에 댓글삭제 권한없음 담기
            return;
        }
        commentEntity.setDeleted(true);
        this.albumMapper.updateComment(commentEntity);  // 댓글 업데이트
        commentDeleteVo.setCommentDeleteResult(CommentDeleteResult.SUCCESS);
    }

    public void selectMusicTop5(MusicVo musicVo) {   // 메인 화면에 조회수 TOP 5 호출 메서드
        MusicEntity[] musicEntity = this.albumMapper.selectMusicTop5(musicVo); // musicEntity 에 데이터베이스에서 가져온 음악들 담기
        musicVo.setMusicList(musicEntity);
        musicVo.setMusicLimit(Config.MUSIC_COUNT_TOP_5); // 뿌려줄 음악 갯수 5개로 제한
    }
}

