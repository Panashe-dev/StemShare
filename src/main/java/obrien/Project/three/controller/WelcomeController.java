package obrien.Project.three.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@Controller
public class WelcomeController {

    @GetMapping("/dashboard")
    public ModelAndView Login(){
        ModelAndView mv=new ModelAndView("Dashboard/Welcome");
        return  mv;
    }
}
