package compare.site.service.rozetka.tablet;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dao.rozetka.TabletsRozetkaDao;
import compare.site.dto.productSite.DtoProductSite;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.service.GetNumberConcreteProductFromBase;
import compare.site.service.LoadProductAbstract;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TabletRozetkaServiceImpl
        extends LoadProductAbstract
        implements TabletRozetkaService {

    @Autowired
    private TabletsRozetkaDao tabletsDao;

    public Page<TabletsRozetka> findAllPageUsingPageable(int page, int size) {
        return tabletsDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TabletsRozetka> findAllByModelContains(String s, Pageable pageable) {
        return tabletsDao.findAllByModelContains(s, pageable);
    }

    @Override
    public ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient) {
        deleteAllTablets();
        try {
            /*
             * how much pages are with telephs
             * */
//            paginator-catalog-l-i-active hidden
            HtmlPage pageHome = webClient.getPage("https://rozetka.com.ua/tablets/c130309/filter/");
            List<HtmlSpan> listCountOfPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");
            int countOfPages = Integer.parseInt(listCountOfPages.get(listCountOfPages.size()-1).asText());

            for (int j = 1; j <= 2; j++) {
                String http = "https://rozetka.com.ua/tablets/c130309/filter/page=" + String.valueOf(j);

                /*Map<Long, List<? super ProductAbstract>> longListMap = */
                saveProduct(productSite, webClient, http);
//                sizeOfProductInDb = GetNumberConcreteProductFromBase.getNumber(longListMap);
            }
            webClient.close();
            DateOfUpdate dateOfUpdateObj = new DateOfUpdate(productSite.getSite(), productSite.getProduct());
            dateUpdateStr = dateOfUpdateService.saveOrUpdateDateOfLoadSiteProduct(dateOfUpdateObj);
        } catch (Exception e) {
            System.out.println("++++++++++++++++" + e.getMessage());
        }
        Long getNums = numberConcreteProductFromBase.getNum();
        /*
         * clear the counter from LoadProductAbstract.class
         * */
        nums=0;
        return new ResponseLoadForFactory(String.valueOf(getNums), dateUpdateStr);
    }

    @Override
    public void deleteAllTablets() {
        tabletsDao.deleteAll();
    }
}
