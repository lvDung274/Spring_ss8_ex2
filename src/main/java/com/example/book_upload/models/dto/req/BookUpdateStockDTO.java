package com.example.book_upload.models.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BookUpdateStockDTO(
        @NotNull(message = "Stock không được để trống")
        @Min(value = 0, message = "Stock phải lớn hơn hoặc bằng 0")
        Integer stock
) {
}
