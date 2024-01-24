package com.User.ums.Utility;

import org.springframework.stereotype.Component;

import com.User.ums.responsedto.UserResponse;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseStructure<T> {
 private int status;
 private String message;
 private T data;
}
