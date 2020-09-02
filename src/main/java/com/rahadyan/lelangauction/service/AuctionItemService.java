package com.rahadyan.lelangauction.service;

import com.rahadyan.lelangauction.dto.AddAuctionRequest;
import com.rahadyan.lelangauction.dto.UpdateStatusRequest;
import com.rahadyan.lelangauction.enums.AuctionStatus;
import com.rahadyan.lelangauction.model.AuctionItem;
import com.rahadyan.lelangauction.repository.AuctionItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionItemService {
    @Autowired
    AuctionItemRepository auctionItemRepository;

    public List<AuctionItem> findAll() {
        List<AuctionItem> auctionItems = new ArrayList<>();
        this.auctionItemRepository.findAll().forEach(auctionItem -> auctionItems.add(auctionItem));
        return auctionItems;
    }

    public AuctionItem create(AddAuctionRequest addAuctionRequest) {
        AuctionItem auctionItem = AuctionItem.builder()
                .name(addAuctionRequest.getName())
                .description(addAuctionRequest.getDescription())
                .status(AuctionStatus.SUBMITTED)
                .category(addAuctionRequest.getCategory())
                .bidOpen(addAuctionRequest.getBidOpen())
                .bidIncrement(addAuctionRequest.getBidIncrement())
                .buyOutPrice(addAuctionRequest.getBuyOutPrice())
                .userId(addAuctionRequest.getUserId())
                .pictUrl(addAuctionRequest.getPictUrl())
                .build();
        return auctionItemRepository.save(auctionItem);
    }

    @Transactional
    public AuctionItem updateStatus(UpdateStatusRequest updateStatusRequest){
        Optional<AuctionItem> auctionItem = auctionItemRepository.findById(updateStatusRequest.getId());
        auctionItem.get().setStatus(updateStatusRequest.getStatus());
        return auctionItemRepository.save(auctionItem.get());
    }


}
