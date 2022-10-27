package obrien.Project.three.controller;


import obrien.Project.three.dto.AnswerDTO;
import obrien.Project.three.dto.QuestionDTO;
import obrien.Project.three.entity.Question;
import obrien.Project.three.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ModelAttribute("ListQuestions")
    public Collection<Question> questionList(){
        return this.questionService.findAllQuestion();
    }

    @GetMapping("/forum")
    public  String initForm(Map<String,Object> model){
        model.put("question",new QuestionDTO());
        model.put("answer",new AnswerDTO());
        return "Message/Question";
    }

    @PostMapping("/forum/c")
    public String processCreatingQuestion(@ModelAttribute("question") @Valid  QuestionDTO questionDTO,
                                          BindingResult result, RedirectAttributes ra){
        if (result.hasErrors()){
            return "Message/Question";
        }else {
            this.questionService.createQuestion(questionDTO);
            return "redirect:/forum?ok";
        }

    }
    @PostMapping("/answer/a")
    public String processCreatingAnswer(@ModelAttribute("answer") @Valid AnswerDTO answerDTO,
                                          BindingResult result, RedirectAttributes ra){
        if (result.hasErrors()){
            return "Message/Question";
        }else {
          this.questionService.findById(answerDTO);
            return "redirect:/forum?ok";
        }

    }
}
