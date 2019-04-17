package compare.site.dao.mobilluck;

import compare.site.entity.mobilluck.TelephonesMobilluck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephonesMobilluckDao extends JpaRepository<TelephonesMobilluck, Integer> {

    Page<TelephonesMobilluck> findAllByModelContains(String s, Pageable pageable);
}
