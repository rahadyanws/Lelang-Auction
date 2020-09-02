package com.rahadyan.lelangauction.repository;

import com.rahadyan.lelangauction.model.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, String> {
    
}
