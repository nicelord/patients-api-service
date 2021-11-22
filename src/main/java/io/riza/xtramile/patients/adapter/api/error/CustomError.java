package io.riza.xtramile.patients.adapter.api.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError extends RuntimeException{
    public CustomError(String message) {
        super(message);
    }
}
