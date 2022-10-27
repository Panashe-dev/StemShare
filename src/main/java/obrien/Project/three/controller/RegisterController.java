package obrien.Project.three.controller;

import obrien.Project.three.dto.StudentDTO;
import obrien.Project.three.dto.TutorDTO;
import obrien.Project.three.service.UserService;
import obrien.Project.three.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Panashe Obrien Mugomba
 * @author dev shalom chiduuro
 * @date  2/10/2022
 *
 *  */

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/student-register")
    public ModelAndView initForm(Map<String,Object> map){
        var student=new StudentDTO();
        map.put("student",student);
        ModelAndView mv=new ModelAndView("Register/Student");
        return mv;
    }

    @PostMapping("/student-register")
    public String processCreationForm(@ModelAttribute("student") @Valid StudentDTO studentDTO,  BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("error","error: some fields are empty.");
            return "Register/Student";
        }else {
            userService.createUserStudent(studentDTO);
            return "redirect:/student-register?success";
        }
    }

    @GetMapping("/tutor-register")
    public ModelAndView createStaff(Map<String,Object> model){
        var tutor=new TutorDTO();
        model.put("tutor",tutor);
        ModelAndView mv=new ModelAndView("Register/Staff");
        return mv;
    }
    @PostMapping("/tutor-register")
    public String processCreationTutorForm(@ModelAttribute("tutor") @Valid TutorDTO tutorDTO, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("error","error: some fields are empty.");
            return "Register/Staff";
        }else {
            this.userService.createUserTutor(tutorDTO);
            return "redirect:/tutor-register?success";
        }
    }

    @ModelAttribute("LevelList")
    public List<String> LevelList(){
     return  Arrays.asList(Constants.level);
    }

    @ModelAttribute("SubjectList")
    public List<String> SubjectList(){
        return  Arrays.asList(Constants.subjects);
    }

}
