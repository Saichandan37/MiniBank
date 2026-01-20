package com.NewGen.MiniBank.exception;

import com.NewGen.MiniBank.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(OptimisticLockException.class)
//    public ResponseEntity<?> handleOptimisticLock() {
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body("Account was modified. Please retry.");
//    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(HttpServletRequest http,UserNotFoundException exception){
//        ExceptionResponse response=new ExceptionResponse(LocalDateTime.now(),exception.getMessage(),http.getRequestURI(),"USR_404");
//        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//
//    }
//    @ExceptionHandler(RoleNotFoundException.class)
//    public ResponseEntity<ExceptionResponse> handleRoleNotFoundException(HttpServletRequest http,RoleNotFoundException) {
//        return buildError();
//    }

       /* =======================
       ACCOUNT / USER ERRORS
       ======================= */

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request) {

        return buildError(
                ex.getMessage(),
                "USR_404",
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleAccountNotFound(
            AccountNotFoundException ex,
            HttpServletRequest request) {

        return buildError(
                ex.getMessage(),
                "ACC_404",
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNotFound(
            RoleNotFoundException ex,
            HttpServletRequest request) {

        return buildError(
                ex.getMessage(),
                "ROLE_404",
                HttpStatus.NOT_FOUND,
                request
        );
    }

    /* =======================
       TRANSACTION ERRORS
       ======================= */

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTransaction(
            InvalidTransactionException ex,
            HttpServletRequest request) {

        return buildError(
                ex.getMessage(),
                "TXN_001",
                HttpStatus.BAD_REQUEST,
                request
        );
    }

//    @ExceptionHandler(InsufficientBalanceException.class)
//    public ResponseEntity<ExceptionResponse> handleInsufficientBalance(
//            InsufficientBalanceException ex,
//            HttpServletRequest request) {
//
//        return buildError(
//                ex.getMessage(),
//                "TXN_002",
//                HttpStatus.BAD_REQUEST,
//                request
//        );
//    }

    /* =======================
       SECURITY ERRORS
       ======================= */

//    @ExceptionHandler(UnauthorizedAccessException.class)
//    public ResponseEntity<ExceptionResponse> handleUnauthorized(
//            UnauthorizedAccessException ex,
//            HttpServletRequest request) {
//
//        return buildError(
//                ex.getMessage(),
//                "AUTH_403",
//                HttpStatus.FORBIDDEN,
//                request
//        );
//    }

    /* =======================
       FALLBACK HANDLER
       ======================= */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        return buildError(
                "Internal server error",
                "GEN_001",
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    /* =======================
       SHARED BUILDER METHOD
       ======================= */

    private ResponseEntity<ExceptionResponse> buildError(
            String message,
            String errorCode,
            HttpStatus status,
            HttpServletRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                message,
                request.getRequestURI(),
                errorCode
        );

        return new ResponseEntity<>(response, status);
    }


}
