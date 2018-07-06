package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.Users;
import ua.com.proteins.service.UsersService;

public class UsersEditor extends PropertyEditorSupport {

	 
	
	public UsersEditor(UsersService usersService) {
		this.usersService = usersService;
	}

	private final UsersService usersService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Users users = usersService.findOne(Integer.valueOf(text));
		setValue(users);
	}
	
	
	
}
