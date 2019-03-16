package compare.site.dao.hotline;

import compare.site.entity.hotline.TabletsHotline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabletsHotlineDao extends JpaRepository<TabletsHotline, Integer> {
    Page<TabletsHotline> findAllByModelContains(String s, Pageable pageable);
}
