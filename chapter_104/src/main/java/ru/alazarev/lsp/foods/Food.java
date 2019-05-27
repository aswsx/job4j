package ru.alazarev.lsp.foods;

import java.util.Date;

/**
 * Class Food решение задачи части 004. 3.1. Хранилище продуктов [#852]
 *
 * @author Aleksey Lazarev
 * @since 27.05.2019
 */
public class Food {
    private String name;
    private Date expireDate;
    private Date createDate;
    private double price;
    private double discount;

    /**
     * Constructor.
     *
     * @param name       Value of food name.
     * @param expireDate Value of expire food date.
     * @param createDate Value of create food date.
     * @param price      Value of food price.
     * @param discount   Value of food discount.
     */
    public Food(String name, Date expireDate, Date createDate, double price, double discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    /**
     * Method return food name variable.
     *
     * @return food name variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Method setup food name variable.
     *
     * @param name food name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method return food expire date variable.
     *
     * @return food expire date variable.
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Method set food expire date variable.
     *
     * @param expireDate food expire date value.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Method return food create date variable.
     *
     * @return food create date variable.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Method set food create date variable.
     *
     * @param createDate foodcreate date value.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Method return food price variable.
     *
     * @return food price variable.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method set food price variable.
     *
     * @param price food price value.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method return food discount variable.
     *
     * @return food discount variable.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Method set food discount variable.
     *
     * @param discount food discount value.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }


    /**
     * Method return shelf life.
     *
     * @return shelf life.
     */
    public long freshDays() {
        return this.expireDate.getTime() - this.createDate.getTime();
    }
}