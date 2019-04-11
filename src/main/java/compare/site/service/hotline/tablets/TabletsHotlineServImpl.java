package compare.site.service.hotline.tablets;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dao.hotline.TabletsHotlineDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.hotline.TabletsHotline;
import compare.site.service.LoadProductAbstract;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class TabletsHotlineServImpl extends LoadProductAbstract implements TabletsHotlineService {
    @Autowired
    TabletsHotlineDao hotlineDao;

//    @Override
//    public void saveTablets(TabletsHotline telephones) {
//    hotlineDao.save(telephones);
//    }

    @Override
    public List<TabletsHotline> findAllTablets() {
        return hotlineDao.findAll();
    }

    @Override
    public Page<TabletsHotline> findAllPageUsingPageable(int page, int size) {
        return hotlineDao.findAll(PageRequest.of(page,size));
    }

    @Override
    public Page<TabletsHotline> findAllByModelContains(String s, Pageable pageable) {
        return hotlineDao.findAllByModelContains(s, pageable);
    }

    @Override
    public ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient)  {
        deleteAllTablets();
        try {
        /*
         * how much pages are with telephs
         * */
        HtmlPage page = webClient.getPage("https://hotline.ua/computer/planshety/");
        List<HtmlAnchor> listCountOfPages = page.getByXPath("//a[@class='pages']");
            /*
             * count of pages with tablets
             * */
            int countOfPages = Integer.parseInt(listCountOfPages.get(listCountOfPages.size()-1).asText());

            for (int j = 1; j <= 2; j++) {
                String http = "https://hotline.ua/computer/planshety/?p=" + String.valueOf(j);
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
        return new ResponseLoadForFactory(String.valueOf(getNums), dateUpdateStr);
    }

    @Override
    public void deleteAllTablets() {
        hotlineDao.deleteAll();
    }
}

