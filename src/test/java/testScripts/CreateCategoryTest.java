package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generiClibraies.BaseClass;
import generiClibraies.IconstantPath.IconstanPath;

public class CreateCategoryTest  extends BaseClass{
	
	@Test
	public void createCategoryTest() throws InterruptedException
	{
		
		SoftAssert  soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
		category.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
	
		
		Map<String,String> map = excel.readFromExcel("Sheet1", "Add Category");
		String categotyName = map.get("Name")+jutil.generateRandomNum(100);
		addCategory.setName(map.get("categoryName"));
		addCategory.clickSave();
		
		Assert.assertTrue(category.getSuccessMessage().contains("Success"));
		
		boolean isPresent = false;
		List<WebElement> categoryList = category.getCategoryList();
		for (WebElement e : categoryList)
		{
			if(e.getText().equals(categotyName))
			{
				isPresent = true;
				break;
			}
		}
		
		soft.assertTrue(isPresent);
		
		category.clickDeleteButton(categotyName,driver);
		category.clickDelete();
		Assert.assertTrue(category.getSuccessMessage().contains("Success"));
		
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet1","Add Course", "Pass", IconstanPath.EXCEL_PATH);
		else
			excel.writeToExcel("Sheet1", "Add Course", "Fail", IconstanPath.EXCEL_PATH);
		
		
		
		soft.assertAll();
		
		
		//List<WebElement> categoryList = category.getCategoryList();
		
	}
	
	

}
