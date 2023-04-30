package com.roky.thunderspi.controllers.riadh;

//TODO Configure QuestionAnswerController

import com.roky.thunderspi.entities.QuestionAnswer;
import com.roky.thunderspi.services.riadh.IQuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz/qstAns")
@RequiredArgsConstructor
public class QuestionAnswerController {

private final IQuestionAnswerService questionAnswerServiceImp;

@GetMapping("/{id}")
    public QuestionAnswer getQuestionAnswer(@PathVariable Long id)
{
    return questionAnswerServiceImp.findbyId(id);
}

/* TODO postMapping */
@PostMapping
    public QuestionAnswer addQuestionAnswer(@RequestParam QuestionAnswer q)
{
    return questionAnswerServiceImp.addQuestionAnswer(q);
}

@PutMapping
    public QuestionAnswer editQuestionAnswer(@RequestParam QuestionAnswer q)
{
    return questionAnswerServiceImp.updateQuestionAnswer(q);
}


@DeleteMapping
    public void deleteQuestionAnswer(@RequestParam QuestionAnswer q)
{
    questionAnswerServiceImp.deleteQuestionAnswer(q.getId());
}
@GetMapping
    public Set<QuestionAnswer> getAllQuestionAnswer()
{
    return questionAnswerServiceImp.findAll();
}




}
