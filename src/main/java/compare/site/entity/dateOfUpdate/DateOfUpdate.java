package compare.site.entity.dateOfUpdate;

import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class DateOfUpdate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idDateOfUpdate;

    private String dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "site")
    private EnumSite enumSite = EnumSite.ROZETKA;

    @Enumerated(EnumType.STRING)
    @Column(name = "product")
    private EnumProducts enumProducts;

    public DateOfUpdate() {
    }

    public DateOfUpdate(EnumSite enumSite, EnumProducts enumProducts) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        this.dateTime = ZonedDateTime.now().format(formatter);
        this.enumSite = enumSite;
        this.enumProducts = enumProducts;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
