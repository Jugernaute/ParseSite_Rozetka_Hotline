package compare.site.controllers.admin;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.controllers.MethodsForController;
import compare.site.entity.*;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.methods.SaveProduct;
import compare.site.service.dateUpdate.DateOfUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class AdminController {

    @Autowired
    private SaveProduct saveProduct;
    @Autowired
    MethodsForController methodsForController;
    @Autowired
    private DateOfUpdateService dateService;


    @GetMapping("/admin/loadInDataBase")
    private Map<String,String> loadInDb (@RequestParam String site,
                                         @RequestParam String product,
                                         @RequestParam int waitForBackgroundJavaScript,
                                         @RequestParam boolean setThrowExceptionOnScriptError,
                                         @RequestParam boolean setJavaScriptEnabled,
                                         @RequestParam boolean setCssEnabled){

        String sizeOfProductInDb = "";
        String dateUpdate = "";
        Map<String,String> stringMap = new HashMap<>();

        if (site.equalsIgnoreCase(EnumSite.РОЗЕТКА.toString())){
            WebClient webClient = methodsForController.webClientSettings(setCssEnabled,setJavaScriptEnabled,setThrowExceptionOnScriptError,waitForBackgroundJavaScript*1000);
            if (product.equalsIgnoreCase(EnumProducts.ТЕЛЕФОНИ.toString())){
                try {
                    /*
                     * how much pages are with telephs
                     * */
                    HtmlPage pageHome = webClient.getPage("https://rozetka.com.ua/mobile-phones/c80003/preset=smartfon/");
                    List<HtmlSpan> listCountOfPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");
                    int countOfPages = Integer.parseInt(listCountOfPages.get(listCountOfPages.size()-1).asText());

//                    now using page
                    int numPages = 2;
                    for (int j = 1; j <= numPages; j++) {
                        String http = "https://rozetka.com.ua/mobile-phones/c80003/page=" + String.valueOf(j) + ";preset=smartfon/";
                        Map<Long, List<? super ProductAbstract>> longListMap = saveProduct.saveProduct(webClient, http, TelephonesRozetka.class);
                        sizeOfProductInDb = methodsForController.getSizeOfProductInDb(longListMap);
                    }

                    webClient.close();
                    DateOfUpdate dateOfUpdate = new DateOfUpdate(EnumSite.РОЗЕТКА, EnumProducts.ТЕЛЕФОНИ);
                    dateUpdate = dateOfUpdate.getDateTime();
                    System.out.println(dateUpdate);
//                    dateService.save(dateOfUpdate);
                    dateService.updateDateOfLoadProduct(dateUpdate, EnumSite.РОЗЕТКА.toString(), EnumSite.HOTLINE.toString());
                    SaveProduct.nums = 0;
                } catch (IOException e) {
                    /*include logger*/
                }
            }
            else if (product.equalsIgnoreCase(EnumProducts.ПЛАНШЕТИ.toString())){
                int numPages = 2;
                try {
                     for (int j = 1; j <= numPages; j++) {
                     String http = "https://rozetka.com.ua/tablets/c130309/filter/page=" + String.valueOf(j);

                        Map<Long, List<? super ProductAbstract>> longListMap = saveProduct.saveProduct(webClient, http, TabletsRozetka.class);
                        sizeOfProductInDb = methodsForController.getSizeOfProductInDb(longListMap);

                     }
                    webClient.close();
                    DateOfUpdate dateOfUpdate = new DateOfUpdate(EnumSite.РОЗЕТКА, EnumProducts.ПЛАНШЕТИ);
                    dateUpdate = dateOfUpdate.getDateTime();
                    dateService.save(dateOfUpdate);
                    SaveProduct.nums = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        stringMap.put("listSize", sizeOfProductInDb);
        stringMap.put("dataUpdate", dateUpdate);
        return stringMap;
    }


}
