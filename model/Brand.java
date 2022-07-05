package model;

import java.io.Serializable;

public class Brand implements Serializable {
    public static int ID_BRAND = 1;
    protected int id;
    protected String nameBrand;

    public Brand() {
    }

    public Brand( String nameBrand) {
        this.id = ID_BRAND++;
        this.nameBrand = nameBrand;
    }

    public static int getIdBrand() {
        return ID_BRAND;
    }

    public static void setIdBrand(int idBrand) {
        ID_BRAND = idBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    @Override
    public String toString() {
        return "Brand.txt{" +
                "id=" + id +
                ", Thương hiệu: '" + nameBrand + '\'' +
                '}';
    }
}
