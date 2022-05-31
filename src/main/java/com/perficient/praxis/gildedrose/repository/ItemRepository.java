package com.perficient.praxis.gildedrose.repository;

import com.perficient.praxis.gildedrose.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
