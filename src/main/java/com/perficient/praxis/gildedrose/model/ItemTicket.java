package com.perficient.praxis.gildedrose.model;

public class ItemTicket extends Item {

    public ItemTicket(int id, String name, int sellIn, int quality) {
        super(id, name, sellIn, quality,Type.TICKETS);

    }

    @Override
    public void updateQuality() {
        this.sellIn=this.sellIn-1;
        if (this.quality < 50) {
            this.quality = this.quality + 1;
        }
        if (this.sellIn < 0) {
            this.quality = this.quality - this.quality;
        }
        else if (this.sellIn < 6 && this.quality < 50) {
            this.quality = this.quality + 2;
        }
        else if(this.sellIn < 11 && this.quality < 50) {
            this.quality = this.quality + 1;
        }


    }
}
