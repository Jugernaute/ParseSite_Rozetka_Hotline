package compare.site.service;

import compare.site.dto.DtoCreator;
import compare.site.entity.DateOfUpdate;
import compare.site.methods.SaveProduct;
import compare.site.service.dateUpdate.DateOfUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveOrUpdateDateOfLoadSiteProduct {
    @Autowired
    private DtoCreator dtoCreator;
    @Autowired
    private DateOfUpdateService dateOfUpdateService;

    private String dateUpdate;

    public String execute(DateOfUpdate dateOfUpdate) {

        try {
            dateUpdate = dateOfUpdate.getDateTime();
            dateOfUpdateService.findByProductsAndSite(dtoCreator.getSite(), dtoCreator.getProduct()).getDateTime();
            dateOfUpdateService.updateDateOfLoadProduct(dateUpdate, dtoCreator.getSite().name(), dtoCreator.getProduct().name());
        } catch (NullPointerException e) {
            dateOfUpdateService.save(dateOfUpdate);
        }
        SaveProduct.nums = 0;

        return dateUpdate;
    }
}

