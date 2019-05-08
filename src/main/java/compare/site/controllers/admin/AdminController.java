package compare.site.controllers.admin;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.DtoProductSite;
import compare.site.dto.productSite.ProductSite;
import compare.site.service.FactorySaveProducts;
import compare.site.service.ResponseUploadForFactory;
import compare.site.service.WebClientSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AdminController {

    @Autowired
    private DtoProductSite dtoProductSite;
    @Autowired
    private FactorySaveProducts factorySaveProducts;
    @Autowired
    private ProductSite productSite;

/**
*Its come from admin.js and using for upload product in data base
 * next params choosing by user:
 * @param site - choose site,
 * @param product - choose product,
 * @param waitForBackgroundJavaScript - {@link WebClientSettings}
 * @param setThrowExceptionOnScriptError - {@link WebClientSettings}
 * @param setJavaScriptEnabled  - {@link WebClientSettings}
 * @param setCssEnabled - {@link WebClientSettings}
 */
    @PostMapping("/admin/uploadInDataBase")
    private Map<String,String> uploadInDB (@RequestParam String site,
                                           @RequestParam String product,
                                           @RequestParam int waitForBackgroundJavaScript,
                                           @RequestParam boolean setThrowExceptionOnScriptError,
                                           @RequestParam boolean setJavaScriptEnabled,
                                           @RequestParam boolean setCssEnabled) {


/**
 * create {@link ProductSite}
 * Its creating need for a simplified use of two values {@param site} & {@param product}
 * with one object
 */
        productSite.create(site, product);
        dtoProductSite.createDto(productSite);

/*create this map is need for response on view*/
        Map<String,String> stringMap = new HashMap<>();

/**
 * create {@link WebClient}
 * */
        WebClient webClient = WebClientSettings.webClientSettings(setCssEnabled,setJavaScriptEnabled,setThrowExceptionOnScriptError,waitForBackgroundJavaScript*1000);

/**
 * This method save in DB product using pattern Factory
 * {@link FactorySaveProducts}
 */
        ResponseUploadForFactory factory = this.factorySaveProducts.getFactory(productSite, webClient);
/*
* response on view
* @listSize - how many this products are in DB;
* @dateUpdate - date of update table with product in DB*/
        stringMap.put("listSize", factory.getSize());
        stringMap.put("dataUpdate", factory.getDateUpdate());

        return stringMap;
    }
}
