package com.perficient.praxis.gildedrose.model;

import javax.persistence.*;

@Entity(name="Item")
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Item_type")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public String name;

    public int sellIn;

    public int quality;

    public Type type;

    public enum Type {
        AGED,
        NORMAL,
        LEGENDARY,
        TICKETS
    }
    //triggering Jenkins
    public Item() {
    }

    public Item(int id, String name, int sellIn, int quality, Type type) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateQuality(){}

    @Override
    public String toString() {
        return this.id+ ", " +this.name + ", " + this.sellIn + ", " + this.quality;
    }
}