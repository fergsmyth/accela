package com.ferg.accelademo.usermanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter one of the following options an press enter:");
		System.out.println("1: Add a user");
		System.out.println("2: Update a user");
		System.out.println("3: Delete a user");
		System.out.println("4: Count users");
		System.out.println("5: List users");
		String input = scanner.nextLine();

	}
}
