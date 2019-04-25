package compare.site.service;

import org.springframework.stereotype.Component;

@Component
public class NumberConcreteProductInBase {
    private Long num;

    public NumberConcreteProductInBase() {
    }

    void setNum(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }
}
