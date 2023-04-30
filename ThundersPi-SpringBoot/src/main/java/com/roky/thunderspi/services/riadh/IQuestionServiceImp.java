package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Question;
import com.roky.thunderspi.repositories.riadh.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IQuestionServiceImp implements IQuestionService {

    private final QuestionRepository questionRepository;
    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Set<Question> findAll() {
        return questionRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Question updateQuestion(Question q) {
        Question toUpdate= questionRepository.findById(q.getId()).orElse(null);
        if(toUpdate!= null)
        {
            return toUpdate;
        }else
            return null;
    }

    @Override
    public Question addQuestion(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public Set<Question> getQuestionByQuizId(Long quizId) {
        return null;
    }
}
