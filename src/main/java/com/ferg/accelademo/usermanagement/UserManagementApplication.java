package com.ferg.accelademo.usermanagement;

import com.ferg.accelademo.usermanagement.entity.UserEntity;
import com.ferg.accelademo.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

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
						userService.createUser(args);
						break;
					case 2:
						userService.updateUser(args);
						break;
					case 3:
						userService.deleteUser(args);
						break;
					case 4:
						userService.getUserCount(args);
						break;
					case 5:
						userService.getUsersList(args);
						break;
					default:
						System.out.println(String.format("Selected option %d is not a valid option.", selectOption));
				}
			} else {
				System.out.println("First argument must be an integer");
			}
		} else {
			System.out.println("*********");
			System.out.println("*********");
			System.out.println("No arguments provided. Check out the README for available options.");
			System.out.println("*********");
			System.out.println("*********");
		}

	}

}
