package com.project.QandAapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class QuestionWrapper{
    private Integer new_id;
    private String questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Integer new_id, String questionTitle, String category, String option1, String option2, String option3, String option4) {
        this.new_id = new_id;
        this.questionTitle = questionTitle;
        this.category = category;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
