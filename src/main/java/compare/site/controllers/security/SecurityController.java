package compare.site.controllers.security;

import compare.site.dto.DtoCreator;
import compare.site.entity.*;
import compare.site.entity.hotline.TabletsHotline;
import compare.site.entity.hotline.TelephonesHotline;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.CopySiteProductDto;
import compare.site.service.GeneralService;
import compare.site.service.dateUpdate.DateOfUpdateService;
import compare.site.service.dtoCreator.DtoCreatorService;
import compare.site.service.hotline.tablets.TabletsHotlineService;
import compare.site.service.hotline.telephones.TelephonesHotlineService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

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
    @Autowired
    DateOfUpdateService dateOfUpdateService;
    @Autowired
    DtoCreator dtoCreator;
    @Autowired
    GeneralService<? super ProductAbstract> generalService;
    @Autowired
    DtoCreatorService dtoCreatorService;


    @GetMapping("/")
    private String start(Model model){

        EnumBrowserVersion [] values = EnumBrowserVersion.values();
        List<String> list = new ArrayList<>();
        for (EnumBrowserVersion value : values) {
           list.add(value.toString());
        }

//        List listTabletsRozetka = generalService.findAllProducts(TabletsRozetka.class);
//        List listTelephonesRozetka = generalService.findAllProducts(TelephonesRozetka.class);
//        List listTelephonesHotline = generalService.findAllProducts(TelephonesHotline.class);
//        List listTabletsHotline = generalService.findAllProducts(TabletsHotline.class);
//
//        Map<String, CopySiteProductDto> map = new HashMap<>();
//        for (EnumSite site : EnumSite.values()){
//            for (EnumProducts products : EnumProducts.values()){
//                switch (site){
//                    case ROZETKA:
//                        if (products == EnumProducts.TELEPHONES){
//                            dtoCreator.createDto(site.name(),products.name());
//                            if(listTelephonesRozetka!=null){
//                                dtoCreator.setSize(listTelephonesRozetka.size());
//                            } else
//                                {
//                                    dtoCreator.setSize(0);
//                            }
//                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
//                            map.put("rozetkaTelephones", copyClass);
//
//                        }else if (products == EnumProducts.TABLETS){
//                            dtoCreator.createDto(site.name(), products.name());
//                            if(listTabletsRozetka!=null){
//                                dtoCreator.setSize(listTabletsRozetka.size());
//                            } else{
//                                dtoCreator.setSize(0);
//                            }
//                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
//                            map.put("rozetkaTablets", copyClass);
//                        }
//                        break;
//                    case HOTLINE:
//                        if (products == EnumProducts.TELEPHONES){
//                            dtoCreator.createDto(site.name(), products.name());
//                            if(listTelephonesHotline!=null){
//                                dtoCreator.setSize(listTelephonesHotline.size());
//                            } else {
//                                dtoCreator.setSize(0);
//                            }
//                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
//                            map.put("hotlineTelephones", copyClass);
//                        }else if (products == EnumProducts.TABLETS){
//                            dtoCreator.createDto(site.name(), products.name());
//                            if(listTabletsHotline!=null){
//                                dtoCreator.setSize(listTabletsHotline.size());
//                            } else {
//                                dtoCreator.setSize(0);
//                            }
//                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
//                            map.put("hotlineTablets", copyClass);
//                        break;
//                }
//            }
//        }

//        ResponseInfoAboutDB infoAboutDB = new ResponseInfoAboutDB();
//        List<Class> classes = infoAboutDB.allEntries(TelephonesRozetka.class);
//        for (Class aClass : classes) {
//            System.out.println(aClass);
//        }
        Map<String, CopySiteProductDto> map = dtoCreatorService.adminInfoAboutAllProducts();


        model.addAttribute("enumWeb", list);
        model.addAttribute("map", map);
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
