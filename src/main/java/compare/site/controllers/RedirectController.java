package compare.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/mainPage")
    private String mainPage(){
        return "main";
    }

    @GetMapping("/adminPage")
    private String adminPage(){
        return "redirect:/";
    }
}
