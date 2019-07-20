package com.app.service.impl;


import com.app.model.Comments;
import com.app.repository.CommentRepo;
import com.app.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService implements ICommentsService {

    @Autowired
    private CommentRepo commentRepo;


    @Override
    public List<Comments> getAllComments() {
        return commentRepo.findAll();
    }

    @Override
    public Comments getCommentsByID(Long id) {
        return commentRepo.findById(id).get();
    }

    @Override
    public void saveComment(Comments comments) {
        commentRepo.save(comments);
    }

    @Override
    public void deleteComments(Long id) {
        commentRepo.deleteById(id);
    }
}
