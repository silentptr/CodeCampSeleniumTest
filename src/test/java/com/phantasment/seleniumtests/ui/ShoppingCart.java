package com.phantasment.seleniumtests.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart
{
    private ArrayList<CartItem> items;

    public ShoppingCart(ArrayList<CartItem> items)
    {
        this.items = items;
    }

    public List<CartItem> getItems()
    {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotal()
    {
        BigDecimal total = new BigDecimal("0");

        for (CartItem item : items)
        {
            total = total.add(item.getSubtotal());
        }

        return total;
    }
}
