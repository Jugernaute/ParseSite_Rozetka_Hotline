package compare.site.entity.rozetka;

import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import compare.site.entity.ProductAbstract;

import javax.persistence.*;

@Entity
@AttributeOverride(name="model",column=@Column(name="modelTelephones"))
public class TelephonesRozetka  extends ProductAbstract {

//    @ManyToOne(fetch = FetchType.EAGER,
//    cascade = CascadeType.ALL)
//    DateOfUpdate dateOfUpdate;


    public TelephonesRozetka() {
    }

    public TelephonesRozetka(String model, int price, String descript, String linkOnSite, EnumSite enumSite, EnumProducts enumProducts) {
        super(model, price, descript, linkOnSite, enumSite, enumProducts);
    }
}
