package com.ferg.accelademo.usermanagement;

import com.ferg.accelademo.usermanagement.service.UserService;
import com.ferg.accelademo.usermanagement.validator.UserArgumentValidator;
import com.ferg.accelademo.usermanagement.validator.UserXMLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private UserXMLValidator userXMLValidator;

	@Autowired
	private UserArgumentValidator userArgumentValidator;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(args.length > 0){
			String firstArg = args[0];
			Scanner scan = new Scanner(firstArg.trim());
			if(scan.hasNextInt()){
				int selectOption = scan.nextInt();
				switch (selectOption) {
					case 1:
						if(userArgumentValidator.validateCreateUser(args)) {
							Long userId = Long.parseLong(args[1]);
							System.out.println(String.format("Creating user with id %d", userId));
							userService.createUser(userId, args[2], args[3]);
						}
						break;
					case 2:
						if(userArgumentValidator.validateUpdateUser(args)) {
							Long userId = Long.parseLong(args[1]);
							System.out.println(String.format("Updating user with id %d", userId));
							userService.updateUser(userId, args[2], args[3]);
						}
						break;
					case 3:
						if(userArgumentValidator.validateDeleteUser(args)) {
							long userId = Long.parseLong(args[1]);
							System.out.println(String.format("Deleting user with id %d", userId));
							userService.deleteUser(userId);
						}
						break;
					case 4:
						if (userArgumentValidator.validateListUsers(args)) {
							System.out.println("Retrieving number of users");
							userService.getUserCount();
						}
						break;
					case 5:
						if (userArgumentValidator.validateCountUsers(args)) {
							System.out.println("Retrieving list of users");
							userService.getUsersList();
						}
						break;
					case 6:
						if(userXMLValidator.validateUserXML(args)){
							String filePath = args[1].trim();
							System.out.println(String.format("Adding user from xml %s", filePath));
							userService.createUserFromXML(filePath);
						}
						break;
					default:
						// Add help output here as default case
						displayOptions();
				}
			} else {
				displayOptions();
			}
		} else {
			displayOptions();
		}

	}

	private void displayOptions() {
		System.out.println("Enter an option between 1 and 5, passing the correct arguments separated by a space.");
		System.out.println("Add user			: 1 userid firstname lastname");
		System.out.println("Update user			: 2 userid firstname lastname");
		System.out.println("Delete user			: 3 userid");
		System.out.println("Count users			: 4");
		System.out.println("List users			: 5");
		System.out.println("Add user(XML)		: 6 filepath/file.xml");
	}

}
