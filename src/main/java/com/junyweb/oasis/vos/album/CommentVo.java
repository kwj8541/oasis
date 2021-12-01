package com.junyweb.oasis.vos.album;

import com.junyweb.oasis.entities.CommentEntity;
import com.junyweb.oasis.enums.album.CommentResult;

public class CommentVo extends CommentEntity {
    private CommentResult commentResult;

    public CommentResult getCommentResult() {
        return commentResult;
    }

    public void setCommentResult(CommentResult commentResult) {
        this.commentResult = commentResult;
    }
}
