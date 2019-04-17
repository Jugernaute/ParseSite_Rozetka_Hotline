package compare.site.dao.mobilluck;

import compare.site.entity.mobilluck.TabletsMobilluck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabletsMobilluckDao extends JpaRepository<TabletsMobilluck, Integer> {
    Page<TabletsMobilluck> findAllByModelContains(String s, Pageable pageable);
}
