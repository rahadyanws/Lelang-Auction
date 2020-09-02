package com.rahadyan.lelangauction.controller;

import com.rahadyan.lelangauction.dto.AddAuctionRequest;
import com.rahadyan.lelangauction.dto.ResponseWrapper;
import com.rahadyan.lelangauction.dto.UpdateStatusRequest;
import com.rahadyan.lelangauction.model.AuctionItem;
import com.rahadyan.lelangauction.service.AuctionItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String BROWSE_AUCTION_ITEM_SUCCESS = "Auction Shown Success!";
    private static final String BROWSE_AUCTION_ITEM_FAILED = "Auction Shown Failed!";
    private static final String ADD_AUCTION_ITEM_SUCCESS = "Auction Created Success!";
    private static final String ADD_AUCTION_ITEM_FAILED = "Auction Created Failed!";
    private static final String UPDATE_AUCTION_ITEM_STATUS_SUCCESS = "Auction Status Updated Success!";
    private static final String UPDATE_AUCTION_ITEM_STATUS_FAILED = "Auction Status Updated Failed!";

    @Autowired
    AuctionItemService auctionItemService;

    @CrossOrigin(origins = "*")
    @GetMapping("/browse")
    public ResponseEntity<ResponseWrapper> browse() {
        ResponseWrapper responseMessage = null;
        List<String> errors = new ArrayList<>();
        try{
            List<AuctionItem> auctionItems = auctionItemService.findAll();
            responseMessage = new ResponseWrapper(BROWSE_AUCTION_ITEM_SUCCESS, auctionItems, 200, errors);
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(BROWSE_AUCTION_ITEM_FAILED, null, 404, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public ResponseEntity<ResponseWrapper> add(@RequestBody AddAuctionRequest addAuctionRequest, Errors err) {
        AuctionItem auctionItem = null;
        ResponseWrapper responseMessage = null;
        List<String> errors = new ArrayList<>();
        try {
            auctionItem = auctionItemService.create(addAuctionRequest);
            responseMessage = new ResponseWrapper(ADD_AUCTION_ITEM_SUCCESS, auctionItem, 200, errors);
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(ADD_AUCTION_ITEM_FAILED, auctionItem, 500, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/update-status")
    public ResponseEntity<ResponseWrapper> topup(@RequestBody UpdateStatusRequest updateStatusRequest, Errors err) {
        ResponseWrapper responseMessage = null;
        List<String> errors = new ArrayList<>();
        AuctionItem auctionItem = null;
        try {
            auctionItem = this.auctionItemService.updateStatus(updateStatusRequest);
            responseMessage = new ResponseWrapper(UPDATE_AUCTION_ITEM_STATUS_SUCCESS, auctionItem, 200, errors);
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(UPDATE_AUCTION_ITEM_STATUS_FAILED, auctionItem, 500, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }
}
