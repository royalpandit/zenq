package com.abhishek.zenq.Bean;

public class AllBidBean {
    /**
     * id : 2
     * from_address : Kota
     * to_address : Mount Abu
     * from_date : 2019-12-08
     * to_date : 2020-01-01
     * weight : 10000
     * price_per_ton : 31
     * truck_type : Truck 2
     */

    private String id;
    private String from_address;
    private String to_address;
    private String from_date;
    private String to_date;
    private String weight;
    private String price_per_ton;
    private String truck_type;
    private String material_type;

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice_per_ton() {
        return price_per_ton;
    }

    public void setPrice_per_ton(String price_per_ton) {
        this.price_per_ton = price_per_ton;
    }

    public String getTruck_type() {
        return truck_type;
    }

    public void setTruck_type(String truck_type) {
        this.truck_type = truck_type;
    }
}
