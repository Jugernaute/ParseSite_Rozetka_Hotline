package compare.site.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class DateOfUpdate {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int idDateOfUpdate;

//    @Column(name = "dataUpdate")
//    @DateTimeFormat(pattern = "EEE, d MMM yyyy HH:mm:ss")
    private String dateTime;

    @Column(name = "site")
    private String enumSite;
    @Column(name = "product")
    private String enumProducts;

    public DateOfUpdate() {
    }

    public DateOfUpdate(EnumSite enumSite, EnumProducts enumProducts) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        this.dateTime = ZonedDateTime.now().format(formatter);
        this.enumSite = enumSite.name();
        this.enumProducts = enumProducts.name();
    }

    public int getIdDateOfUpdate() {
        return idDateOfUpdate;
    }

    public void setIdDateOfUpdate(int idDateOfUpdate) {
        this.idDateOfUpdate = idDateOfUpdate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getEnumSite() {
        return enumSite;
    }

    public void setEnumSite(String enumSite) {
        this.enumSite = enumSite;
    }

    public String getEnumProducts() {
        return enumProducts;
    }

    public void setEnumProducts(String enumProducts) {
        this.enumProducts = enumProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateOfUpdate that = (DateOfUpdate) o;
        return idDateOfUpdate == that.idDateOfUpdate &&
                Objects.equals(dateTime, that.dateTime) &&
                enumSite == that.enumSite &&
                enumProducts == that.enumProducts;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idDateOfUpdate, dateTime, enumSite, enumProducts);
    }

    @Override
    public String toString() {
        return "DateOfUpdateDao{" +
                "idDateOfUpdate=" + idDateOfUpdate +
                ", dateTime=" + dateTime +
                ", enumSite=" + enumSite +
                ", enumProducts=" + enumProducts +
                '}';
    }
}
