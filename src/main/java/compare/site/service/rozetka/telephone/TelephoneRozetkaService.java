package compare.site.service.rozetka.telephone;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.ResponseUploadForFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TelephoneRozetkaService {
    void deleteAllTelephones();
    Page<TelephonesRozetka> findAllPageUsingPageable(int page, int size);
    Page<TelephonesRozetka> findAllByModelContains(String s, Pageable pageable);
    ResponseUploadForFactory saveToBase (ProductSite productSite, WebClient webClient);
}
