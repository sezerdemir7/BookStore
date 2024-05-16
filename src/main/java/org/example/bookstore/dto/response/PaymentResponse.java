package org.example.bookstore.dto.response;

import lombok.*;
import org.example.bookstore.entity.User;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long userId;
    private int tutar;
}
