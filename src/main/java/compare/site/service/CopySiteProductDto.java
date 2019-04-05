package compare.site.service;

import compare.site.dto.DtoCreator;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;

public class CopySiteProductDto {
    private EnumSite site;
    private EnumProducts product;
    private int size;
    private String dateOfUpdate;

    public CopySiteProductDto(DtoCreator dtoCreator) {
        this.site = dtoCreator.getSite();
        this.product = dtoCreator.getProduct();
        this.size = dtoCreator.getSize();
        this.dateOfUpdate = dtoCreator.getDateOfUpdate();
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

    @Override
    public String toString() {
        return "CopyClass{" +
                "site=" + site +
                ", product=" + product +
                ", size=" + size +
                ", dateOfUpdate='" + dateOfUpdate + '\'' +
                '}';
    }
}
