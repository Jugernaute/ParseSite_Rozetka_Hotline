package compare.site.entity.rozetka;

import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import compare.site.entity.ProductAbstract;

import javax.persistence.*;

@Entity
@AttributeOverride(name="model",column=@Column(name="modelTelephones"))
public class TelephonesRozetka  extends ProductAbstract {


}
