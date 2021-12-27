import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.logging.Logger;

public class Login {
    WebDriver driver ;
    Logger log;

    @BeforeClass
    public void init(){

        String url = "https://opensource-demo.orangehrmlive.com/";
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        log = Logger.getLogger(Login.class.getName());
    }

    @Test
    public void login(){

        String loginUserNameId = "txtUsername";
        String user = "Admin";
        String loginPasswordId = "txtPassword";
        String password = "admin123";
        String loginBtnId = "btnLogin";

        driver.findElement(By.id(loginUserNameId)).sendKeys(user);
        driver.findElement(By.id(loginPasswordId)).sendKeys(password);
        log.info("The password is set ");
        driver.findElement(By.id(loginBtnId)).click();
    }

    @Test
    public void mainMenu() throws InterruptedException {

        String menuAdminId = "menu_admin_viewAdminModule";
        String menuAdminUserMangementId = "menu_admin_UserManagement";
        String menuAdminUsersId = "menu_admin_viewSystemUsers";
        String addBtnId = "btnAdd";
        driver.findElement(By.id(menuAdminId)).click();
        driver.findElement(By.id(menuAdminUserMangementId)).click();
        driver.findElement(By.id(menuAdminUsersId)).click();
        driver.findElement(By.id(addBtnId)).click();

        log.info("Add button clicked");
        Thread.sleep(3000);
        }

        @Test(dependsOnMethods = {"login", "mainMenu"})
        public void addUser() throws InterruptedException {

            String userRoleDropDownId = "systemUser_userType";
            String userRole = "Admin";
            String employeeNameId = "systemUser_employeeName_empName";
            String employeeName = "Aaliyah Haq";
            String userNameId = "systemUser_userName";
            String userName = "Asad Ali12";
            String statusId = "systemUser_status";
            String statusText = "Enabled";
            String passwordId = "systemUser_password";
            String userPassword = "AsadAliKhan";
            String confirmPasswordId = "systemUser_confirmPassword";
            String userConfirmPassword = "AsadAliKhan";
            String saveButtonId ="btnSave";

            Select user_Role = new Select(driver.findElement(By.id(userRoleDropDownId)));
            user_Role.selectByVisibleText(userRole);

            driver.findElement(By.id(employeeNameId)).sendKeys(employeeName);
            driver.findElement(By.id(userNameId)).sendKeys(userName);

            log.info("--------------------------> User Name Added Successfully");

            Select status = new Select(driver.findElement(By.id(statusId)));
            status.selectByVisibleText(statusText);

            log.info("--------------------------> User Status Selected");
            driver.findElement(By.id(passwordId)).sendKeys(userPassword);
            driver.findElement(By.id(confirmPasswordId)).sendKeys(userConfirmPassword);

            log.info("--------------------------> Password Set Successfully");
            Thread.sleep(3000);
            driver.findElement(By.id(saveButtonId)).click();

            Thread.sleep(3000);
        }

    @AfterClass
    public void closeDriver(){
        driver.close();
        driver.quit();
    }
}
