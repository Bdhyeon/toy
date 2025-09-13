package com.bdh.toy.book.entity;

import com.bdh.toy.entity.Score;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 500)
    private String title;
    @Column(length = 500)
    private String writer;
    @Column(length = 500)
    private String publisher;
    @Column
    private LocalDate publishedAt;
    private Score score;

}
