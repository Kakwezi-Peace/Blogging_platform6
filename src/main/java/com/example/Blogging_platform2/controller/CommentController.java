package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.ApiResponse;
import com.example.Blogging_platform2.dto.CommentDto;
import com.example.Blogging_platform2.exception.CommentNotFoundException;
import com.example.Blogging_platform2.model.Comment;
import com.example.Blogging_platform2.service.CommentService;
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
@RequestMapping("/api/comments")
@Tag(name = "Comment Management", description = "APIs for managing comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    @Operation(summary = "Create a new comment")
    public ResponseEntity<ApiResponse<CommentDto>> createComment(@Valid @RequestBody CommentDto dto) {
        Comment created = service.saveComment(dto);
        CommentDto responseDto = convertToDto(created);

        return new ResponseEntity<>(
                ApiResponse.success("Comment created successfully", responseDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Get all comments for a post")
    public ResponseEntity<ApiResponse<List<CommentDto>>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentDto> comments = service.getCommentsByPost(postId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("Retrieved " + comments.size() + " comments", comments));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get comment by ID")
    public ResponseEntity<ApiResponse<CommentDto>> getComment(@PathVariable Long id) {
        Comment comment = service.getCommentById(id);
        if (comment == null) {
            throw new CommentNotFoundException("Comment with ID " + id + " not found");
        }
        return ResponseEntity.ok(ApiResponse.success("Comment retrieved successfully", convertToDto(comment)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comment by ID")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        if (service.getCommentById(id) == null) {
            throw new CommentNotFoundException("Comment with ID " + id + " not found");
        }
        service.deleteComment(id);
        return ResponseEntity.ok(ApiResponse.success("Comment deleted successfully"));
    }

    private CommentDto convertToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setPostId(comment.getPost().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}
