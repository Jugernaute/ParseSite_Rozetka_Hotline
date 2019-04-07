package compare.site.controllers;

import compare.site.dto.productSite.ProductSite;
import compare.site.dto.searchProductPage.DtoSearchObject;
import compare.site.entity.EnumProducts;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.methods.SaveProduct;
import compare.site.service.ResponseProductMainPage;
import compare.site.service.general.GeneralService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class RestMainController {
    @Autowired
    private TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;
    @Autowired
    private ResponseProductMainPage responseProductMainPage;
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


    @PostMapping("/main/pagination/selectItems")
    private Map selectItems (@RequestBody DtoSearchObject dtoSearchObject){
        return responseProductMainPage.response(dtoSearchObject);
    }

    @PostMapping("/main/pagination/page")
    private Map selectPage (@RequestBody DtoSearchObject dtoSearchObject){
        return responseProductMainPage.response(dtoSearchObject);
    }

    @GetMapping("/main/search")
    private Map<String, List<TabletsRozetka>> search(@RequestParam int num,
                                                     @RequestParam String search){

        Page<TabletsRozetka> telephones = tabletRozetkaService.findAllByModelContains(search, PageRequest.of(0,num));
        Map<String,List<TabletsRozetka>> listMap = new HashMap<>();
        String s = Integer.toString(telephones.getTotalPages())+"."+Long.toString(telephones.getTotalElements());
        listMap.put(s, telephones.getContent());
        return listMap;
    }


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
