package compare.site.service.mobilluck.telephones;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import compare.site.dao.mobilluck.TelephonesMobilluckDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.mobilluck.TelephonesMobilluck;
import compare.site.service.ResponseUploadForFactory;
import compare.site.service.UploadProductAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.List;

@Service
@Transactional
public class TelephonesMobilluckServImpl extends UploadProductAbstract implements TelephonesMobilluckService {
    @Autowired
    TelephonesMobilluckDao telephonesMobilluckDao;

    /**
     * Creates connection to site and find page with product
     * than call methods which save this product
     * and date of upload to DB.
     * Calculate how much products are upload to DB
     * @return how much products are upload to DB and date of upload
     * */
    @Override
    public ResponseUploadForFactory saveToBase(ProductSite productSite, WebClient webClient) {
        /*clear all telephones from DB*/
        deleteAllTelephones();
        try {
            /**
             * connection to page with tablets
             * @exception java.io.IOException if an IO problem occurs
             * @exception MalformedURLException if an error occurred when creating a URL object
             * */
            HtmlPage page = webClient.getPage("https://www.mobilluck.com.ua/katalog/mobila/");

            /* find domElements which are responsible for how much pages are with tablets */
            List<HtmlAnchor> listPages = page.getByXPath("//a[@class='a-text']");
            /**
             * last index of {@link listPages} display max number of page
             */
            String numPages = listPages.get(listPages.size() - 1).asText();
            /**
             *Collection of tablets from each page and call method saving
             * for simplified  version here {<code>j=3</code>}
             * */
            for (int j = 1; j <= 3; j++) {
                /**
                 * url site where page = {<code>j</code>}
                 * */
                String http = "https://www.mobilluck.com.ua/katalog/mobila/pages_"+j+"_15.html";
                /**
                 * save all product which are on this page {<code>j</code>}
                 * */
                saveProduct(productSite, webClient, http);
            }
            webClient.close();
        } catch (Exception e) {
            System.out.println("error " + e.fillInStackTrace());
        }
        /* clear the counter from LoadProductAbstract.class */
        nums=0;
        return dateOfUpdateService.responseUploadForFactory();
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
