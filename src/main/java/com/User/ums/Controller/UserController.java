package com.User.ums.Controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.User.ums.Entity.User;
import com.User.ums.Service.UserService;
import com.User.ums.Utility.ResponseStructure;
import com.User.ums.requestdto.UserRequest;
import com.User.ums.responsedto.UserResponse;

import io.swagger.annotations.ApiOperation;




import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Operation(description = "**Add password -**"
			+ " the API endpoint is used to add password to the user account after registration", responses = {
					@ApiResponse(responseCode = "200", description = "successfully add password to user", content = @Content(schema = @Schema(implementation = UserResponse.class))),
					@ApiResponse(responseCode = "404", description = "Failed to update password to user") })
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	
	@Operation(description = "**Update User -**"
			+ " the API endpoint is used to update the user data based on the userId", responses = {
					@ApiResponse(responseCode = "200", description = "user updated", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "400", description = "failed to update user") })
	@PutMapping("/user/{userId}")
	public  ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int userId,@RequestBody User user){
		return userService.updateUser(user,userId);
	}
	
	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> fetchAllUser() {
		return userService.fetchAllUser();
	}
	
	
	@Operation(description = "**Get user by userId and UserRole -**"
			+ " the API endpoint is used to fetch the user based on the userId and userRole", responses = {
					@ApiResponse(responseCode = "302", description = "user successfully fetched", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "user not found") }) 
	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(@PathVariable int userId) {
		return userService.fetchUserById(userId);
	}
	
	
	@Operation(description = "**Delete User -**"
			+ " the api endpoint is used to mark the user to be deleted", responses = {
					@ApiResponse(responseCode = "200", description = "user deleted", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "user not found") })
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
}

