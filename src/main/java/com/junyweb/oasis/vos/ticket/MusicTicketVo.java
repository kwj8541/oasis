package com.junyweb.oasis.vos.ticket;

import com.junyweb.oasis.entities.MusicTicketEntity;
import com.junyweb.oasis.enums.ticket.MusicTicketResult;

public class MusicTicketVo extends MusicTicketEntity {
    private MusicTicketResult musicTicketResult;

    public MusicTicketResult getMusicTicketResult() {
        return musicTicketResult;
    }

    public void setMusicTicketResult(MusicTicketResult musicTicketResult) {
        this.musicTicketResult = musicTicketResult;
    }
}
