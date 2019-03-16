package compare.site.service.hotline.tablets;

import compare.site.entity.hotline.TabletsHotline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TabletsHotlineService {
    void saveTablets(TabletsHotline telephones);
    List<TabletsHotline> findAllTablets();
    Page<TabletsHotline> findAllPageUsingPageable(int page, int size);
    Page<TabletsHotline> findAllByModelContains(String s, Pageable pageable);
}
