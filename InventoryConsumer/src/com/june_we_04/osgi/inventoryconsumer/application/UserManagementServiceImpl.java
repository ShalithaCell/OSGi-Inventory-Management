package com.june_we_04.osgi.inventoryconsumer.application;

import java.util.Optional;
import java.util.Scanner;

import com.june_we_04.osgi.authservice.model.User;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IUserManagementService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;

public class UserManagementServiceImpl implements IUserManagementService{
	
	private IDisplayService displayService;
	private Scanner scanner;
	
	public UserManagementServiceImpl() {
		displayService =  new DisplayServiceImpl();
		scanner = new Scanner(System.in);
	}

	@Override
	public void RegisterNewUserAccount() {
		boolean success = false;
		
		do {
			displayService.Display("----Creating User Account with the Create User Wizard----");
			displayService.DisplayLineBreak();
			
			displayService.InLineDisplay("Enter your user name : ");
			String userName = scanner.next();
			
			if(userName.isEmpty()) {
				displayService.Display("Invalid user name");
				displayService.DisplayLineBreak();
				continue;
			}
			
			displayService.InLineDisplay("Enter your password : ");
			String password = scanner.next();
			
			if(password.isEmpty()) {
				displayService.Display("Invalid password");
				displayService.DisplayLineBreak();
				continue;
			}
			
			if(password.chars().count() <= 5) {
				displayService.Display("Invalid password. Password must be at least 6 characters long.");
				displayService.DisplayLineBreak();
				continue;
			}
			
			// get the user service
			var authService = ServiceRegister.getInstance().GetAuthService();
			
			User user = new User();
			user.setName(userName);
			user.setPassword(password);
			user.setStoreID(InMemoryDb.getInstance().getStore().getStoreID());
			
			user = authService.RegisterUser(user);
			
			displayService.DisplayLineBreak();
			displayService.Display("USer Account Registered sucessfully.");
			displayService.Display("User ID   :  = " + user.getUserID());
			displayService.Display("User Name :  = " + user.getName());
			displayService.Display("Store ID  :  = " + user.getStoreID());
			displayService.DisplayLineBreak();
			
			success = true;
			
		} while (!success);
	}

	@Override
	public boolean Login() {
		
		boolean success = false;
		
		do {
			displayService.Display("----Login----");
			displayService.DisplayLineBreak();
			
			displayService.InLineDisplay("Enter your user ID (Enter 99 to Exit): ");
			int userID = scanner.nextInt();
			
			displayService.InLineDisplay("Enter your user password : ");
			String password = scanner.next();
			
			if(userID == 99) {
				InMemoryDb.getInstance().SetActionID(ApplicationConst.START_WIZARD_CODE);
				return false;
			}
			
			// get the user service
			var authService = ServiceRegister.getInstance().GetAuthService();
			
			success = authService.Authenticate(userID, password);
			
			if(!success) {
				displayService.Display("UserID or Password is incroreect !");
				displayService.DisplayLineBreak();
				continue;
			}
			
			displayService.Display("Suceessfully Logged IN !!");
			displayService.DisplayLineBreak();
			
			success = true;
			
			Optional<User> oUser = authService.GetUser(userID, password);
			User user = oUser.get();
			
			InMemoryDb.getInstance().setUser(user);
			
		} while (!success);
		
		return success;
	}

	@Override
	public void ResetPassword() {
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter your old password : ");
		String password = scanner.next();
		
		// get the user service
		var authService = ServiceRegister.getInstance().GetAuthService();
		
		boolean success = authService.Authenticate(InMemoryDb.getInstance().getUser().getUserID(), password);
		
		if(!success) {
			displayService.Display("Password is Incorrect !");
			displayService.DisplayLineBreak();
			
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
			return;
		}
		
		displayService.InLineDisplay("Enter your new password : ");
		String newPassword = scanner.next();
		
		authService.ResetPassword(InMemoryDb.getInstance().getUser().getUserID(), newPassword);
		
		displayService.DisplayLineBreak();
		displayService.Display("Password is reset successfully. Please login again");
		
		InMemoryDb.getInstance().setUser(null);
		InMemoryDb.getInstance().SetActionID(ApplicationConst.START_WIZARD_CODE);
		
	}

	@Override
	public void ChangeUserName() {
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter your old password : ");
		String password = scanner.next();
		
		// get the user service
		var authService = ServiceRegister.getInstance().GetAuthService();
		
		boolean success = authService.Authenticate(InMemoryDb.getInstance().getUser().getUserID(), password);
		
		if(!success) {
			displayService.Display("Password is Incorrect !");
			displayService.DisplayLineBreak();
			
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
			return;
		}
		
		displayService.InLineDisplay("Enter your new user name : ");
		String username = scanner.next();
		
		authService.ChangeUserName(InMemoryDb.getInstance().getUser().getUserID(), username);
		
		displayService.DisplayLineBreak();
		displayService.Display("Username changed successfully.");
		
		InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_USER_MANAGEMENT);
	}

	@Override
	public void RemoveUser() {
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter your old password : ");
		String password = scanner.next();
		
		// get the user service
		var authService = ServiceRegister.getInstance().GetAuthService();
		
		boolean success = authService.Authenticate(InMemoryDb.getInstance().getUser().getUserID(), password);
		
		if(!success) {
			displayService.Display("Password is Incorrect !");
			displayService.DisplayLineBreak();
			
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_USER_MANAGEMENT);
			return;
		}
		
		displayService.Display("Are you sure want to remove user '" + InMemoryDb.getInstance().getUser().getName() + "' ?");
		displayService.Display("1. Yes");
		displayService.Display("2. No");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		int action = scanner.nextInt();
		
		if(action != 1) {
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_USER_MANAGEMENT);
			return;
		}
		
		authService.RemoveUser(InMemoryDb.getInstance().getUser().getUserID());
		
		displayService.DisplayLineBreak();
		displayService.Display("User remove successfully !");
		
		InMemoryDb.getInstance().setUser(null);
		InMemoryDb.getInstance().SetActionID(ApplicationConst.START_WIZARD_CODE);
		
	}

}
