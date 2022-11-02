import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LoginTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()) {
            //if(app.getHelperUser().isElementPresent(By.xpath("//a"))){
            app.getHelperUser().logout();
            logger.info("The logout was needed ");
        }

}
    @Test (dataProvider = "datalogin",dataProviderClass = DataProviderUser.class,enabled = false)
    public void loginSuccess(String email,String password) {

        logger.info("Login scenario success was used data email: " +email+" & password: " +password);

//        String email = "mashka@gmail.com";
//        String password = "mashkA12345$5";


        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email,password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("In assert checked message 'Logged in success' in dialog  ");


    }
    @Test(dataProvider = "dataModelUser",dataProviderClass = DataProviderUser.class,enabled = false)
    public void loginSuccessModelsDP(User user){

        logger.info("Login scenario success was used data"+user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("In assert checked message 'Logged in success' in dialog  ");
    }

    @Test(groups = {"smoke"})
    public void loginSuccessModels() {
        User user = new User().withEmail("mashka@gmail.com").withPassword("mashkA12345$5");
        logger.info("Login scenario success was used data"+user.toString());

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("In assert checked message 'Logged in success' in dialog  ");
    }

    @Test
    public void negativeWrongEmail() {
        User user = new User().withEmail("mashkagmail.com").withPassword("mashkA12345$5");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitWithoutWait();
        //Assert errorMessage
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        //Assert buttonYalla not active
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());


    }

    @Test
    public void negativeWrongPassword() {
        User user = new User().withEmail("mashka@gmail.com").withPassword("mashkA1345$5");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitWithoutWait();
        //text message"Autorization error"
        Assert.assertEquals(app.getHelperUser().getMessage(), "Wrong email or password");
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Authorization error");

    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){

        app.getHelperUser().clickOkButton();

    }
}

