package compare.site.dao.rozetka;

import compare.site.entity.rozetka.TabletsRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabletsRozetkaDao extends JpaRepository<TabletsRozetka, Integer>  {

    Page<TabletsRozetka> findAllByModelContains(String s, Pageable pageable);

}