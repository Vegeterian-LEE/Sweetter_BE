package com.sparta.clonesweetter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "BookMark")
@NoArgsConstructor
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public BookMark(User user) {
        this.user = user;
    }
}
