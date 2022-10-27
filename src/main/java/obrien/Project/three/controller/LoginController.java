package obrien.Project.three.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dev Panashe Obrien Mugomba
 * @author dev shalom chiduuro
 * @date  2/10/2022
 *
 *  */

@Controller
public class LoginController {

    @GetMapping("/login")
    public String LoginPage(){
        return "Auth/Login";
    }

}
