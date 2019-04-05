package compare.site.service.dateUpdate;

import compare.site.dao.DateOfUpdateDao;
import compare.site.entity.DateOfUpdate;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DateOfUpdateServImpl implements DateOfUpdateService {
    @Autowired
    private DateOfUpdateDao dateOfUpdateDao;

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
}
