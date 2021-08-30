package com.phantasment.seleniumtests.ui;

import java.math.BigDecimal;

public class CartItem
{
    private String name;
    private BigDecimal price;
    private int quantity;

    public CartItem(String name, BigDecimal price)
    {
        this(name, price, 1);
    }

    public CartItem(String name, BigDecimal price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public BigDecimal getSubtotal()
    {
        return price.multiply(new BigDecimal(quantity));
    }
}
