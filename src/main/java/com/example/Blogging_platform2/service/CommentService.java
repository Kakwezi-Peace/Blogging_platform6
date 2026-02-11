package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.dto.CommentDto;
import com.example.Blogging_platform2.exception.CommentNotFoundException;
import com.example.Blogging_platform2.exception.PostNotFoundException;
import com.example.Blogging_platform2.exception.UserNotFoundException;
import com.example.Blogging_platform2.model.Comment;
import com.example.Blogging_platform2.model.Post;
import com.example.Blogging_platform2.model.User;
import com.example.Blogging_platform2.dao.CommentDao;
import com.example.Blogging_platform2.dao.PostDao;
import com.example.Blogging_platform2.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentDao commentDao;

    private final UserDao userDao;
    private final PostDao postDao;

    public CommentService(CommentDao commentDao, UserDao userDao, PostDao postDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    public Comment saveComment(CommentDto dto) {

        Post post = postDao.findById(dto.getPostId()).orElseThrow(() -> new PostNotFoundException(dto.getPostId()));
        User user = userDao.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException(dto.getUserId()));
        Comment comment = new Comment();

        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(dto.getContent());

        return commentDao.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentDao.findById(id).orElseThrow(()->new CommentNotFoundException(id));
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentDao.findAllByPostId(postId);
    }

    public void deleteComment(Long id) {
      Comment comment = commentDao.findById(id).orElseThrow(()-> new CommentNotFoundException(id));
        commentDao.deleteById(id);
    }
}
