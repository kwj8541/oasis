package com.junyweb.oasis.services;

import com.junyweb.oasis.entities.MusicTicketEntity;
import com.junyweb.oasis.entities.TicketNameEntity;
import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.ticket.MusicTicketResult;
import com.junyweb.oasis.mappers.ITicketMapper;
import com.junyweb.oasis.vos.ticket.MusicTicketVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private static class Config {
        public static final int TICKET_EXPIRED_SECONDS = 5;

        public Config() {
        }
    }

    private final ITicketMapper ticketMapper;

    public TicketService(ITicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public void musicTicket(MusicTicketEntity musicTicketEntity, MusicTicketVo musicTicketVo, UserEntity userEntity) {
        TicketNameEntity ticketNameEntity = new TicketNameEntity();
        ticketNameEntity = this.ticketMapper.selectTicketName(ticketNameEntity); // DB 에있는 쿠폰 담기

        if (userEntity.isHaveTicket()) { // 회원이 이미 스트리밍을 이용중인지 확인
            musicTicketVo.setMusicTicketResult(MusicTicketResult.ALREADY_HAVE);
            return;
        }

        if (!musicTicketEntity.getTicketName().equals(ticketNameEntity.getTicketName())) { // 파라미터로 받아오는 쿠폰이름과 DB 에있는 쿠폰이름 일치여부 확인
            musicTicketVo.setMusicTicketResult(MusicTicketResult.FAILURE);
            return;
        }
        musicTicketVo.setMusicTicketResult(MusicTicketResult.SUCCESS);
        userEntity.setHaveTicket(true);
        this.ticketMapper.updateUserTicket(userEntity);
        musicTicketEntity.setExpiredAt(DateUtils.addMinutes(musicTicketEntity.getCreatedAt(),Config.TICKET_EXPIRED_SECONDS));
        this.ticketMapper.insertTicket(musicTicketEntity);

    }
}
