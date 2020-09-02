package com.rahadyan.lelangauction.model;



import com.rahadyan.lelangauction.enums.AuctionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "auction_item")
public class AuctionItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(length = 50)
    @NotEmpty(message = "name must not empty")
    @Size(min = 10, max = 50, message = "name minimal length 10 and maximal length 50")
    @Pattern(regexp = "[a-zA-Z0-9\\s]+", message = "name must in alphanumeric format")
    private String name;

    @Column(length = 300)
    @Size(max = 300, message = "description max length is 300")
    private String description;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    @Column(length = 100)
    @NotEmpty(message = "category must not empty")
    private String category;

    @Positive(message = "bidOpen should be more than 0")
    private double bidOpen;

    @Positive(message = "bidIncrement should be more than 0")
    private double bidIncrement;

    @Positive(message = "buyOutPrice should be more than 0")
    private double buyOutPrice;

    @NotEmpty(message = "userId must not empty")
    private String userId;

    @NotEmpty(message = "pictUrl must not empty")
    private String pictUrl;

    public AuctionItem(
            String name,
            String description, AuctionStatus status,
            String category,
            double bidOpen,
            double bidIncrement,
            double buyOutPrice,
            String userId,
            String pictUrl) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.bidOpen = bidOpen;
        this.bidIncrement = bidIncrement;
        this.buyOutPrice = buyOutPrice;
        this.userId = userId;
        this.pictUrl = pictUrl;
    }
}