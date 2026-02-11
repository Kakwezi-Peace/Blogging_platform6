package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.dao.ReviewDao;
import com.example.Blogging_platform2.exception.ReviewNotFoundException;
import com.example.Blogging_platform2.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewDao reviewDao;

    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public Review saveReview(Review review) {
        return reviewDao.save(review);
    }

    public Review getReviewById(Long id) {
        return reviewDao.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with ID " + id + " not found"));
    }

    public List<Review> getReviewsByPost(Long postId) {
        return reviewDao.findAllByPostId(postId);
    }

    public Review createReview(Review review) {
        return reviewDao.save(review);
    }

    public Boolean deleteReview(Long id) {
        if (!reviewDao.existsById(id)) {
            throw new ReviewNotFoundException("Review with ID " + id + " not found");
        }
        reviewDao.deleteById(id);
        return true;
    }
}

