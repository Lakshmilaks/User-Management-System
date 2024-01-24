package com.User.ums.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.User.ums.Entity.User;
import com.User.ums.Exception.UserNotFoundByIdException;
import com.User.ums.Repository.UserRepository;
import com.User.ums.Service.UserService;
import com.User.ums.Utility.ResponseStructure;
import com.User.ums.requestdto.UserRequest;
import com.User.ums.responsedto.UserResponse;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder encode;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ResponseStructure responseStructure;

	private User mapToUser(@RequestBody UserRequest request) {
		return User.builder().userName(request.getUsername()).userEmail(request.getEmail())
				.userPassword(encode.encode(request.getPassword())).build();

	}
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userid(user.getUserId()).username(user.getUserName()).email(user.getUserEmail())
				.build();
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {

		User user = userRepo.save(mapToUser(userRequest));
//		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("user saved sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(User updateduser, int userid) {
		User user = userRepo.findById(userid).map(u -> {
			updateduser.setUserId(userid);
			return userRepo.save(updateduser);
		}).orElseThrow(() -> new UserNotFoundByIdException("No User Found !!"));
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user updated sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> fetchAllUser() {
		List<User> userList=userRepo.findAll();
		if(userList==null)
			throw new UserNotFoundByIdException("No User Found !!");
		List<UserResponse> userResponseList= userList.stream()
                .map(user -> mapToUserResponse(user))
                .collect(Collectors.toList());
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found sucessfully");
		responseStructure.setData(userResponseList);
		return new ResponseEntity<ResponseStructure<List<UserResponse>>>(responseStructure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("No User Found !!") );
		userRepo.delete(user);
//		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user Deleted sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("No User Found !!") );
		
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
	}

}
