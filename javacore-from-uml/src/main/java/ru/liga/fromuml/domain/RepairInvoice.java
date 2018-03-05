package ru.liga.fromuml.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;

public class RepairInvoice {
    private List<Flat> flats;
    private String customer;

    public BigDecimal wholePrice() {
        throw new NotImplementedException();
    }

    public String report() {
        return "итого:" + wholePrice();
    }

}
