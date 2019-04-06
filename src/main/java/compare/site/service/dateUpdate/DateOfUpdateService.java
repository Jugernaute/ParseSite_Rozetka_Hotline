package compare.site.service.dateUpdate;

import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;

public interface DateOfUpdateService {
    void save(DateOfUpdate dateOfUpdate);
    void updateDateOfLoadProduct(String date, String site, String product);
    DateOfUpdate findByProductsAndSite(EnumSite site, EnumProducts product);
    String saveOrUpdateDateOfLoadSiteProduct (DateOfUpdate dateOfUpdate);
}
