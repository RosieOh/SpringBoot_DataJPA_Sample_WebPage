package com.sample.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article/*")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("list")
    public String getArticles(Model model) {
        return "page/article/list";
    }

    // page 의 size 만큼 Article 리스트를 Json 으로 리턴함.
    @GetMapping("list1")
    @ResponseBody
    public List<Article> getArticles(@RequestParam Integer page, @RequestParam Integer size) {
        return articleService.getArticles(page, size);
    }

    @GetMapping("detail/{id}")
    public String getArticle(@PathVariable(value = "id") Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "page/article/detail";
    }

    @DeleteMapping("detail/{id}")
    public void deleteArticle(@PathVariable(value = "id") Long id) {
        articleService.deleteArticle(id);
    }

    // id 에 해당하는 article 을 수정하는 뷰를 보여줌.
    @GetMapping("/edit/{id}")
    public String getArticleEdit(@PathVariable(value = "id") Long id, Model model) {
        Article article = articleService.getArticle(id);

        model.addAttribute("article", article);
        model.addAttribute("is_update", true);
        return "page/article/edit";
    }

    @PutMapping("edit/{id}")
    @ResponseBody
    public Article updateArticle(@PathVariable(value = "id") Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @GetMapping("edit")
    public String getArticleEdit(Model model) {
        model.addAttribute("is_update", false);
        return "page/article/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    public Article postNewArticle(@RequestBody Article article) {
        return articleService.insertArticle(article);
    }
}
