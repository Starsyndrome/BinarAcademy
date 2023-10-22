package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String merchantID;

    @Column(name = "merchant_code")
    private String merchantCode;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_location")
    private String merchantLocation;

    @Column(name = "merchant_open")
     private Boolean open;
}