package com.junyweb.oasis.services;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.mappers.ITicketMapper;
import com.junyweb.oasis.vos.ticket.MusicTicketVo;

public class TicketService {
    private static class Config {
        public static final int TICKET_EXPIRED_SECONDS = 300;

        public Config() {
        }
    }

    private final ITicketMapper ticketMapper;

    public TicketService(ITicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public void musicTicket(UserEntity userEntity, MusicTicketVo musicTicketVo) {

    }
}
