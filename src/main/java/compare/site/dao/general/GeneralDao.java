package compare.site.dao.general;

import compare.site.entity.ProductAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneralDao <T extends ProductAbstract> extends JpaRepository<T, Integer> {

    @Query(value = "SELECT * FROM parsing_site.telephones_rozetka WHERE parsing_site.tablets_rozetka.model_tablets like ?1",
    countQuery = "SELECT count(*) FROM parsing_site.tablets_rozetka WHERE model_tablets like ?1", nativeQuery = true)
    Page<T> findAllByModelContains(String s, Pageable pageable);

    @Query(value = "select parsing_site.date_of_update.date_time from parsing_site.date_of_update where site = :site and product = :product",nativeQuery = true)
    T findByEnumProductsAndEnumSite(@Param("site") String site,
                                    @Param("product") String product);
}
