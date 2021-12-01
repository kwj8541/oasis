package com.junyweb.oasis.vos.album;

import com.junyweb.oasis.entities.CommentEntity;
import com.junyweb.oasis.enums.album.CommentDeleteResult;

public class CommentDeleteVo extends CommentEntity {
    private String title;

    CommentDeleteResult commentDeleteResult;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CommentDeleteResult getCommentDeleteResult() {
        return commentDeleteResult;
    }

    public void setCommentDeleteResult(CommentDeleteResult commentDeleteResult) {
        this.commentDeleteResult = commentDeleteResult;
    }
}
