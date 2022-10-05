package com.ll.exam.app10.app.keyword.service;

import com.ll.exam.app10.app.keyword.entity.Keyword;
import com.ll.exam.app10.app.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeyWordService {

    private final KeywordRepository keywordRepository;

    public Keyword save(String keywordContent) {
        Optional<Keyword> keyword = keywordRepository.findByContent(keywordContent);

        if (keyword.isPresent()) {
            return keyword.get();
        }

        Keyword newKeyword = new Keyword().builder().content(keywordContent).build();

        keywordRepository.save(newKeyword);

        return newKeyword;
    }
}
