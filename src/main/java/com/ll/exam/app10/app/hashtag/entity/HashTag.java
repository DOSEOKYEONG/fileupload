package com.ll.exam.app10.app.hashtag.entity;

import com.ll.exam.app10.app.article.entity.Article;
import com.ll.exam.app10.app.base.entity.BaseEntity;
import com.ll.exam.app10.app.keyword.entity.Keyword;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class HashTag extends BaseEntity {

    @ManyToOne
    @ToString.Exclude
    private Article article;

    @ManyToOne
    @ToString.Exclude
    private Keyword keyword;
}