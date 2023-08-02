package com.project.QandAapp.service;

import com.project.QandAapp.dao.QuestionDao;
import com.project.QandAapp.dao.QuizDao;
import com.project.QandAapp.model.Question;
import com.project.QandAapp.model.QuestionWrapper;
import com.project.QandAapp.model.Quiz;
import com.project.QandAapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questionList = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz = quizDao.findById(id);
       List<Question> questionsFromDb = quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser = new ArrayList<>();
       for(Question q: questionsFromDb){
           QuestionWrapper qw = new QuestionWrapper(q.getNew_id(), q.getQuestionTitle(),q.getCategory(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionsForUser.add(qw);
       }

       return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int result = 0;
        int i = 0;
        for(Response resp : responses){
            if(resp.getResponse().equals(questions.get(i).getRightAnswer())){
                result++;
            }
            i++;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
