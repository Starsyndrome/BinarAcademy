package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    @Query(nativeQuery = true, value = "insert into order_detail(orderDetailID, product_id, order_id, quantity, total_price) " +
            "values(:orderDetailID, :productID, :orderID, :qty, :totalPrice)")
    void submitNewOrderDetail(@Param("orderDetailID") String orderDetailID, @Param("productID") Product product,
                              @Param("orderID") Order order, @Param("qty") Integer qty, @Param("totalPrice") Double totalPrice);
}