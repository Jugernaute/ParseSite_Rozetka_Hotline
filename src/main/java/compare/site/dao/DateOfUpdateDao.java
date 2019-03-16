package compare.site.dao;

import compare.site.entity.DateOfUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DateOfUpdateDao extends JpaRepository<DateOfUpdate, Integer> {
    @Query(name = "update date_of_update set date_time  = :date where site = :site && product = :product;", nativeQuery = true)
    DateOfUpdate updateDateOfLoadProduct (@Param("date") String dateTime,
                                  @Param("site") String site,
                                  @Param("product") String product);
}
