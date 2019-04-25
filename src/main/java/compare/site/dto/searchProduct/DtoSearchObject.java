package compare.site.dto.searchProduct;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import compare.site.dto.productSite.ProductSite;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@JsonDeserialize(using = DtoSearchObjectDeserilizer.class)
@Component
public class DtoSearchObject {
    private ProductSite productSite;
    private Pageable pageable;
    private String search;

    public DtoSearchObject() {
    }

    public ProductSite getProductSite() {
        return productSite;
    }

    public void setProductSite(ProductSite productSite) {
        this.productSite = productSite;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "DtoSearchObject{" +
                "productSite=" + productSite +
                ", pageable=" + pageable +
                ", search='" + search + '\'' +
                '}';
    }
}
