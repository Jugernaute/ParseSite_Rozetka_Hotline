package compare.site.service.rozetka.telephone;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dao.rozetka.TelephonesRozetkaDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.ResponseUploadForFactory;
import compare.site.service.UploadProductAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class TelephoneRozetkaServiceImpl extends UploadProductAbstract implements TelephoneRozetkaService{
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
            HtmlPage pageHome = webClient.getPage("https://rozetka.com.ua/mobile-phones/c80003/");

            /* find domElements which are responsible for how much pages are with tablets */
            List<HtmlSpan> listPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");
            /**
             * last index of {@link listPages} display max number of page
             */
            int countOfPages = Integer.parseInt(listPages.get(listPages.size()-1).asText());

            /**
             *Collection of tablets from each page and call method saving
             * for simplified  version here {<code>j=3</code>}
             * */
            for (int j = 1; j <= 3; j++) {
                /**
                 * url site where page = {<code>j</code>}
                 * */
                String http = "https://rozetka.com.ua/mobile-phones/c80003/page=" + String.valueOf(j) + ";preset=smartfon/";
                saveProduct(productSite, webClient, http);
            }
            webClient.close();


        } catch (Exception e) {
            System.out.println("error -> " + Arrays.toString(e.getStackTrace()));
        }
        /* clear the counter from LoadProductAbstract.class */
        nums=0;
        return dateOfUpdateService.responseUploadForFactory();
    }
}
