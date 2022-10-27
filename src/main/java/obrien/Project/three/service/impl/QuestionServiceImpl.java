package obrien.Project.three.service.impl;


import lombok.extern.slf4j.Slf4j;
import obrien.Project.three.dto.AnswerDTO;
import obrien.Project.three.dto.QuestionDTO;
import obrien.Project.three.entity.Answer;
import obrien.Project.three.entity.Question;
import obrien.Project.three.repository.QuestionRepository;
import obrien.Project.three.service.QuestionService;
import obrien.Project.three.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void createQuestion(QuestionDTO questionDTO) {
        Question question=new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setCreatedBy(SecurityUtils.findUsername().get());
        question.setCreatedDate(LocalDateTime.now());
        question.setLastModifiedBy(null);
        question.setLastModifiedDate(null);
        question.setDeleted(false);
        questionRepository.save(question);
    }

    @Override
    public Collection<Question> findAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(AnswerDTO answerDTO) {
        Question question = this.questionRepository
                .findById(Integer.valueOf(answerDTO.getQuestionId()))
                .orElseThrow( ()->new IllegalStateException("Question not found"));
        Answer answer=new Answer();
        answer.setAnswer(answerDTO.getAnswer());
        answer.setCreatedBy(SecurityUtils.findUsername().get());
        answer.setCreatedDate(LocalDateTime.now());
        question.addAnswer(answer);
        return Optional.ofNullable(this.questionRepository.save(question));
    }
}
