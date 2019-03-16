package compare.site.service.dateUpdate;

import compare.site.dao.DateOfUpdateDao;
import compare.site.entity.DateOfUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DateOfUpdateServImpl implements DateOfUpdateService {
    @Autowired
    private DateOfUpdateDao updateDao;

    @Override
    public DateOfUpdate save(DateOfUpdate dateOfUpdate) {
        return updateDao.save(dateOfUpdate);
    }

    @Override
    public DateOfUpdate updateDateOfLoadProduct(String date, String site, String product) {
        return updateDao.updateDateOfLoadProduct(date, site, product);
    }
}
