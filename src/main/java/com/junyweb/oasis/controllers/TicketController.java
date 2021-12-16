package com.junyweb.oasis.controllers;
import com.junyweb.oasis.entities.MusicTicketEntity;
import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.ticket.MusicTicketResult;
import com.junyweb.oasis.services.TicketService;
import com.junyweb.oasis.vos.ticket.MusicTicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "com/junyweb/oasis/controllers/TicketController.java")
@RequestMapping(value = "/oasis")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/verifyTicket")
    public String ticketVerify() {
        return "/ticket/verifyTicket";
    }

    @RequestMapping(value = "/buyMusicTicket", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String buyMusicTicketGET() {
        return "/ticket/buyMusicTicket";
    }

    @RequestMapping(value = "/buyMusicTicket", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String buyMusicTicketPost(@SessionAttribute(name = "userEntity") UserEntity userEntity,
                                     MusicTicketEntity musicTicketEntity,
                                     Model model){
        musicTicketEntity.setUserEmail(userEntity.getEmail());   // 세션에 담긴 회원 이메일 담기@
        MusicTicketVo musicTicketVo = new MusicTicketVo();
        this.ticketService.musicTicket(musicTicketEntity,musicTicketVo,userEntity);
        model.addAttribute("resultOfTicket", musicTicketVo.getMusicTicketResult());
        return "/ticket/verifyCoupon";
    }


}