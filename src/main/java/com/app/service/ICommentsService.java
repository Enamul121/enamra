package com.app.service;

import com.app.model.Comments;

import java.util.List;

public interface ICommentsService {

    List<Comments> getAllComments();

    Comments getCommentsByID(Long id);

    void saveComment(Comments comments);

    void deleteComments(Long id);

}
