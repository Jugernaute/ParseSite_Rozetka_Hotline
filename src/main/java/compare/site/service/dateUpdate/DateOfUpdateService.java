package compare.site.service.dateUpdate;

import compare.site.entity.DateOfUpdate;

public interface DateOfUpdateService {
    DateOfUpdate save(DateOfUpdate dateOfUpdate);
    DateOfUpdate updateDateOfLoadProduct(String date, String site, String product);
}
