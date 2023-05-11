package org.example.clientService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    private LocalDateTime dateTime;

    public ErrorResponse(String message)
    {
        super();
        this.message = message;
    }
}
