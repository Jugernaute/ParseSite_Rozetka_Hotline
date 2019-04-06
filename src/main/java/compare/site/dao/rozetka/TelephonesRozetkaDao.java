package compare.site.dao.rozetka;

import compare.site.entity.rozetka.TelephonesRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelephonesRozetkaDao extends JpaRepository<TelephonesRozetka, Integer> {

//    @Query(value = "select * from parsing_site.tablets_rozetka t where model_tablets like ?1", nativeQuery = true)
    Page<TelephonesRozetka> findAllByModelContains(String s, Pageable pageable);

}
