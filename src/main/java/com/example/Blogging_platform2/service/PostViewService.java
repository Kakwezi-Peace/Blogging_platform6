package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.dao.PostViewDao;
import com.example.Blogging_platform2.exception.PostViewNotFoundException;
import com.example.Blogging_platform2.model.PostView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostViewService {

    private final PostViewDao postViewDao;

    public PostViewService(PostViewDao postViewDao) {
        this.postViewDao = postViewDao;
    }

    public PostView savePostView(PostView view) {
        return postViewDao.save(view);
    }

    public List<PostView> getViewsByPost(Long postId) {
        return postViewDao.findByPostId(postId);
    }

    public Optional<PostView> getPostViewById(Long id) {
        return postViewDao.findById(id);
    }

    public void deletePostView(Long id) {
        if (!postViewDao.existsById(id)) {
            throw new PostViewNotFoundException("View with ID " + id + " not found");
        }
        postViewDao.deleteById(id);
    }

    public PostView createView(PostView view) {
        return postViewDao.save(view);
    }

    public PostView getView(Long id) {
        return postViewDao.findById(id)
                .orElseThrow(() -> new PostViewNotFoundException("View with ID " + id + " not found"));
    }

    public Boolean deleteView(Long id) {
        if (!postViewDao.existsById(id)) {
            throw new PostViewNotFoundException("View with ID " + id + " not found");
        }
        postViewDao.deleteById(id);
        return true;
    }
}
