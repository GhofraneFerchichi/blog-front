package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Quiz;
import com.roky.thunderspi.repositories.riadh.QuizRepository;
import com.roky.thunderspi.repositories.riadh.UserQuizTakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IQuizServiceImp implements IQuizService {


    private final QuizRepository quizRepository;

    private final UserQuizTakeRepository userQuizTakeRepository;
    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public Set<Quiz> getAllQuiz() {
        return quizRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public Quiz addQuiz(Quiz q) {
        return quizRepository.save(q);
    }

    @Override
    public Quiz editQuiz(Quiz q) {
        return quizRepository.save(q);
    }

    @Override
    public Set<Quiz> getQuizByTeacherId(Long id) {
        return quizRepository.findQuizByTeacher(id);
    }

    @Override
    public Set<Quiz> getQuizByNumberOfTakes() {
        return null;
    }

    @Override
    public Set<Quiz> getQuizByCourse(Long id)
    {
        return quizRepository.findAll().stream().filter(q -> q.getCourse().getIdCourse().equals(id)).collect(Collectors.toSet());
    }


}
