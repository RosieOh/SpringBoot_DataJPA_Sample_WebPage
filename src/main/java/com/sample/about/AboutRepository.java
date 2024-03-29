package com.sample.about;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About, Long> {
    About findTopByOrderByIdDesc();
}
