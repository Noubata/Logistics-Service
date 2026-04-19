package beny.logisticsservice.data.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "order_id", unique = true, nullable = false)
    private UUID orderId;

    @Column(name = "driver_id")
    private UUID driverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShipmentStatus status;

    @Column(name = "origin_address", nullable = false, columnDefinition = "TEXT")
    private String originAddress;

    @Column(name = "dest_address", nullable = false, columnDefinition = "TEXT")
    private String destAddress;

    @Column(name = "origin_lat", nullable = false, precision = 10, scale = 7)
    private BigDecimal originLat;

    @Column(name = "origin_lng", nullable = false, precision = 10, scale = 7)
    private BigDecimal originLng;

    @Column(name = "dest_lat", nullable = false, precision = 10, scale = 7)
    private BigDecimal destLat;

    @Column(name = "dest_lng", nullable = false, precision = 10, scale = 7)
    private BigDecimal destLng;

    @Column(name = "current_lat", precision = 10, scale = 7)
    private BigDecimal currentLat;

    @Column(name = "current_lng", precision = 10, scale = 7)
    private BigDecimal currentLng;

    @Column(name = "eta_minutes")
    private Integer etaMinutes;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}