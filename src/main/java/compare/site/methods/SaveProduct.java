package compare.site.methods;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import compare.site.dao.GeneralDao;
import compare.site.entity.DateOfUpdate;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class SaveProduct {

//    @Autowired
//    private TelephoneRozetkaService telephoneService;
    @Autowired
    private GeneralDao<? super ProductAbstract> generalDao;
//    @Autowired
//    private TabletRozetkaService tabletRozetkaService;
//    @Autowired
//    private T productService;

    public static long nums = 0;

    public Map<Long, List<? super ProductAbstract>> saveProduct (WebClient webClient, String httpLink, /*int numPages,*/ Class<?> aClass) throws IOException {
        Map<Long,List<? super ProductAbstract>> productList = new HashMap<>();
        List<? super ProductAbstract> list = new ArrayList<>();
        long count = 0;
        HtmlPage page = webClient.getPage(httpLink);


//        for (int j = 1; j <= numPages; j++) {
//            String http = "https://rozetka.com.ua/tablets/c130309/filter/page="+String.valueOf(j);
////            List<? super ProductAbstract> list = saveProduct.saveProduct(webClient, http, Tablets.class);
//
//        }
        /*
         * main block with all info about telephone
         * */
        List<HtmlDivision> byXPath2 = page.getByXPath("//div[@class='g-i-tile-i-box-desc']");
        /*
         * lists
         * */
        List<HtmlAnchor> listLinks = page.getByXPath("//a[@class='responsive-img centering-child-img']");
        List<HtmlDivision> listPrice = page.getByXPath("//div[@class='g-price-uah']");
        for (HtmlDivision htmlDivision : byXPath2) {
            count++;
            List<HtmlDivision> listModel = htmlDivision.getByXPath("//div[@class='g-i-tile-i-title clearfix']");
            String description = htmlDivision.getLastElementChild().asText();

            if (aClass.equals(TelephonesRozetka.class)) {
                TelephonesRozetka telephones = new TelephonesRozetka();
                telephones.setModel(listModel.get(Math.toIntExact(count - 1)).asText());
                telephones.setDescript(description);
                telephones.setLinkOnSite(listLinks.get(Math.toIntExact(count - 1)).getHrefAttribute());
//                telephoneService.saveTelephone(telephones);
                generalDao.save(telephones);
//                    telephones.setPriceTelephone(Integer.parseInt(price));
                list.add(telephones);
            } else
                if (aClass.equals(TabletsRozetka.class)) {
                TabletsRozetka tablets = new TabletsRozetka();
                tablets.setModel(listModel.get(Math.toIntExact(count - 1)).asText());
                tablets.setDescript(description);
                tablets.setLinkOnSite(listLinks.get(Math.toIntExact(count - 1)).getHrefAttribute());
//                    tablets.setPriceTelephone(Integer.parseInt(price));
//                    tabletRozetkaService.saveTablet(tablets);
                    generalDao.save(tablets);
                list.add(tablets);
            }
        }
        nums+=count;
        productList.put(nums, list);
        return productList;
    }


}
