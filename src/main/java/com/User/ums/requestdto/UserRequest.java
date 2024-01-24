package com.User.ums.requestdto;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	@jakarta.validation.constraints.NotNull(message = "user cannot be null")
	private String username; 

	@NotBlank(message = "email cannot be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	@NotBlank(message = "password is required")
	@jakarta.validation.constraints.NotNull(message="password is required")
	@Size(min =8,max=20, message = "password must be between 8 and 20 chharacters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
//	@Pattern(regexp="\\\\b[a-zA-Z]")
	private String password;
}
