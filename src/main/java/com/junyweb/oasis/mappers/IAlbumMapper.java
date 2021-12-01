package com.junyweb.oasis.mappers;

import com.junyweb.oasis.entities.*;
import com.junyweb.oasis.vos.album.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IAlbumMapper {

    int updateComment(CommentEntity commentEntity);

    AlbumEntity selectAlbum(AlbumVo albumVo);

    int selectAlbumCount(AlbumListEntity albumListEntity);

    AlbumListEntity[] selectAlbumsByList(AlbumListVo albumListVo);

    ImageEntity selectImage(AlbumImageVo imageVo);

    MusicEntity[] selectMusicByTitle(MusicVo musicVo);

    MusicEntity selectMusic(MusicVo musicVo);

    int updateMusic(MusicEntity musicEntity); // view 늘리기위한 update

    int insertComment(CommentEntity commentEntity); // 댓글 입력 메서드

    CommentEntity[] selectComments(AlbumVo albumVo); // 댓글창에 댓글 호출 메서드

    CommentEntity selectComment(CommentEntity commentEntity); // 선택한댓글 삭제하기위해 해당 댓글 호출 메서드

    MusicEntity[] selectMusicTop5(MusicVo musicVo);

}
