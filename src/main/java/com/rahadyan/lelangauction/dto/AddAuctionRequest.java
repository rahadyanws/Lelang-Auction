package com.rahadyan.lelangauction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAuctionRequest {
    public double bidOpen;
    public double bidIncrement;
    public double buyOutPrice;
    public String category;
    public String description;
    public String name;
    public String userId;
    public String pictUrl;
}
