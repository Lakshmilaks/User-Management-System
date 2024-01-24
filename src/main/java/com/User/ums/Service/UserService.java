package com.User.ums.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.User.ums.Entity.User;
import com.User.ums.Utility.ResponseStructure;
import com.User.ums.requestdto.UserRequest;
import com.User.ums.responsedto.UserResponse;
@Service
public interface UserService {
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(User user, int userid);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(int userId);

	public ResponseEntity<ResponseStructure<List<UserResponse>>> fetchAllUser();

}
