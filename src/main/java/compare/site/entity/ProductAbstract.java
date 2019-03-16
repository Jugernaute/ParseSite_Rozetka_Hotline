package compare.site.entity;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class ProductAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private int price;


    @Column (name = "descr", length = 2000)
    private String descript;

    @Column (name = "link", length = 500)
    private String linkOnSite;

    @Transient
    private EnumSite enumSite;
    @Transient
    private EnumProducts enumProducts;



    public ProductAbstract() {
    }

    public ProductAbstract(String model, int price, String descript, String linkOnSite, EnumSite enumSite, EnumProducts enumProducts) {
        this.model = model;
        this.price = price;
        this.descript = descript;
        this.linkOnSite = linkOnSite;
        this.enumSite = enumSite;
        this.enumProducts = enumProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getLinkOnSite() {
        return linkOnSite;
    }

    public void setLinkOnSite(String linkOnSite) {
        this.linkOnSite = linkOnSite;
    }

    public EnumSite getEnumSite() {
        return enumSite;
    }

    public void setEnumSite(EnumSite enumSite) {
        this.enumSite = enumSite;
    }

    public EnumProducts getEnumProducts() {
        return enumProducts;
    }

    public void setEnumProducts(EnumProducts enumProducts) {
        this.enumProducts = enumProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAbstract that = (ProductAbstract) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(model, that.model) &&
                Objects.equals(descript, that.descript) &&
                Objects.equals(linkOnSite, that.linkOnSite) &&
                enumSite == that.enumSite &&
                enumProducts == that.enumProducts;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, model, price, descript, linkOnSite, enumSite, enumProducts);
    }

    @Override
    public String toString() {
        return "ProductAbstract{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", descript='" + descript + '\'' +
                ", linkOnSite='" + linkOnSite + '\'' +
                ", enumSite=" + enumSite +
                ", enumProducts=" + enumProducts +
                '}';
    }
}
