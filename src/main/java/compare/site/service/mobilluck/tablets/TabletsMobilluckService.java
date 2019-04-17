package compare.site.service.mobilluck.tablets;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.mobilluck.TabletsMobilluck;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TabletsMobilluckService {
    ResponseLoadForFactory saveToBase (ProductSite productSite, WebClient webClient);
    List<TabletsMobilluck> findAllTablets();
    Page<TabletsMobilluck> findAllPageUsingPageable(int page, int size);
    Page<TabletsMobilluck> findAllByModelContains(String s, Pageable pageable);
    void deleteAllTablets();
}
