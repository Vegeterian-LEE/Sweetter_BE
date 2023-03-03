package com.sparta.clonesweetter.entity;

import com.sparta.clonesweetter.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;

    public Comment(User user, Project project, String content){
        this.user = user;
        this.project = project;
        this.content = content;
    }

    public  void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }

}
