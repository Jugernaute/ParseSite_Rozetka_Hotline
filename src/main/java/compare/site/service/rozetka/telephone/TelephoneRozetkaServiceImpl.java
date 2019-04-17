package compare.site.service.rozetka.telephone;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dao.rozetka.TelephonesRozetkaDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.GetNumberConcreteProductFromBase;
import compare.site.service.LoadProductAbstract;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TelephoneRozetkaServiceImpl extends LoadProductAbstract implements TelephoneRozetkaService{
    @Autowired
    TelephonesRozetkaDao telephonesDao;

    @Override
    public void deleteAllTelephones() {
        telephonesDao.deleteAll();
    }

    @Override
    public Page<TelephonesRozetka> findAllPageUsingPageable(int page, int size) {
        return telephonesDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TelephonesRozetka> findAllByModelContains(String s, Pageable pageable) {
        return telephonesDao.findAllByModelContains(s, pageable);
    }

    @Override
    public ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient) {
        deleteAllTelephones();
        try {
            /*
             * how much pages are with telephs
             * */
            HtmlPage pageHome = webClient.getPage("https://www.mobilluck.com.ua/katalog/mobila/");
            List<HtmlSpan> listCountOfPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");
            int countOfPages = Integer.parseInt(listCountOfPages.get(listCountOfPages.size()-1).asText());

//                    now using page
            int numPages = 2;
            for (int j = 1; j <= 2; j++) {
                String http = "https://rozetka.com.ua/mobile-phones/c80003/page=" + String.valueOf(j) + ";preset=smartfon/";
                saveProduct(productSite, webClient, http);
            }
            webClient.close();
            DateOfUpdate dateOfUpdate = new DateOfUpdate(productSite.getSite(), productSite.getProduct());
            dateUpdateStr = dateOfUpdateService.saveOrUpdateDateOfLoadSiteProduct(dateOfUpdate);

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
}
