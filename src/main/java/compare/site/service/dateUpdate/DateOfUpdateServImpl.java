package compare.site.service.dateUpdate;

import compare.site.dao.dateOfUpdate.DateOfUpdateDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
//import compare.site.service.SaveProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DateOfUpdateServImpl  implements DateOfUpdateService {
    @Autowired
    private DateOfUpdateDao dateOfUpdateDao;
    @Autowired
    private ProductSite productSite;

    @Override
    public void save(DateOfUpdate dateOfUpdate) {
        dateOfUpdateDao.save(dateOfUpdate);
    }

    @Override
    public void updateDateOfLoadProduct(String date, String site, String product) {
        dateOfUpdateDao.updateDateOfLoadProduct(date, site, product);
    }

    @Override
    public DateOfUpdate findByProductsAndSite(EnumSite site, EnumProducts product) {
        return dateOfUpdateDao.findByEnumSiteAndEnumProducts(site,product);
    }

    @Override
    public String saveOrUpdateDateOfLoadSiteProduct(DateOfUpdate dateOfUpdate) {
        String dateUpdate=null;
        try {
            dateUpdate = dateOfUpdate.getDateTime();
            findByProductsAndSite(productSite.getSite(), productSite.getProduct()).getDateTime();
            updateDateOfLoadProduct(dateUpdate, productSite.getSite().name(), productSite.getProduct().name());
        } catch (NullPointerException e) {
            save(dateOfUpdate);
        }
//        SaveProduct.nums = 0;
        return dateUpdate;
    }
}
