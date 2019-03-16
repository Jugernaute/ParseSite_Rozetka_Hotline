package compare.site.controllers.security;

import compare.site.entity.EnumBrowserVersion;
import compare.site.entity.hotline.TelephonesHotline;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.hotline.tablets.TabletsHotlineService;
import compare.site.service.hotline.telephones.TelephonesHotlineService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SecurityController {
    @Autowired
    TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    TabletRozetkaService tabletRozetkaService;
    @Autowired
    TelephonesHotlineService telephonesHotlineService;
    @Autowired
    TabletsHotlineService tabletsHotlineService;

    @GetMapping("/")
    private String start(Model model){
        EnumBrowserVersion [] values = EnumBrowserVersion.values();
        List<String> list = new ArrayList<>();
        for (EnumBrowserVersion value : values) {
           list.add(value.toString());
        }

        int size = telephoneRozetkaService.findAllTelephones().size();
        int size1 = tabletRozetkaService.findAllTablets().size();
        int size2 = telephonesHotlineService.findAllTelephones().size();
        int size3 = tabletsHotlineService.findAllTablets().size();

        model.addAttribute("enumWeb", list);
        model.addAttribute("rozTel", size);
        model.addAttribute("rozTabl", size1);
        model.addAttribute("hotTel", size2);
        model.addAttribute("hotTabl", size3);
        return "admin";
    }

    @PostMapping("/ok")
    private String loginOk (){
        return "admin";
    }

//    @GetMapping("/login")
//    private String login (){
//        return "login";
;//    }

    @GetMapping("/login-error")
    private String loginError (){
        return "loginError";
    }

}
