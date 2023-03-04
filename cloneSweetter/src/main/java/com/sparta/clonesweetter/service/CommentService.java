package com.sparta.clonesweetter.service;

import com.sparta.clonesweetter.dto.CommentResponseDto;
import com.sparta.clonesweetter.dto.StatusResponseDto;
import com.sparta.clonesweetter.entity.Comment;
import com.sparta.clonesweetter.entity.CommentLike;
import com.sparta.clonesweetter.entity.User;
import com.sparta.clonesweetter.entity.UserRoleEnum;
import com.sparta.clonesweetter.repository.CommentLikeRepository;
import com.sparta.clonesweetter.repository.CommentRepository;
import com.sparta.clonesweetter.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikeRepository commentLikeRepository;

//    public StatusResponseDto<List<CommentResponseDto>> getComments(Long id){
//        List<Comment> list = commentRepository.findAllByProjectId(id);
//        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
//        for (Comment)
//    }

    // 댓글 작성
    public StatusResponseDto<CommentResponseDto> createComment(Long id, String content, UserDetailsImpl userDetails) {

        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("등록되지 않은 게시글입니다."));
        Comment comment = new Comment(userDetails.getUser(), post, content);
        commentRepository.save(comment);
        return StatusResponseDto.success(new CommentResponseDto(comment));
    }


    // 댓글 삭제
    public StatusResponseDto<String> deleteComment(Long id, UserDetailsImpl userDetails) throws AuthenticationException {
        User user = userDetails.getUser();
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("댓글을 찾을 수 없음")
        );
        if (user.getRole() == UserRoleEnum.ADMIN || user.getUsername().equals(comment.getUser().getUsername())) {
            commentRepository.deleteById(id);
            return StatusResponseDto.success("delete success!");
        } else {
            throw new AuthenticationException("작성자만 수정이 가능합니다.");
        }
    }

    // 댓글 좋아요
    public StatusResponseDto<String> likeComment(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글"));
        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUser(comment, userDetails.getUser());
        if (optionalCommentLike.isPresent()) { // 유저가 이미 좋아요를 눌렀을 때
            commentLikeRepository.deleteById(optionalCommentLike.get().getId());
            return StatusResponseDto.success("댓글 좋아요 취소");
        }
        commentLikeRepository.save(new CommentLike(comment, userDetails.getUser()));
        return StatusResponseDto.success("댓글 좋아요 성공");
    }


}
