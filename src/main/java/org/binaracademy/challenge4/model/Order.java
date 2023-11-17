package org.binaracademy.challenge4.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String orderID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "completed")
    private Boolean completed;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}