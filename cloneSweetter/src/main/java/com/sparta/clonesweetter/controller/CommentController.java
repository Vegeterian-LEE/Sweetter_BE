package com.sparta.clonesweetter.controller;

import com.sparta.clonesweetter.dto.CommentResponseDto;
import com.sparta.clonesweetter.dto.StatusResponseDto;
import com.sparta.clonesweetter.security.UserDetailsImpl;
import com.sparta.clonesweetter.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {


    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment/{id}")
    public StatusResponseDto<CommentResponseDto> createComment(@PathVariable Long id, String content, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id, content, userDetails);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{id}")
    public StatusResponseDto<String> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws AuthenticationException {
        return commentService.deleteComment(id, userDetails);
    }

//    @GetMapping("/comment/{projectId}")
//    public StatusResponseDto<List<CommentResponseDto>> getComments(@PathVariable Long projectId){
//        return commentService.getComments(projectId);
//    }

    // 댓글 좋아요
    @PostMapping("/comment/like/{id}")
    public StatusResponseDto<String> likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.likeComment(id, userDetails);
    }

}
