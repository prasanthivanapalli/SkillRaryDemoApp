package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generiClibraies.BaseClass;
import generiClibraies.IconstantPath.IconstanPath;

public class CreateUserTest extends BaseClass {
	
	@Test
	
	public void createUserTest()
	{
		
		SoftAssert soft = new SoftAssert();
		home.clickUsersTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		
		users.clickNewButton();
		
		soft.assertEquals(addUser.getPageHeader(),"Add New User");
		
		Map<String,String> map = excel.readFromExcel("Sheet1", "Add User");
		addUser.createNewUser(map.get("Email"),map.get("Password"),
				map.get("FirstName"), map.get("LastName"), map.get("Address"),
				map.get("Contact Info"),map.get("photo"));
		
	

		soft.assertTrue(course.getSuccessMessage().contains("Success"));
		
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet1","Add Course", "Pass", IconstanPath.EXCEL_PATH);
		else
			excel.writeToExcel("Sheet1", "Add Course", "Fail", IconstanPath.EXCEL_PATH);
		
		soft.assertAll();
	}

}
