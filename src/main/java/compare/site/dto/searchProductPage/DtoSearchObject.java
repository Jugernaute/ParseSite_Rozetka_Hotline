package compare.site.dto.searchProductPage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import compare.site.dto.Pageable;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import org.springframework.stereotype.Component;

@JsonDeserialize(using = DtoSearchObjectDeserilizer.class)
@Component
public class DtoSearchObject {
    private EnumSite site;
    private EnumProducts product;
    private Pageable pageable;
    private String search;

    public DtoSearchObject() {
    }

    public EnumProducts getProduct() {
        return product;
    }

    public EnumSite getSite() {
        return site;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public void setSite(EnumSite site) {
        this.site = site;
    }

    public void setProduct(EnumProducts product) {
        this.product = product;
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
                "site=" + site +
                ", product=" + product +
                ", pageable=" + pageable +
                ", search='" + search + '\'' +
                '}';
    }
}
