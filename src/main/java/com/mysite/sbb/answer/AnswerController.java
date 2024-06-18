package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
   //답변하기 위한 질문 가져옴
	private final QuestionService questionService;
	
   //답변 등록
   private final AnswerService answerService;
   
   @PostMapping("/create/{id}")
   public String createAnswer(Model model, @PathVariable("id") Integer id, 
        @Valid AnswerForm answerForm, BindingResult bindingResult) {
      Question question = this.questionService.getQuestion(id); //questionService의 id값 받아옴
      if(bindingResult.hasErrors()) {
    	  model.addAttribute("question",question );
    	  return "question_detail";
      }
      this.answerService.create(question, answerForm.getContent());
      return String.format("redirect:/question/detail/%s", id);
   }
   
   
}