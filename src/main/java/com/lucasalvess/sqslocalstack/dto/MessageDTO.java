package com.lucasalvess.sqslocalstack.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record MessageDTO(
        String name,
        UUID xRequestId,
        UUID idempotency_id,
        Date dispatchedAt,
        UserDTO userDTO
) {

}
