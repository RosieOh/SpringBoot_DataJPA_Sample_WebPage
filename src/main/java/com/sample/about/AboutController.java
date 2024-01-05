package com.sample.about;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/about/*")
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("list")
    public String getAbout(Model model) {
        String content = aboutService.getRecentContent();
        model.addAttribute("content", content);
        return "page/about/list";
    }

    @GetMapping("edit")
    public String getAboutEdit(Model model) {
        String content = aboutService.getRecentContent();
        model.addAttribute("content", content);
        return "page/about/edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    public About postAbout(@RequestBody About about) {
        return aboutService.insertAbout(about);
    }
}
