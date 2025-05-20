package com.tui.proof.application.exception.handler;

import com.tui.proof.domain.exception.ClientNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler =
            new GlobalExceptionHandler();

    @Test
    void can_handle_domain_exception_and_return_error() {
        assertEquals(ErrorDTO.builder()
                        .code("Not Found")
                        .message("could not find client with id: " + 1L)
                        .build(),
                globalExceptionHandler.handleException(
                        new ClientNotFoundException("could not find client with id: " + 1L)));
    }

    @Test
    void can_handle_exception_and_return_error() {
        assertEquals(ErrorDTO.builder()
                        .code("Internal Server Error")
                        .message("Unexpected error!")
                        .build(),
                globalExceptionHandler.handleException(new Exception("Some error")));
    }

}