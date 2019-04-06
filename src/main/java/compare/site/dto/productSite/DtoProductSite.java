package compare.site.dto.productSite;

import compare.site.entity.dateOfUpdate.DateOfUpdate;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import compare.site.entity.ProductAbstract;
import compare.site.service.general.GeneralService;
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
//        for ( EnumSite enumSite1 : EnumSite.values() ) {
//            if( enumSite1.toString().equalsIgnoreCase(site) ) {
//                DtoProductSite.this.productSite.setSite(enumSite1);
//            }
//        }
//        for (EnumProducts enumProducts : EnumProducts.values()){
//            if (enumProducts.name().equalsIgnoreCase(product)){
//                DtoProductSite.this.productSite.setProduct(enumProducts);
//            }
//        }
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
//        public EnumSite getSite() {
//        return site;
//    }
//
//    public void setSite(EnumSite site) {
//        this.site = site;
//    }
//
//    public EnumProducts getProduct() {
//        return product;
//    }
//
//    public void setProduct(EnumProducts product) {
//        this.product = product;
//    }

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



