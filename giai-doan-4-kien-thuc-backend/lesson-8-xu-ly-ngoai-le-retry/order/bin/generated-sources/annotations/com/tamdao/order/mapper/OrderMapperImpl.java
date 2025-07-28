package com.tamdao.order.mapper;

import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-14T14:23:52+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.createdAt( order.getCreatedAt() );
        orderResponse.customerName( order.getCustomerName() );
        orderResponse.id( order.getId() );
        orderResponse.price( order.getPrice() );
        orderResponse.productName( order.getProductName() );
        orderResponse.quantity( order.getQuantity() );

        return orderResponse.build();
    }
}
