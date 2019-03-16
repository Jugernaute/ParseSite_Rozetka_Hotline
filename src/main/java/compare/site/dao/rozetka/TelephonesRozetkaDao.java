package compare.site.dao.rozetka;

import compare.site.entity.rozetka.TelephonesRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephonesRozetkaDao extends JpaRepository<TelephonesRozetka, Integer> {

    Page<TelephonesRozetka> findAllByModelContains(String s, Pageable pageable);

}
