package compare.site.service.mobilluck.telephones;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import compare.site.dao.mobilluck.TelephonesMobilluckDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.ProductAbstract;
import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.mobilluck.TelephonesMobilluck;
import compare.site.service.LoadProductAbstract;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelephonesMobilluckServImpl extends LoadProductAbstract implements TelephonesMobilluckService {
    @Autowired
    TelephonesMobilluckDao telephonesMobilluckDao;


    @Override
    public ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient) {
        deleteAllTelephones();
        try {
            /*
             * how much pages are with telephs
             * */
            HtmlPage page = webClient.getPage("https://www.mobilluck.com.ua/katalog/mobila/");
            List<HtmlAnchor> listPages = page.getByXPath("//a[@class='a-text']");
            /*
             * count of pages with tablets
             * */
            String numPages = listPages.get(listPages.size() - 1).asText();


            for (int j = 1; j <= 3; j++) {
                String http = "https://www.mobilluck.com.ua/katalog/mobila/pages_"+j+"_15.html";
                saveProduct(productSite, webClient, http);
            }
            webClient.close();
        } catch (Exception e) {
            System.out.println("error " + e.fillInStackTrace());
        }
        DateOfUpdate dateOfUpdateObj = new DateOfUpdate(productSite.getSite(), productSite.getProduct());
        dateUpdateStr = dateOfUpdateService.saveOrUpdateDateOfLoadSiteProduct(dateOfUpdateObj);
        Long getNums = numberConcreteProductFromBase.getNum();
        /*
         * clear the counter from LoadProductAbstract.class
         * */
        nums=0;
//        System.out.println(getNums);
//        System.out.println(dateUpdateStr);
        return new ResponseLoadForFactory(String.valueOf(getNums), dateUpdateStr);
    }

    @Override
    public List<TelephonesMobilluck> findAllTelephones() {
        return telephonesMobilluckDao.findAll();
    }

    @Override
    public void deleteAllTelephones() {
        telephonesMobilluckDao.deleteAll();
    }

    @Override
    public Page<TelephonesMobilluck> findAllPageUsingPageable(int page, int size) {
        return telephonesMobilluckDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TelephonesMobilluck> findAllByModelContains(String s, Pageable pageable) {
        return telephonesMobilluckDao.findAllByModelContains(s, pageable);
    }
}
