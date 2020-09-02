package com.rahadyan.lelangauction.dto;

import com.rahadyan.lelangauction.enums.AuctionStatus;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    public String id;
    public AuctionStatus status;
}
