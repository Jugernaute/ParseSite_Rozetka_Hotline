package compare.site.service.rozetka.tablet;

//import compare.site.entity.Tablets;
import compare.site.entity.rozetka.TabletsRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TabletRozetkaService {


    void saveTablet(TabletsRozetka tablets);
    List<TabletsRozetka> findAllTablets();
    Page<TabletsRozetka> findAllPageUsingPageable(int page, int size);
    Page<TabletsRozetka> findAllByModelContains(String s, Pageable pageable);

}
