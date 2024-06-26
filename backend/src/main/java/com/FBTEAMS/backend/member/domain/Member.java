package com.FBTEAMS.backend.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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

    @Embedded
    private UserName userName;

    @Column(nullable = false)
    private boolean deleted;


    public Member(UserId userId, Password password, UserName userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }

    public boolean isSameUserId(Member member) {
        return userId.getValue().equals(member.userId.getValue());
    }

    public void delete() {
        userId = null;
        password = null;
        userName = null;
        deleted = true;
    }

    public void changePassword(String password, PasswordEncoder encoder) {
        this.password = this.password.update(password, encoder);
    }

    public void changeUserName(String userName) {
        this.userName = this.userName.update(userName);
    }

    public String getUserId() {
        return Optional.ofNullable(userId)
                .map(UserId::getValue)
                .orElse("");
    }

    public String getPassword() {
        return Optional.ofNullable(password)
                .map(Password::getValue)
                .orElse("");
    }

    public String getUserName() {
        return Optional.ofNullable(userName)
                .map(UserName::getValue)
                .orElse("");
    }
}
