package com.sample.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    // projects 뷰를 보여줌.
    @GetMapping("/projects")
    public String getArticles() {
        return "page/projects";
    }

    // page 의 size 만큼 Projects 리스트를 Json 으로 리턴함.
    @GetMapping("/projects/list")
    @ResponseBody
    public List<Projects> getProjects(@RequestParam Integer page, @RequestParam Integer size) {
        return projectsService.getProjects(page, size);
    }

    // id 에 해당하는 project detail 뷰를 보여줌.
    @GetMapping("/project-detail/{id}")
    public String getProjectDetail(@PathVariable(value = "id") Long id, Model model) {
        Projects project = projectsService.getProject(id);

        System.out.println(project);

        model.addAttribute("project", project);
        return "page/project_detail";
    }

    // id 에 해당하는 project 를 삭제함.
    @DeleteMapping("/project-detail/{id}")
    public void deleteProject(@PathVariable(value = "id") Long id) {
        projectsService.deleteProjects(id);
    }

    // id 에 해당하는 project 를 수정하는 뷰를 보여줌.
    @GetMapping("/project-edit/{id}")
    public String getProjectEdit(@PathVariable(value = "id") Long id, Model model) {
        Projects project = projectsService.getProject(id);

        model.addAttribute("project", project);
        model.addAttribute("is_update", true);
        return "page/project_edit";
    }

    // id 에 해당하는 project 을 업데이트 함.
    @PutMapping("/project-edit/{id}")
    public @ResponseBody
    Projects putUpdateProject(@PathVariable(value="id") Long id, @RequestBody Projects project) {
        return projectsService.updateProjects(id, project);
    }

    // 새로운 project 을 만드는 뷰를 보여줌.
    @GetMapping("/project-edit")
    public String getProjectEdit(Model model) {
        model.addAttribute("is_update", false);
        return "page/project_edit";
    }

    // 새로운 project 을 만듬.
    @PostMapping("/project-edit")
    public @ResponseBody
    Projects postNewProject(@RequestBody Projects project) {
        return projectsService.insertProjects(project);
    }
}
