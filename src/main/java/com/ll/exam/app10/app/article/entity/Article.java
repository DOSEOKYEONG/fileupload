package com.ll.exam.app10.app.article.entity;

import com.ll.exam.app10.app.base.entity.BaseEntity;
import com.ll.exam.app10.app.hashtag.entity.HashTag;
import com.ll.exam.app10.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Article extends BaseEntity {

    @ManyToOne
    private Member author;

    private String subject;

    private String content;

    public String getExtra_inputValue_hashTagContents() {
        Map<String, Object> extra = getExtra();

        if (extra.containsKey("hashTags") == false) {
            return "";
        }

        List<HashTag> hashTagList = (List<HashTag>) extra.get("hashTags");

        if (hashTagList.isEmpty()) {
            return "";
        }

        return "#" + hashTagList
                .stream()
                .map(hashTag -> hashTag.getKeyword().getContent())
                .sorted()
                .collect(Collectors.joining(" #"))
                .trim();


    }
}
