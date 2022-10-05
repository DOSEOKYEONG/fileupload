package com.ll.exam.app10.app.keyword.entity;

import com.ll.exam.app10.app.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Keyword extends BaseEntity {
    private String content;

    public String getListUrl() {
        return "/article/list?kwType=keyword&kw=%s".formatted(content);
    }
}
