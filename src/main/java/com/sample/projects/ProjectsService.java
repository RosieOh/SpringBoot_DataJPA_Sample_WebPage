package com.sample.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    ProjectsRepository projectsRepository;

    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    public List<Projects> getProjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return projectsRepository.findByIdGreaterThanOrderByIdDesc(0l, pageable);
    }

    public Projects insertProjects(Projects projects) {
        return projectsRepository.save(projects);
    }

    public Projects getProject(Long id) {
        return projectsRepository.findById(id).get();
    }

    public void deleteProjects(Long id) {
        projectsRepository.deleteById(id);
    }

    public Projects updateProjects(Long id, Projects updatedProjects) {
        Projects projects = projectsRepository.findById(id).get();
        projects.update(updatedProjects);
        projectsRepository.save(projects);
        return projects;
    }
}
