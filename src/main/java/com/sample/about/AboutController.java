package com.sample.about;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("/about")
    public String getAbout(Model model) {
        String content = aboutService.getRecentContent();
        model.addAttribute("content", content);
        return "page/about";
    }

    @GetMapping("/about_edit")
    public String getAboutEdit(Model model) {
        String content = aboutService.getRecentContent();
        model.addAttribute("content", content);
        return "page/about_edit";
    }

    @PostMapping("/about_edit")
    @ResponseBody
    public About postAbout(@RequestBody About about) {
        return aboutService.insertAbout(about);
    }
}
