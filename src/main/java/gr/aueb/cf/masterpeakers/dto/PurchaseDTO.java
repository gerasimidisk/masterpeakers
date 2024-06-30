package gr.aueb.cf.masterpeakers.dto;

import lombok.Data;

@Data
public class PurchaseDTO {
    private Long gameId;
    private Integer quantity;
    private double price;
}
