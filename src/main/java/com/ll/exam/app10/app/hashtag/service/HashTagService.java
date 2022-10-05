package com.ll.exam.app10.app.hashtag.service;

import com.ll.exam.app10.app.article.entity.Article;
import com.ll.exam.app10.app.hashtag.entity.HashTag;
import com.ll.exam.app10.app.hashtag.repository.HashTagRepository;
import com.ll.exam.app10.app.keyword.entity.Keyword;
import com.ll.exam.app10.app.keyword.service.KeyWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    private final KeyWordService keyWordService;

    public void applyHashTags(Article article, String hashTagContents) {
        // 기존  해시태그들 가져오기
        // 새 해시태그 키워드를 리스트로 만들기
        // 삭제할 키워드 계산
        // 추가할 키워드 계산

        List<HashTag> oldHashTags = getHashTags(article); // 기존 해시 태그들

        List<String> keywordContents = Arrays.stream(hashTagContents.split("#"))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        List<HashTag> needToDelete = new ArrayList<>();

        for (HashTag hashTag : oldHashTags) {
            hashTag.getKeyword().getContent();

            boolean contains = keywordContents.stream().anyMatch(s -> s.equals(hashTag.getKeyword().getContent()));

            if (contains == false) {
                needToDelete.add(hashTag);
            }
        }

        keywordContents.forEach(keywordContent -> {
            saveHashTag(article, keywordContent);
        });

        needToDelete.forEach(hashTag -> {
            hashTagRepository.delete(hashTag);
        });
    }

    private HashTag saveHashTag(Article article, String keywordContent) {
        Keyword keyword = keyWordService.save(keywordContent);

        Optional<HashTag> optionalHashTag = hashTagRepository.findByArticleIdAndKeywordId(article.getId(), keyword.getId());

        if (optionalHashTag.isPresent()) {
            return optionalHashTag.get();
        }

        HashTag hashTag = HashTag.builder()
                .article(article)
                .keyword(keyword)
                .build();

        hashTagRepository.save(hashTag);

        return hashTag;
    }

    public List<HashTag> getHashTags(Article article) {
        return hashTagRepository.findByArticleId(article.getId());
    }
}
