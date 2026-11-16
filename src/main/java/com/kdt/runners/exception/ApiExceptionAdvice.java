
package com.kdt.runners.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiExceptionAdvice {

    // 400: 입력값 문제 (직접 던지는 것 + Spring이 던지는 3종)
    @ExceptionHandler({
            InvalidInputException.class,
            MethodArgumentNotValidException.class,      // @Valid 검증 실패
            HttpMessageNotReadableException.class,      // JSON 형식 오류·본문 누락
            MethodArgumentTypeMismatchException.class   // /runners/abc 처럼 타입이 안 맞음
    })
    ResponseEntity<ApiError> invalidInput(Exception ex) {
        return error(HttpStatus.BAD_REQUEST, "INVALID_INPUT", "invalid input");
    }

    @ExceptionHandler(CannotChangeOwnRoleException.class)
    ResponseEntity<ApiError> cannotChangeOwnRole(CannotChangeOwnRoleException ex) {
        return error(HttpStatus.BAD_REQUEST, "CANNOT_CHANGE_OWN_ROLE", "cannot change own role");
    }

    @ExceptionHandler(CannotDeleteSelfException.class)
    ResponseEntity<ApiError> cannotDeleteSelf(CannotDeleteSelfException ex) {
        return error(HttpStatus.BAD_REQUEST, "CANNOT_DELETE_SELF", "cannot delete self");
    }

    // 401: 인증 실패
    @ExceptionHandler(LoginFailedException.class)
    ResponseEntity<ApiError> loginFailed(LoginFailedException ex) {
        return error(HttpStatus.UNAUTHORIZED, "LOGIN_FAILED", "login failed");
    }

    @ExceptionHandler(TokenFailedException.class)
    ResponseEntity<ApiError> tokenFailed(TokenFailedException ex) {
        return error(HttpStatus.UNAUTHORIZED, "TOKEN_FAILED", "token failed");
    }

    // 403: 권한 없음
    @ExceptionHandler(ForbiddenException.class)
    ResponseEntity<ApiError> forbidden(ForbiddenException ex) {
        return error(HttpStatus.FORBIDDEN, "FORBIDDEN", "forbidden");
    }

    // 404: 대상 없음
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiError> notFound(NotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, "NOT_FOUND", "runner not found");
    }

    // 409: 중복
    @ExceptionHandler(DuplicateLoginIdException.class)
    ResponseEntity<ApiError> duplicateLoginId(DuplicateLoginIdException ex) {
        return error(HttpStatus.CONFLICT, "DUPLICATE_LOGIN_ID", "duplicate loginId");
    }

    // 공통: 상태 코드 + {code, message} 본문으로 응답을 만든다
    private ResponseEntity<ApiError> error(HttpStatus status, String code, String message) {
        return ResponseEntity.status(status).body(new ApiError(code, message));
    }
}

/* 일단 과제 명세대로는 예외 8개 클래스를 만들라 해서 이렇게 만들었는데
CLEAN CODE 리팩토링을 해보자면 enum 함수를 적용해서 예외는 하나만 두는
방식이 상당히 좋을것 같다.
 */