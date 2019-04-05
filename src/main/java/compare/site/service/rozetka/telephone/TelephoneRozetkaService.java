package compare.site.service.rozetka.telephone;

import compare.site.entity.rozetka.TelephonesRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TelephoneRozetkaService {
    void saveTelephone(TelephonesRozetka telephones);
    void deleteAllTelephones();
    List<TelephonesRozetka> findAllTelephones();
    Page<TelephonesRozetka> findAllPageUsingPageable(int page, int size);
    Page<TelephonesRozetka> findAllByModelContains(String s, Pageable pageable);
}
