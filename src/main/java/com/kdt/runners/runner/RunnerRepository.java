package com.kdt.runners.runner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RunnerRepository extends JpaRepository<Runner, Long> {

    Optional<Runner> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}
