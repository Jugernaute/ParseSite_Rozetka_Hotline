package compare.site.controllers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dao.GeneralDao;
import compare.site.entity.EnumProducts;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
//import compare.site.entity.Telephones;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.methods.SaveProduct;
import compare.site.service.GeneralService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RestMainController {
    @Autowired
    private TelephoneRozetkaService telephoneService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;

    @Autowired
    private SaveProduct saveProduct;
    @Autowired
    private GeneralService<? super ProductAbstract> generalService;


    @GetMapping("/main/loadTelephones")
    private Map<Integer, List<? super ProductAbstract>> load (@RequestParam int num) throws IOException {


        Page<? super ProductAbstract> telephones = generalService.findAllPageUsingPageable(0, num);

        Map<Integer,List<? super ProductAbstract>> listMap = new HashMap<>();
        listMap.put(telephones.getTotalPages()-1,telephones.getContent());
        return listMap;
        }
//        File file = new File("C:\\Users\\User\\HACKING\\lEARNING\\Parsing\\parsing_example_1\\src\\main\\resources\\static\\temp.txt");
//        FileWriter fileWriter = new FileWriter("C:\\Users\\User\\HACKING\\lEARNING\\Parsing\\parsing_example_1\\src\\main\\resources\\static\\temp.txt");
//        fileWriter.write(page.asXml());
//        fileWriter.flush();
//        fileWriter.close();
//        return telephonesSet;


    @GetMapping("/main/pagination/selectItems")
    private Map<Integer, List<? super ProductAbstract>> selectItems (@RequestParam int num,
                                                                     @RequestParam String search,
                                                                     @RequestParam String product){
        Map<Integer,List<? super ProductAbstract>> listMap = new HashMap<>();
//        List<TelephonesRozetka> allProducts = generalService.findAllProducts(TelephonesRozetka.class);

        if (product.equalsIgnoreCase(EnumProducts.TELEPHONES.toString())){
            if (!search.isEmpty()){
                Page<? super ProductAbstract> telephones = generalService.findAllByModelContains(search, PageRequest.of(0,num));
                listMap.put(telephones.getTotalPages(), telephones.getContent());
                return listMap;
            }
            Page<? super ProductAbstract> telephones = generalService.findAllPageUsingPageable(0, num);
            listMap.put(telephones.getTotalPages(), telephones.getContent());
            return listMap;
        }else
            if (product.equalsIgnoreCase(EnumProducts.TABLETS.toString())){
            if (!search.isEmpty()){
                Page<? super ProductAbstract> tablets = generalService.findAllByModelContains(search, PageRequest.of(0,num));
                listMap.put(tablets.getTotalPages(), tablets.getContent());
                return listMap;
            }
            Page<? super ProductAbstract> tablets = generalService.findAllPageUsingPageable(0, num);
            listMap.put(tablets.getTotalPages(), tablets.getContent());
            return listMap;
        }
        return listMap;
    }

//    @GetMapping("/main/search")
//    private Map<String, List<Telephones>> search(@RequestParam int num,
//                                                  @RequestParam String search){
//
//        Page<Telephones> telephones = telephoneService.findAllByModelContains(search, PageRequest.of(0,num));
//        Map<String,List<Telephones>> listMap = new HashMap<>();
////        System.out.println(telephones.getTotalElements());
//        String s = Integer.toString(telephones.getTotalPages())+"."+Long.toString(telephones.getTotalElements());
//        listMap.put(s, telephones.getContent());
//        return listMap;
//    }


    @GetMapping("main/loadTablets")
    public Map<Integer,List<TabletsRozetka>> loadTablets (/*@RequestParam int currentPage,*/
                                                   @RequestParam int num,
                                                   @RequestParam String search,
                                                   @RequestParam String product) throws IOException {

        Map<Integer,List<TabletsRozetka>> listMap = new HashMap<>();
        if (!search.isEmpty()){
            Page<TabletsRozetka> tablets = tabletRozetkaService.findAllByModelContains(search, PageRequest.of(0, num));
            listMap.put(tablets.getTotalPages(), tablets.getContent());
            return listMap;
        }
        Page<TabletsRozetka> tablets = tabletRozetkaService.findAllPageUsingPageable(0, num);
        listMap.put(tablets.getTotalPages(), tablets.getContent());
        return listMap;
    }


}
