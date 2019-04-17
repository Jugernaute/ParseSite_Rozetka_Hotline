package compare.site.service.mobilluck.telephones;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.mobilluck.TelephonesMobilluck;
import compare.site.service.ResponseLoadForFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TelephonesMobilluckService {
    ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient);
    List<TelephonesMobilluck> findAllTelephones();
    void deleteAllTelephones();
    Page<TelephonesMobilluck> findAllPageUsingPageable(int page, int size);
    Page<TelephonesMobilluck> findAllByModelContains(String s, Pageable pageable);
}
