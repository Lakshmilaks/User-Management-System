package com.User.ums.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException{
	private String message;

}
