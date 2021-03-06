package auto.test.login;

import auto.base.BaseCases;
import auto.base.CaseDataProvider;
import auto.pojo.Locator;
import auto.pojo.LoginFailData;
import auto.pojo.LoginSuccessData;
import auto.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


/**
 * 痛点：1.元素定位方式和值的维护比较麻烦---》分离
 *       2.api侵入：by
 * @author ZS
 * @Description:
 * @date 2020/4/8 23:59
 */
public class LoginTest extends BaseCases {


    @Test(dataProvider = "getAllCaseData", dataProviderClass = CaseDataProvider.class)
    public void login_fail_case(LoginFailData loginFailData) {
        to("login_url");
        send("登录页面","手机号",loginFailData.getPhone());
        send("登录页面","密码",loginFailData.getPassword());
        click("登录页面","登录按钮");
        String actualTips = getTipsNotNull("登录页面","提示Tips");

        System.out.println(actualTips);
        Assert.assertEquals(actualTips, loginFailData.getExpectedTips());
    }

    @Test(dataProvider = "getAllCaseData", dataProviderClass = CaseDataProvider.class)
    public void login_success_case(LoginSuccessData loginSuccessData) {
        to("login_url");
        send(By.id("mobilephone"), loginSuccessData.getPhone());
        send(By.id("password"), loginSuccessData.getPassword());
        click(By.id("login"));
        //2.智能等待
        Assert.assertTrue(currentPageUrlContains(loginSuccessData.getPartialUrl()));
    }

    public static void main(String[] args) throws InterruptedException {
        /*String browserName = "Chrome";
        String seleniumVersion = "3.X";
        WebDriver driver = WebAutoUtils.getDriver(browserName, seleniumVersion);
        Thread.sleep(2000);
        driver.quit();*/

    }

}
