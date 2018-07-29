package com.shymaaothman.crossworkerstask.entities;

/**
 * Created by Shymaa Othman on 7/25/2018.
 */

public class Content {

    private String discount;
    private String  price ;
    private Boolean redeemable;
    private String   text ;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getRedeemable() {
        return redeemable;
    }

    public void setRedeemable(Boolean redeemable) {
        this.redeemable = redeemable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
