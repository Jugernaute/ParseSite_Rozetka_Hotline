package compare.site.controllers.security;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.entity.*;
import compare.site.dto.productSite.CopySiteProductDto;
import compare.site.service.dtoProductSite.DtoCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class SecurityController {

    @Autowired
    DtoCreatorService dtoCreatorService;


    @GetMapping("/")
    private String start(Model model){
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
//        webClient.waitForBackgroundJavaScript(2000);
        webClient.getOptions().setCssEnabled(false);

        System.out.println(System.getenv());







 /*
 * Response to admin.html which browser client want to use;
 * This info need for next (@WebClientSettings)
 * */
        EnumBrowserVersion [] values = EnumBrowserVersion.values();
        List<String> list = new ArrayList<>();
        for (EnumBrowserVersion value : values) {
           list.add(value.toString());
        }
/*
* This map response on Admin.html info about number of each product in base
* and last date of update in base when product was uploaded for each product
* */
        Map<String, CopySiteProductDto> map = dtoCreatorService.adminInfoAboutAllProducts();

        model.addAttribute("enumWeb", list);
        model.addAttribute("map", map);
        return "admin";
    }

    @PostMapping("/ok")
    private String loginOk (){
        return "admin";
    }


    @GetMapping("/login-error")
    private String loginError (){
        return "loginError";
    }

}
