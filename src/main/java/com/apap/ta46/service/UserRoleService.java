package com.apap.ta46.service;

import com.apap.ta46.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser (UserRoleModel user);
	public String encrypt (String password);
	//public void updatePassUser (UserRoleModel user, String pass_baru);
	//public boolean cekPass (UserRoleModel user, String pass_lama);

}
