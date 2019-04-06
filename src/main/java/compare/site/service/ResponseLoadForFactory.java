package compare.site.service;

public class ResponseLoadForFactory {

    private String size;
    private String dateUpdate;

    public ResponseLoadForFactory(String size, String dateUpdate) {
        this.size = size;
        this.dateUpdate = dateUpdate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
