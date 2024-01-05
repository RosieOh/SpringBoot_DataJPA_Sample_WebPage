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
        return "page/article";
    }

    // page 의 size 만큼 Article 리스트를 Json 으로 리턴함.
    @GetMapping("article_list")
    @ResponseBody
    public List<Article> getArticles(@RequestParam Integer page, @RequestParam Integer size) {
        return articleService.getArticles(page, size);
    }

    @GetMapping("detail/{id}")
    public String getArticle(@PathVariable(value = "id") Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "page/article_detail";
    }

    @DeleteMapping("detail/{id}")
    public void deleteArticle(@PathVariable(value = "id") Long id) {
        articleService.deleteArticle(id);
    }

    @PutMapping("edit/{id}")
    @ResponseBody
    public Article updateArticle(@PathVariable(value = "id") Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @GetMapping("edit")
    public String getArticleEdit(Model model) {
        model.addAttribute("update", false);
        return "page/article_edit";
    }

    @PostMapping("edit")
    @ResponseBody
    public Article postNewArticle(@RequestBody Article article) {
        return articleService.insertArticle(article);
    }
}
