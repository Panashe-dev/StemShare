package obrien.Project.three.controller;

import obrien.Project.three.service.UserService;
import obrien.Project.three.utils.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView initForm(Map<String,Object> model){
        model.put("user", userService.findByUsername(SecurityUtils.findUsername().get()));
        ModelAndView mv=new ModelAndView("Account/Profile");
        return mv;
    }
}
