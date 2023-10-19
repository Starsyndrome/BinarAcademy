package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(nativeQuery = true, value = "insert into \"order\"(OrderID, order_time, destination_address, completed, users_id) " +
            "values(:orderID, :orderTime, :destinationAddress, :complete, :usersID)")
    void submitNewOrder(@Param("orderID") String orderID, @Param("orderTime") Date orderTime,
                        @Param("destinationAddress") String destinationAddress, @Param("complete") Boolean complete,
                        @Param("usersID")Users users);
}