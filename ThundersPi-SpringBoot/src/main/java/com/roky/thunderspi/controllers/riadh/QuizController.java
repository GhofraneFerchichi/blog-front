package com.roky.thunderspi.controllers.riadh;


import com.roky.thunderspi.entities.Quiz;
import com.roky.thunderspi.services.riadh.IQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final IQuizService quizService;

    @GetMapping
    public Set<Quiz> getAllQuizzes()
    {
        return quizService.getAllQuiz();
    }

    @GetMapping("{/course/{id}")
    public Set<Quiz> getQuizByCourse(@PathVariable Long courseId)
    {
        return quizService.getQuizByCourse(courseId);
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id)
    {
        return quizService.getQuizById(id);
    }

    @PostMapping
    public Quiz addQuiz(@RequestParam Quiz q)
    {
        return quizService.addQuiz(q);
    }

    @PutMapping
    public Quiz updateQuiz(@RequestParam Quiz q)
    {
        return quizService.editQuiz(q);
    }

    @DeleteMapping("/del{id}")
    public void deleteQuiz(@PathVariable Long id)
    {
        quizService.deleteQuiz(id);
    }



}
