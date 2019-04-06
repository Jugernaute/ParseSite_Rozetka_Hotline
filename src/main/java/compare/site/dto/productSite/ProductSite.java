package compare.site.dto.productSite;

import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import org.springframework.stereotype.Component;

@Component
public class ProductSite {
    private EnumSite site;
    private EnumProducts product;

    public ProductSite() {
    }

    public void create (String site, String product) {
        for ( EnumSite enumSite1 : EnumSite.values() ) {
            if( enumSite1.toString().equalsIgnoreCase(site) ) {
                ProductSite.this.setSite(enumSite1);
            }
        }
        for (EnumProducts enumProducts : EnumProducts.values()){
            if (enumProducts.name().equalsIgnoreCase(product)){
                ProductSite.this.setProduct(enumProducts);
            }
        }
    }

    public EnumSite getSite() {
        return site;
    }

    public void setSite(EnumSite site) {
        this.site = site;
    }

    public EnumProducts getProduct() {
        return product;
    }

    public void setProduct(EnumProducts product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductSite{" +
                "site=" + site +
                ", product=" + product +
                '}';
    }
}
