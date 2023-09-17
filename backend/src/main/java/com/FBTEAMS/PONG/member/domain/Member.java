package com.FBTEAMS.PONG.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id                                                     // id 어노테이션을 사용하여 기본키를 직접 할당한다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE)     // 기본 키를 직접 할당하는 대신 데이터베이스가 생성해주는 값을 사용하려면 @GeneratedValue를 사용하였다.
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private Password password;



}