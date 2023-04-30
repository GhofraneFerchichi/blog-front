package com.roky.thunderspi.controllers.riadh;
//TODO Configure UserQuestionAnswerController

import com.roky.thunderspi.entities.UserQuestionAnswer;
import com.roky.thunderspi.services.riadh.IUserQuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserQuestionAnswerController {

    private final IUserQuestionAnswerService userQuestionAnswerService;

    /* TODO Get All
    Get by ID
    Post
    Put
    Delete */

    @GetMapping
    public Set<UserQuestionAnswer> getAllUserAnswers()
    {
        return userQuestionAnswerService.findAll();
    }
    @PostMapping
    public UserQuestionAnswer addUserAnswer(UserQuestionAnswer u)
    {
       return userQuestionAnswerService.addQuestionAnswer(u);
    }

    @GetMapping("/question/{id}")
    public Set<UserQuestionAnswer> getAnswersByQuestion(@PathVariable Long questionid){
        return userQuestionAnswerService.findAllbyQuestion(questionid);
    }

}
