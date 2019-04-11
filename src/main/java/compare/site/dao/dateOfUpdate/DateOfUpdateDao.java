package compare.site.dao.dateOfUpdate;

import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DateOfUpdateDao extends JpaRepository<DateOfUpdate, Integer> {
    @Modifying //Modifying queries can only use void or int/Integer as return type!
    @Transactional
    @Query(value = "update parsing_site.date_of_update set date_of_update.date_time=:date where site =:site and product=:product", nativeQuery = true)
    void updateDateOfLoadProduct (@Param("date") String date,
                                  @Param("site") String site,
                                  @Param("product") String product);

    DateOfUpdate findByEnumSiteAndEnumProducts (EnumSite site, EnumProducts products);
}
