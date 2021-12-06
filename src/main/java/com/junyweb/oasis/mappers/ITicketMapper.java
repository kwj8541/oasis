package com.junyweb.oasis.mappers;

import com.junyweb.oasis.entities.MusicTicketEntity;
import com.junyweb.oasis.entities.TicketNameEntity;
import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.vos.ticket.MusicTicketVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITicketMapper {
    int insertTicket(MusicTicketEntity musicTicketEntity);

    int updateTicket(MusicTicketVo musicTicketVo);

    TicketNameEntity selectTicketName(TicketNameEntity ticketNameEntity);

    int updateUserTicket(UserEntity userEntity);

    int insertTicketTest(MusicTicketEntity musicTicketEntity);

}
