package compare.site.dao.hotline;

import compare.site.entity.hotline.TabletsHotline;
import compare.site.entity.hotline.TelephonesHotline;
import compare.site.entity.rozetka.TabletsRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephonesHotlineDao extends JpaRepository<TelephonesHotline, Integer> {

    Page<TelephonesHotline> findAllByModelContains(String s, Pageable pageable);
}
