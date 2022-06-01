package com.perficient.praxis.gildedrose.model;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="ItemAged")

@DiscriminatorValue("AGED")

public class ItemAged extends Item{

    public ItemAged(){}
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