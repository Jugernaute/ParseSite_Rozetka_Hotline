package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dto.DtoCreator;
import compare.site.entity.DateOfUpdate;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.service.dateUpdate.DateOfUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TabletsRozetkaLoad extends LoadProductAbstract {
    @Autowired
    public DateOfUpdateService dateOfUpdateService;
    @Override
    public ResponseLoad load(DtoCreator siteProductDto, WebClient webClient) {
        int numPages = 2;

        try {
            /*
             * how much pages are with telephs
             * */
//            paginator-catalog-l-i-active hidden
            HtmlPage pageHome = webClient.getPage("https://rozetka.com.ua/tablets/c130309/filter/");
            List<HtmlSpan> listCountOfPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");
            int countOfPages = Integer.parseInt(listCountOfPages.get(listCountOfPages.size()-1).asText());

//            System.out.println(countOfPages);
                     for (int j = 1; j <= 2; j++) {
                     String http = "https://rozetka.com.ua/tablets/c130309/filter/page=" + String.valueOf(j);

                         Map<Long, List<? super ProductAbstract>> longListMap = saveProduct.saveProduct(webClient, http, TabletsRozetka.class);
                         sizeOfProductInDb = GetNumberConcreteProductInDb.getNumber(longListMap);
                     }
                    webClient.close();
                    DateOfUpdate dateOfUpdateObj = new DateOfUpdate(siteProductDto.getSite(), siteProductDto.getProduct());
                    dateUpdateStr = saveOrUpdateDate.execute(dateOfUpdateObj);
                } catch (Exception e) {
                    System.out.println("++++++++++++++++" + e.getMessage());
                }
        return new ResponseLoad(sizeOfProductInDb, dateUpdateStr);
    }
}
