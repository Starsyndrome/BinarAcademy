package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
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
    @JoinColumn(name = "users_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Users users;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "completed")
    private Boolean completed;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}