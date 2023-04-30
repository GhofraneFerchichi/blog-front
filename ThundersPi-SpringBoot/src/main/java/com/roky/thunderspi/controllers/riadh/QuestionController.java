package com.roky.thunderspi.controllers.riadh;


import com.roky.thunderspi.entities.Question;
import com.roky.thunderspi.services.riadh.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz/qst")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @GetMapping
    public Set<Question> getAll()
    {
        return questionService.findAll();
    }
    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id)
    {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{id}/question")
    public Set<Question> getQuestionByQuizId(@PathVariable Long quizId)
    {
        return null;
    }

    @PostMapping
    public Question addQuestion(@RequestParam Question q)
    {
        return questionService.addQuestion(q);
    }

    @PutMapping
    public Question updateQuestion(@RequestParam Question q)
    {
        return questionService.updateQuestion(q);
    }
    @DeleteMapping("/del{id}")
    public void deleteQuestion(@PathVariable Long id)
    {
        questionService.deleteQuestion(id);
    }
}
