package com.bdh.toy.book.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Score {
   A(5)
    , B(4)
    , C(3)
    , D(4)
    , E(5)
    , F(0);

   private final Integer score;
}
