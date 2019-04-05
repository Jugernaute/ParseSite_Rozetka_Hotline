package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dto.DtoCreator;
import compare.site.entity.DateOfUpdate;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.methods.SaveProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TelephonesRozetkaLoad extends LoadProductAbstract {


    @Override
    public ResponseLoad load(DtoCreator siteProductDto, WebClient webClient) {
            telephoneRozetkaService.deleteAllTelephones();
            try {
                /*
                 * how much pages are with telephs
                 * */
                HtmlPage pageHome = webClient.getPage("https://rozetka.com.ua/mobile-phones/c80003/preset=smartfon/");
                List<HtmlSpan> listCountOfPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");
                int countOfPages = Integer.parseInt(listCountOfPages.get(listCountOfPages.size()-1).asText());

//                    now using page
                int numPages = 2;
                for (int j = 1; j <= 2; j++) {
                    String http = "https://rozetka.com.ua/mobile-phones/c80003/page=" + String.valueOf(j) + ";preset=smartfon/";
                    Map<Long, List<? super ProductAbstract>> longListMap = saveProduct.saveProduct(webClient, http, TelephonesRozetka.class);
                    sizeOfProductInDb = GetNumberConcreteProductInDb.getNumber(longListMap);
                }
                webClient.close();
                DateOfUpdate dateOfUpdate = new DateOfUpdate(siteProductDto.getSite(), siteProductDto.getProduct());
                dateUpdateStr = saveOrUpdateDate.execute(dateOfUpdate);
                SaveProduct.nums = 0;
            } catch (Exception e) {
                System.out.println("++++++++++++++++" + e.getMessage());
            }
        return new ResponseLoad(sizeOfProductInDb,dateUpdateStr);
    }
}
