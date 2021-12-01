package com.junyweb.oasis.controllers;

import com.junyweb.oasis.services.AlbumService;
import com.junyweb.oasis.vos.album.MusicVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private final AlbumService albumService;

    public MainController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String mainGet(Model model) { // 메인 화면 GET
        MusicVo musicVo = new MusicVo();
        this.albumService.selectMusicTop5(musicVo); // 메인화면에서 조회수 TOP 5 음악을 호출하기위한 메서드
        model.addAttribute("musicTop5", musicVo.getMusicList()); // model 에 음악 리스트 담아주기
        return "root/main";
    }
}
