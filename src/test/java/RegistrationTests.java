import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {


    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logout();
    }

    @Test(dataProvider = "regDataValid",dataProviderClass = DataProviderUser.class,enabled = false)
    public void registrationSuccessDP(User user) {

       logger.info("Registration scenario success was used data"+user.toString());
     //   User user = new User().withName("Vova").withLastname("Vovin").withEmail("vova1"+i+"@gmail.com").withPassword("Vova123$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
        logger.info("In assert checked massage 'Registered' in dialog ");

    }

    public void  registrationSuccess(){
        System.out.println( System.currentTimeMillis());
        int i =(int) (System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("Vova").withLastname("Vovik").withEmail("vova11"+i+"@gmail.com").withPassword("VovA123$5");
        logger.info("Registration  scenario success was used data"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
        logger.info("In assert checked message 'Registered' in dialog  ");

    }
    @Test(groups = {"smoke"})
    public void registrationWrongPassword() {

        User user = new User().withName("Vova").withLastname("Vovin").withEmail("vova1@gmail.com").withPassword("Vov3$5");
        logger.info("Registration  negative scenario with wrong password was used data"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submitWithoutWait();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("The Y'alla button was not active");
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}


