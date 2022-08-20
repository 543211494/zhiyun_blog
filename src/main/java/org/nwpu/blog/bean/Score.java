package org.nwpu.blog.bean;

import lombok.Data;

@Data
public class Score {

    private Integer articleId;
    private Integer count;
    private Integer score;

    public Score(Integer articleId, Integer score) {
        this.articleId = articleId;
        this.score = score;
    }

    public Score() {
    }
}
