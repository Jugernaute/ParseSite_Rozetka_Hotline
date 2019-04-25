package compare.site.dto.productSite;

public class CopySiteProductDto {
    private ProductSite productSite;
    private int size;
    private String dateOfUpdate;

    public CopySiteProductDto(DtoProductSite dtoProductSite) {
        this.productSite= dtoProductSite.getProductSite();
        this.size = dtoProductSite.getSize();
        this.dateOfUpdate = dtoProductSite.getDateOfUpdate();
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
        return "CopySiteProductDto{" +
                "productSite=" + productSite +
                ", size=" + size +
                ", dateOfUpdate='" + dateOfUpdate + '\'' +
                '}';
    }
}
