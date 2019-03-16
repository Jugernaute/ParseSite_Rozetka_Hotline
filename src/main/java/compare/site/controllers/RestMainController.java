package compare.site.controllers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.entity.EnumProducts;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
//import compare.site.entity.Telephones;
import compare.site.methods.SaveProduct;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class RestMainController {
//    @Autowired
//    private TelephonesRozetka telephoneService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;

    @Autowired
    private SaveProduct saveProduct;


//    @GetMapping("/main/loadTelephones")
//    private Map<Integer, List<TelephonesRozetka>> load (@RequestParam int num) throws IOException {


//        Page<TelephonesRozetka> telephones = telephoneService.findAllPageUsingPageable(0, num);
//
//        Map<Integer,List<Telephones>> listMap = new HashMap<>();
//        listMap.put(telephones.getTotalPages()-1,telephones.getContent());
//        return listMap;
//        }
//        File file = new File("C:\\Users\\User\\HACKING\\lEARNING\\Parsing\\parsing_example_1\\src\\main\\resources\\static\\temp.txt");
//        FileWriter fileWriter = new FileWriter("C:\\Users\\User\\HACKING\\lEARNING\\Parsing\\parsing_example_1\\src\\main\\resources\\static\\temp.txt");
//        fileWriter.write(page.asXml());
//        fileWriter.flush();
//        fileWriter.close();
//        return telephonesSet;
//    }


//    @GetMapping("/main/loadProductsDB")
//    private List<? extends ProductAbstract> loadDB () {
//        return telephoneService.findAllTelephones();
//    }
//
//    @GetMapping("/main/pagination")
//    public Map<Integer, List<Telephones>> pagination (@RequestParam int currentPage,
//                                                       @RequestParam int num,
//                                                      @RequestParam String search){
//        Map<Integer,List<Telephones>> listMap = new HashMap<>();
//        if (!search.isEmpty()){
//            Page<Telephones> telephones = telephoneService.findAllByModelContains(search, PageRequest.of(currentPage-1,num));
//            listMap.put(telephones.getTotalPages(), telephones.getContent());
//            return listMap;
//        }
//        Page<Telephones> telephones = telephoneService.findAllPageUsingPageable(currentPage-1, num);
//        List<Telephones> content = telephones.getContent();
//        listMap.put(telephones.getTotalPages(), content);
//        return listMap;
//    }
//
    @GetMapping("/main/pagination/selectItems")
    private Map<Integer, List<? extends ProductAbstract>> selectItems (@RequestParam int num,
                                                        @RequestParam String search,
                                                        @RequestParam String product){
        Map<Integer,List<? extends ProductAbstract>> listMap = new HashMap<>();
//        if (product.equalsIgnoreCase(EnumProducts.ТЕЛЕФОНИ.toString())){
//            if (!search.isEmpty()){
//                Page<Telephones> telephones = telephoneService.findAllByModelContains(search, PageRequest.of(0,num));
//                listMap.put(telephones.getTotalPages(), telephones.getContent());
//                return listMap;
//            }
//            Page<Telephones> telephones = telephoneService.findAllPageUsingPageable(0, num);
//            listMap.put(telephones.getTotalPages(), telephones.getContent());
//            return listMap;
//        }else
            if (product.equalsIgnoreCase(EnumProducts.ПЛАНШЕТИ.toString())){
            if (!search.isEmpty()){
                Page<TabletsRozetka> tablets = tabletRozetkaService.findAllByModelContains(search, PageRequest.of(0,num));
                listMap.put(tablets.getTotalPages(), tablets.getContent());
                return listMap;
            }
            Page<TabletsRozetka> tablets = tabletRozetkaService.findAllPageUsingPageable(0, num);
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
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(3*1000);

        List<? super ProductAbstract> listTablets = new ArrayList<>();

        int numPages = 1;
        for (int j = 1; j <= numPages; j++) {
            String http = "https://rozetka.com.ua/tablets/c130309/filter/page="+String.valueOf(j);
            saveProduct.saveProduct(webClient, http, TabletsRozetka.class);
        }
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
