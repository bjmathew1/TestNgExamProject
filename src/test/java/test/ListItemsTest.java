package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.ListItemsPage;
import util.BrowserFactory;

/*
Test 1: Validate a user is able to add a category 
and once the category is added it should display.

Test 2: Validate a user is not able to add a duplicated category. 
If it does then the following message will display: 
"The category you want to add already exists: <duplicated category name>."

Test 3: Validate the month drop down has all 
the months (jan, feb, mar ...) in the Due Date dropdown section.

*/
public class ListItemsTest {
	WebDriver driver;
	ListItemsPage listItemsPage;
		
	@BeforeMethod
	public void init() {	
		driver = BrowserFactory.init();
	}	

	@Test (priority=1)
	@Parameters({"categoryName"})
	public void validateAddCategoryItemIsVisibleOrNot(String categoryName) throws Exception {		
		listItemsPage = PageFactory.initElements(driver, ListItemsPage.class);
		
		listItemsPage.enterUniqueCategoryName(categoryName);
		listItemsPage.clickAddCategoryButton();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'TestNgExamBeena')]")));
		System.out.println("This validation is from Test1: ");
		listItemsPage.validateAddedCategoryLinkExists(categoryName);	
	}
	
	@Test (priority=2)
	@Parameters({"categoryName"})
	public void validateCategoryCannotBeDuplicated(String categoryName) throws Exception {	
		listItemsPage = PageFactory.initElements(driver, ListItemsPage.class);
		
		listItemsPage.enterGenericCategoryName(categoryName);
		listItemsPage.clickAddCategoryButton();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'TestNgExamBeena')]")));
		
		System.out.println("This is the validation from Test2: ");
		listItemsPage.validateAddedCategoryLinkExists(categoryName);
		
		try {
			Thread.sleep(3000);
		listItemsPage.enterGenericCategoryName(categoryName);
		listItemsPage.clickAddCategoryButton();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Category Item Cannot Be Added Again. This Category Name Already Exists");		
		}
		System.out.println("This validation is from Test2: ");
		listItemsPage.validateDuplicateCategoryPage();	
	}
	
	@Test (priority=3)
	@Parameters({"monthName"})
	public void validateMonthDropDownOptions(String monthName) throws Exception {	
		listItemsPage = PageFactory.initElements(driver, ListItemsPage.class);
		
		listItemsPage.selectDueMonthDropdownVisibility();			
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}		
}