package com.perficient.praxis.gildedrose.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name="ItemLegendary")

@DiscriminatorValue("LEGENDARY")
public class ItemLegendary extends Item {

    public ItemLegendary(){}
    public ItemLegendary(int id, String name, int sellIn, int quality) {
        super(id,name,sellIn,quality,Type.LEGENDARY);

    }

    @Override
    public void updateQuality() {
    }
}
