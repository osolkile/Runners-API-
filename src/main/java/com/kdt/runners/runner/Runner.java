package com.kdt.runners.runner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "crew_runner")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "login_id", nullable = false, unique = true, length = 20)
    private String loginId;

    @Setter
    @Column(name = "password_hash", nullable = false, length = 60)
    private String passwordHash;

    @Setter
    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 10)
    private RunnerRole role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static Runner create(String loginId, String passwordHash, String nickname, RunnerRole role) {
        Runner runner = new Runner();
        runner.loginId = loginId;
        runner.passwordHash = passwordHash;
        runner.nickname = nickname;
        runner.role = role;
        return runner;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
