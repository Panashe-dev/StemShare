package obrien.Project.three.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScienceVideosController {

    @GetMapping("/experiments")
    public String initForm(){
        return "ResourceHub/VideosDetails";
    }
}
