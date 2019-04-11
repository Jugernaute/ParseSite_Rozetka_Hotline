package compare.site.service.hotline.tablets;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.hotline.TabletsHotline;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TabletsHotlineService {
    ResponseLoadForFactory saveToBase (ProductSite productSite, WebClient webClient);
    List<TabletsHotline> findAllTablets();
    Page<TabletsHotline> findAllPageUsingPageable(int page, int size);
    Page<TabletsHotline> findAllByModelContains(String s, Pageable pageable);
    void deleteAllTablets();
}
