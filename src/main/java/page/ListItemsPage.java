package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
public class ListItemsPage extends BasePage {
	
	WebDriver driver;
	public ListItemsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By toggleCheckBox = By.cssSelector("input[name='allbox']");
	By addItemField = By.cssSelector("input[name='data']");
	By addItemButton= By.cssSelector("input[value='Add']");
	By addCategoryField = By.cssSelector("input[name='categorydata']");
	By addCategoryButton= By.cssSelector("input[value='Add category']");
	By listItemCheckBox = By.cssSelector("input[name='todo[0]']");
	By removeItemButton = By.cssSelector("input[value='Remove']");
	By listedCategoryItemLink = By.cssSelector("a[href='delcat.php?cid=2000']");
	By addedCategoryItemName= By.xpath("//span[contains(text(),'TestNgExamBeena')]");
	By duplicateCategoryPageTitleFirst= By.xpath("////body/text()[1]");
	By duplicateCategoryPageTitle = By.xpath("//span[1][contains(text(),'TestNgExamBeena')]");
	By dueMonthDropdown = By.xpath("//select[@name='due_month']");
	By addCategoryDropdown = By.xpath("//select[@name='category']");
	
	String beforeItemNum = "input[name='todo[";
	String afterItemNum = "]']";
	String beforeDropdownMonthName ="//select[3]/option[";
	String afterDropdownMonthName ="]";
	String selectedMonthName;
	String beforeCategoryItem = "//select[1]/option[";
	String afterCategoryItem = "]']";

	public void selectDueMonthDropdown(String monthName) {
		WebElement dropdownField=driver.findElement(dueMonthDropdown);
		selectFromDropdown(dropdownField,monthName);
	}
	
	public void selectDueMonthDropdownVisibility() {
	for (int i=1;i<=13;i++) {
		String selectedMonthName = driver.findElement(By.xpath(beforeDropdownMonthName+i+afterDropdownMonthName)).getText();
		System.out.println(selectedMonthName);	
		}
	}
	
	public String getInsertedCategoryName() {
		return insertedCategoryName;
	}
	
	static String insertedCategoryName;
	public void insertedCategoryName(String categoryName) {
		insertedCategoryName = categoryName+generateRandomNum(999);
		driver.findElement(addCategoryField).sendKeys(insertedCategoryName);
	}

	public void clickToggleButton() {
		driver.findElement(toggleCheckBox).click();
	}
	
	public void enterItemName(String itemName) {
		driver.findElement(addItemField).sendKeys(itemName+generateRandomNum(99));
	}
	
	public void clickAddItemButton() {
		driver.findElement(addItemButton).click();
	}
	
	public void enterUniqueCategoryName(String categoryName) {
		driver.findElement(addCategoryField).sendKeys(categoryName+generateRandomNum(99));
	}
	
	public void enterGenericCategoryName(String categoryName) {
		driver.findElement(addCategoryField).sendKeys(categoryName);
	}
	
	public void clickAddCategoryButton() {
		driver.findElement(addCategoryButton).click();
	}
	
	public void clickListItemCheckBox() {
		driver.findElement(listItemCheckBox).click();
	}
	
	public void clickRemoveItemButton() {
		driver.findElement(removeItemButton).click();
	}
	
	public void clickAddedCategoryLink() {
		driver.findElement(addedCategoryItemName).click();
	}
	
	public void validateAddedCategoryLinkExists(String categoryName) {
		String addedCategoryItemNameText = driver.findElement(addedCategoryItemName).getText();
		if (addedCategoryItemNameText.contains(categoryName)) {
			System.out.println("Added Category Item Is Visible");
		}
			else {System.out.println("Added Category Item Does Not Exist");			
			}
		}
		
	public void validateDuplicateCategoryPage() {
		boolean duplicateCategoryPageTitleText2 = driver.findElement(duplicateCategoryPageTitle).isDisplayed();
		if (duplicateCategoryPageTitleText2 != true) {
			System.out.println("Page Cannot Be Found");
		}
			else {
				System.out.print("The category you want to add already exists: ");
				System.out.println(driver.findElement(duplicateCategoryPageTitle).getText());
			}	
		}

	public void removeAllPreviousItems() {
		driver.findElement(toggleCheckBox).click();
		driver.findElement(removeItemButton).click();		
	}
	
	public void addCategoryToPage(int num) {	
		enterUniqueCategoryName("categoryName");
		driver.findElement(addCategoryButton).click();
		}

	public void addMultipleCategoriesToPage() {
		for (int i=0; i<2;i++) {
			addCategoryToPage(i);
		}
	}
		
	public boolean isElementPresent() {
		try {
			return 	driver.findElement(listItemCheckBox).isDisplayed();
		}catch (NoSuchElementException e) {
			System.out.println("Single selected item has been successfully removed. Item does not exist.");
		return false;
		}	
	}	
}

	

