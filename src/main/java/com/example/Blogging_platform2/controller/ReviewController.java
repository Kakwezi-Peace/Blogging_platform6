package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.ApiResponse;
import com.example.Blogging_platform2.dto.ReviewDto;
import com.example.Blogging_platform2.exception.ReviewNotFoundException;
import com.example.Blogging_platform2.model.Post;
import com.example.Blogging_platform2.model.Review;
import com.example.Blogging_platform2.model.User;
import com.example.Blogging_platform2.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review Management", description = "APIs for managing reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    @Operation(summary = "Create a new review")
    public ResponseEntity<ApiResponse<ReviewDto>> createReview(@Valid @RequestBody ReviewDto dto) {
        Review review = new Review();

        // JPA expects entity references instead of raw IDs
        Post post = new Post();
        post.setId(dto.getPostId());
        review.setPost(post);

        User user = new User();
        user.setId(dto.getUserId());
        review.setUser(user);

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        Review created = service.saveReview(review);
        ReviewDto responseDto = convertToDto(created);

        return new ResponseEntity<>(
                ApiResponse.success("Review created successfully", responseDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Get all reviews for a post")
    public ResponseEntity<ApiResponse<List<ReviewDto>>> getReviewsByPost(@PathVariable Long postId) {
        List<ReviewDto> reviews = service.getReviewsByPost(postId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("Retrieved " + reviews.size() + " reviews", reviews));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get review by ID")
    public ResponseEntity<ApiResponse<ReviewDto>> getReview(@PathVariable Long id) {
        Review review = service.getReviewById(id);
        return ResponseEntity.ok(ApiResponse.success("Review retrieved successfully", convertToDto(review)));
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Delete review by ID")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long id) {
        service.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success("Review deleted successfully"));
    }


    // Helper method for DTO conversion
    private ReviewDto convertToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());

        if (review.getPost() != null) {
            dto.setPostId(review.getPost().getId());
        }

        if (review.getUser() != null) {
            dto.setUserId(review.getUser().getId());
        }

        dto.setRating(review.getRating());
        dto.setComment(review.getComment());

        if (review.getCreatedAt() != null) {
            dto.setCreatedAt(review.getCreatedAt().toString()); // or use formatter
        }

        return dto;
    }


}
