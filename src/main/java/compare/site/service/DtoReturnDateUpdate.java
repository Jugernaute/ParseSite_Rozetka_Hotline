package compare.site.service;

import compare.site.dto.DtoCreator;
import compare.site.entity.DateOfUpdate;
import compare.site.service.dateUpdate.DateOfUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoReturnDateUpdate {
    @Autowired
    DateOfUpdateService ofUpdateService;
    @Autowired
    DtoCreator dtoCreator;

    public String returnDate (){
        DateOfUpdate byProductsAndSite = ofUpdateService.findByProductsAndSite(dtoCreator.getSite(), dtoCreator.getProduct());
        //        System.out.println("return " + dateTime);
        return byProductsAndSite.getDateTime();
    }
}
