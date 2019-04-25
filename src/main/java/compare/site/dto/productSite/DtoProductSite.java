package compare.site.dto.productSite;

import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.service.dateUpdate.DateOfUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoProductSite {
    @Autowired
    DateOfUpdateService dateOfUpdateService;

    private ProductSite productSite;
    private int size;
    private String dateOfUpdate;

    public DtoProductSite() {
    }

    public void createDto(ProductSite productSite) {

        DateOfUpdate byProductsAndSite = dateOfUpdateService.findByProductsAndSite(productSite.getSite(), productSite.getProduct());
        if (byProductsAndSite!=null){
            this.dateOfUpdate = byProductsAndSite.getDateTime();
        }else {
            this.dateOfUpdate = "missing";
        }
    }

    public ProductSite getProductSite() {
        return productSite;
    }

    public void setProductSite(ProductSite productSite) {
        this.productSite = productSite;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(String dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }


}



