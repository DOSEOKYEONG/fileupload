package com.ll.exam.app10.app.article.repository;

import com.ll.exam.app10.app.article.entity.Article;
import com.ll.exam.app10.app.keyword.entity.Keyword;
import com.ll.exam.app10.app.util.Util.Util;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ll.exam.app10.app.article.entity.QArticle.article;
import static com.ll.exam.app10.app.hashtag.entity.QHashTag.hashTag;
import static com.ll.exam.app10.app.keyword.entity.QKeyword.keyword;


@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> getQslArticlesOrderByIdDesc() {
        return jpaQueryFactory
                .select(article)
                .from(article)
                .orderBy(article.id.desc())
                .fetch();
    }

    @Override
    public List<Article> searchQsl(String kwType, String kw) {
        JPAQuery<Article> jpaQuery = jpaQueryFactory
                .select(article)
                .distinct()
                .from(article);

        if (Util.str.empty(kw) == false) {
            if (Util.str.eq(kwType, "hashTag")) {
                jpaQuery.innerJoin(hashTag)
                        .on(article.eq(hashTag.article))
                        .innerJoin(keyword)
                        .on(keyword.eq(hashTag.keyword))
                        .where(keyword.content.eq(kw));
            }
        }

        jpaQuery.orderBy(article.id.desc());

        return jpaQuery.fetch();
    }
}