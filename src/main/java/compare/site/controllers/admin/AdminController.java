package compare.site.controllers.admin;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.DtoProductSite;
import compare.site.dto.productSite.ProductSite;
import compare.site.service.FactorySaveProducts;
import compare.site.service.ResponseLoadForFactory;
import compare.site.service.WebClientSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AdminController {

    @Autowired
    private DtoProductSite dtoProductSite;
    @Autowired
    private FactorySaveProducts factory;
    @Autowired
    private ProductSite productSite;

/*
*Its come from admin.js and using for load product in data base
* */
    @PostMapping("/admin/loadInDataBase")
    private Map<String,String> loadInDb (@RequestParam String site,
                                         @RequestParam String product,
                                         @RequestParam int waitForBackgroundJavaScript,
                                         @RequestParam boolean setThrowExceptionOnScriptError,
                                         @RequestParam boolean setJavaScriptEnabled,
                                         @RequestParam boolean setCssEnabled) {


/*
* create dto -> SiteProductDto.class which include
* @ String site;
* @ String product;
**/
        productSite.create(site, product);
        dtoProductSite.createDto(productSite);
        Map<String,String> stringMap = new HashMap<>();
/*
* Setting for WebClient (see WebClientSettings.class)
* default ->
* @setCssEnabled=false
* @setJavaScriptEnabled=false (if true ->  show price of products, but
*   time of load it is very long!)
* @setThrowExceptionOnScriptError=false
* @waitForBackgroundJavaScript=false (time for how many times wait for load javascript,
*   in our case - price of product)
*   */
        WebClient webClient = WebClientSettings.webClientSettings(setCssEnabled,setJavaScriptEnabled,setThrowExceptionOnScriptError,waitForBackgroundJavaScript*1000);

/*
* Depending of ProductSite.class we using FactorySaveProducts.class */
        ResponseLoadForFactory factory = this.factory.getFactory(productSite, webClient);
/*
* response on view
* @listSize - how many this products are in DB;
* @dateUpdate - date of update table with product in DB*/
        stringMap.put("listSize", factory.getSize());
        stringMap.put("dataUpdate", factory.getDateUpdate());
        return stringMap;
    }
}
