package com.Veri.Interface;

import com.Veri.bean.UploadBean;
import com.Veri.bean.Users;

public interface Interface {
	
	public int DataOwnerRegister(Users Dataownerregister);
	
	public int DataOwnerLogin(String email,String password);
	
	public int DataUserRegister(Users dataUserRegister);
	
	public int DataUserLogin(String email,String password);
	
	public int fileUpload(UploadBean upload);
	
	public String getpublickey(String filename);


}
