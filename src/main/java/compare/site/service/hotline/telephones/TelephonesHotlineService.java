package compare.site.service.hotline.telephones;

import compare.site.entity.hotline.TelephonesHotline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TelephonesHotlineService {
    void saveTelephone(TelephonesHotline telephones);
    List<TelephonesHotline> findAllTelephones();
    Page<TelephonesHotline> findAllPageUsingPageable(int page, int size);
    Page<TelephonesHotline> findAllByModelContains(String s, Pageable pageable);
}
