package com.junyweb.oasis.controllers;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.album.CommentResult;
import com.junyweb.oasis.enums.album.MusicResult;
import com.junyweb.oasis.services.AlbumService;
import com.junyweb.oasis.vos.album.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller(value = "com/junyweb/oasis/controllers/AlbumController.java")
@RequestMapping(value = "/oasis")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(value = {
            "/album-list/{albumPage}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)   // 앨범 목록 GET
    public String albumListGet(@PathVariable(name = "albumPage") Optional<Integer> albumPage,
                               Model model) {
        AlbumListVo albumListVo = new AlbumListVo();
        albumListVo.setPage(albumPage.orElse(1));
        this.albumService.albumList(albumListVo);
        model.addAttribute("albumListVo", albumListVo.getAlbumList());
        model.addAttribute("listVo", albumListVo);
        return "album/albumList";
    }

    @RequestMapping(
            value = "/albums/{albumPage}/{title}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String albumsGet( // 앨범 GET
            @PathVariable(name = "albumPage") int albumPage,
            @PathVariable(name = "title") String title,
            HttpServletRequest request,
            Model model) {
        AlbumVo albumVo = new AlbumVo();
        MusicVo musicVo = new MusicVo();
        albumVo.setPage(albumPage);
        albumVo.setAlbumPage(albumPage);
        albumVo.setTitle(title);
        musicVo.setTitleName(title);
        this.albumService.albums(albumVo, musicVo);
        request.setAttribute("albums", albumVo);
        model.addAttribute("albums", albumVo);
        model.addAttribute("music", musicVo.getMusicList());
        return "album/albums";
    }

    @RequestMapping(value = "/albums/{albumPage}/{title}", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String albumsPost( // 댓글 쓰기 위한 앨범 POST 부분
            @PathVariable(name = "albumPage") int albumPage,
            @PathVariable(name = "title") String title,
            HttpServletRequest request,
            @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
            CommentVo commentVo) {
        commentVo.setAlbumPage(albumPage); // 선택한 앨범의 페이지를 commentVo 에 담기
        this.albumService.putComment(userEntity, commentVo); // 댓글 insert 하기위한 메서드 호출
        if (commentVo.getCommentResult() == CommentResult.SUCCESS) { // 댓글 작성 성공시 현 위치의 URL 로 리턴
            return "redirect:/oasis/albums/"+albumPage+"/"+title;
        } else { // 댓글 작성 실패시 서비스단에서 지정해놓은 결과를 request객체를 통해 앨범jsp로 넘겨줌
            request.setAttribute("commentResult", commentVo.getCommentResult());
            return "album/albums";
        }
    }

    @RequestMapping(value = "/album-image/{title}", method = RequestMethod.GET) // 앨범 이미지 뿌려주는 매핑
    public ResponseEntity<byte[]> imageGet(@PathVariable("title") String title, Model model) {
        AlbumImageVo albumImageVo = new AlbumImageVo();
        albumImageVo.setTitle(title);
        model.addAttribute("imageVo", albumImageVo);
        this.albumService.albumImage(albumImageVo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", albumImageVo.getMime());
        return new ResponseEntity<>(albumImageVo.getImageFile(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/albums/music/{title}/{musicName}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> musicGet(HttpServletResponse response,
                                           @SessionAttribute(name = "userEntity") UserEntity userEntity,
                                           @PathVariable(name = "title") String title,
                                           @PathVariable(name = "musicName") String musicName) throws IOException {
        MusicVo musicVo = new MusicVo();
        musicVo.setTitleName(title);
        musicVo.setMusicName(musicName);
        this.albumService.music(userEntity,musicVo);
        if (musicVo.getMusicResult() == MusicResult.FAILURE) {
            response.sendError(404);
            return null;
        }
        if (musicVo.getMusicResult() == MusicResult.NOT_ALLOWED) {
            response.sendRedirect("/oasis/verifyTicket");
            return null;
        }
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", "audio/mpeg");
        return new ResponseEntity<>(
                musicVo.getMusic(),
                headers,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/verifyTicket")
    public String ticketVerify() {
        return "/ticket/verifyTicket";
    }

    @RequestMapping(value = "/delete/{index}/{title}/{albumPage}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String deleteCommentGet(@PathVariable(name = "index") int index, // 댓글 index
                                   @PathVariable(name = "title") String title, // 댓글의 현 위치의 title
                                   @PathVariable(name = "albumPage") int albumPage, // 댓글의 현 위치의 앨범페이지
                                   @SessionAttribute(name = "userEntity") UserEntity userEntity,
                                   HttpServletRequest request,
                                   CommentDeleteVo commentDeleteVo) throws IOException {
        this.albumService.deleteComment(userEntity,commentDeleteVo);
        commentDeleteVo.setIndex(index);
        commentDeleteVo.setAlbumPage(albumPage);
        commentDeleteVo.setTitle(title);
        request.setAttribute("commentDeleteVo", commentDeleteVo);
        return "/album/commentDelete";
    }

}
