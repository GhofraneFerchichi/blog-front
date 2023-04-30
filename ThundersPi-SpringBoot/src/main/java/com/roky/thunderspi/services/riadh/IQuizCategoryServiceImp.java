package com.roky.thunderspi.services.riadh;

import com.roky.thunderspi.entities.QuizCategory;
import com.roky.thunderspi.repositories.riadh.QuizCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class IQuizCategoryServiceImp implements IQuizCategoryService{

    //TODO to implement
    private final QuizCategoryRepository quizCategoryRepository;
    @Override
    public QuizCategory getQuizCategoryById(Long id) {
        return null;
    }

    @Override
    public Set<QuizCategory> findAll() {
        return null;
    }

    @Override
    public void deleteQuizCategory(Long id) {

    }

    @Override
    public QuizCategory updateQuestion(QuizCategory q) {
        return null;
    }

    @Override
    public QuizCategory addQuestion(QuizCategory q) {
        return null;
    }
}
