package beny.logisticsservice.event;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderConfirmed {
    private UUID orderId;
    private UUID buyerId;
    private UUID sellerId;
    private String buyerAddress;
    private String sellerAddress;
    private BigDecimal totalAmount;
    private String orderDate;
}
