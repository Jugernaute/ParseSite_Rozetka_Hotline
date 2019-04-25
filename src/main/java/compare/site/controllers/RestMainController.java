package compare.site.controllers;

import compare.site.dto.searchProduct.DtoSearchObject;
import compare.site.service.ResponseProductMainPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RestMainController {
    @Autowired
    private ResponseProductMainPage responseProductMainPage;



//        File file = new File("C:\\Users\\User\\HACKING\\lEARNING\\Parsing\\parsing_example_1\\src\\main\\resources\\static\\temp.txt");
//        FileWriter fileWriter = new FileWriter("C:\\Users\\User\\HACKING\\lEARNING\\Parsing\\parsing_example_1\\src\\main\\resources\\static\\temp.txt");
//        fileWriter.write(page.asXml());
//        fileWriter.flush();
//        fileWriter.close();
//        return telephonesSet;


    @PostMapping("/main/selectItems")
    private Map selectItems (@RequestBody DtoSearchObject dtoSearchObject){
        return responseProductMainPage.response(dtoSearchObject);
    }

    @PostMapping("/main/page")
    private Map selectPage (@RequestBody DtoSearchObject dtoSearchObject){
        return responseProductMainPage.response(dtoSearchObject);
    }

    @PostMapping("/main/search")
    private Map search(@RequestBody DtoSearchObject dtoSearchObject){
        return responseProductMainPage.response(dtoSearchObject);
    }


    @PostMapping("main/loadProducts")
    public Map loadTablets (@RequestBody DtoSearchObject dtoSearchObject) {
        return responseProductMainPage.response(dtoSearchObject);
    }


}
