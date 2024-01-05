package com.sample.about;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AboutService {

    @Autowired
    AboutRepository aboutRepository;

    public String getRecentContent() {
        About content = aboutRepository.findTopByOrderByIdDesc();
        if (content == null) {
            return "";
        } else {
            return content.getContent();
        }
    }

    public About insertAbout(About about) {
        return aboutRepository.save(about);
    }
}