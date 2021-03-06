package com.perficient.praxis.gildedrose.model;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name="ItemNormal")

@DiscriminatorValue("NORMAL")
public class ItemNormal extends Item {


    public ItemNormal(){

    }
    public ItemNormal(int id, String name, int sellIn, int quality) {
        super(id,name,sellIn,quality,Type.NORMAL);

    }

    @Override
    public void updateQuality(){
        this.sellIn-=1;
        if (this.sellIn<0 && this.quality>0){this.quality-=1;}
        if (this.quality<=50 && this.quality>0){this.quality-=1;}

    }
}