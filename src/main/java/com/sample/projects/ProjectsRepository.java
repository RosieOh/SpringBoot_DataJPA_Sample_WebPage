package com.sample.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByIdGreaterThanOrderByIdDesc(Long id, Pageable paging);
}