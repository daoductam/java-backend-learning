package com.tamdao.order.mapper;

import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-23T11:08:31+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.2.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.customerName( order.getCustomerName() );
        orderResponse.email( order.getEmail() );
        orderResponse.productName( order.getProductName() );
        orderResponse.quantity( order.getQuantity() );
        orderResponse.price( order.getPrice() );
        orderResponse.createdAt( order.getCreatedAt() );

        return orderResponse.build();
    }
}
