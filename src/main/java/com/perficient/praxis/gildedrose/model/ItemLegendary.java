package com.perficient.praxis.gildedrose.model;

public class ItemLegendary extends Item {

    public ItemLegendary(int id, String name, int sellIn, int quality) {
        super(id,name,sellIn,quality,Type.LEGENDARY);

    }

    @Override
    public void updateQuality() {
    }
}
