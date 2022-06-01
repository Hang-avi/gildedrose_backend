package com.perficient.praxis.gildedrose.model;

public class ItemAged extends Item{

    public ItemAged(int id, String name, int sellIn, int quality) {
        super(id,name,sellIn,quality, Type.AGED);

    }

    @Override
    public void updateQuality(){
        this.sellIn=this.sellIn-1;
        if (this.quality < 50) {this.quality = this.quality + 1;}
        if (this.sellIn < 0 && this.quality < 50) {this.quality = this.quality + 1;}

    }
}