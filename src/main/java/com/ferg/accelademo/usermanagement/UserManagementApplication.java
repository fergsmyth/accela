package com.ferg.accelademo.usermanagement;

import com.ferg.accelademo.usermanagement.exception.CommandArgumentException;
import com.ferg.accelademo.usermanagement.service.UserService;
import com.ferg.accelademo.usermanagement.service.command.CommandService;
import com.ferg.accelademo.usermanagement.service.command.CommandType;
import com.ferg.accelademo.usermanagement.validator.UserArgumentValidator;
import com.ferg.accelademo.usermanagement.validator.UserXMLValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	private UserService userService;
	private UserXMLValidator userXMLValidator;
	private UserArgumentValidator userArgumentValidator;
	private final Map<String, CommandService> commandServiceMap;
	private Logger logger = LoggerFactory.getLogger(UserManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	public UserManagementApplication(UserService userService, UserXMLValidator userXMLValidator, UserArgumentValidator userArgumentValidator, Map<String, CommandService> commandServiceMap) {
		this.userService = userService;
		this.userXMLValidator = userXMLValidator;
		this.userArgumentValidator = userArgumentValidator;
		this.commandServiceMap = commandServiceMap;
	}

	@Override
	public void run(String... args) {
		if(args.length > 0){
			String firstArg = args[0];
			Scanner scan = new Scanner(firstArg.trim());
			if(scan.hasNextInt()){
				int selectOption = scan.nextInt();
				CommandType commandType = CommandType.commandOptionToCommandTypeMappings().get(selectOption);
				if(commandType != null){
					try {
						commandServiceMap.get(commandType.name()).execute();
					} catch (CommandArgumentException e) {
						logger.error(e.get);
					}
				} else {
					logger.error("No command found for option %d", selectOption);
					displayOptions();
				}
				switch (selectOption) {
					case 1:
						System.out.println("Selected option: Add user");
						if(userArgumentValidator.validateCreateUser(args)) {
							Long userId = Long.parseLong(args[1]);
							System.out.println(String.format("Creating user with id %d", userId));
							userService.createUser(userId, args[2], args[3]);
						}
						break;
					case 2:
						System.out.println("Selected option: Update user");
						if(userArgumentValidator.validateUpdateUser(args)) {
							Long userId = Long.parseLong(args[1]);
							System.out.println(String.format("Updating user with id %d", userId));
							userService.updateUser(userId, args[2], args[3]);
						}
						break;
					case 3:
						System.out.println("Selected option: Delete user");
						if(userArgumentValidator.validateDeleteUser(args)) {
							long userId = Long.parseLong(args[1]);
							System.out.println(String.format("Deleting user with id %d", userId));
							userService.deleteUser(userId);
						}
						break;
					case 4:
						System.out.println("Selected option: Count users");
						if (userArgumentValidator.validateListUsers(args)) {
							System.out.println("Retrieving number of users");
							userService.getUserCount();
						}
						break;
					case 5:
						System.out.println("Selected option: List users");
						if (userArgumentValidator.validateCountUsers(args)) {
							System.out.println("Retrieving list of users");
							userService.getUsersList();
						}
						break;
					case 6:
						System.out.println("Selected option: Add user via xml");
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
