import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            User user = new User().withEmail("mashka@gmail.com").withPassword("mashkA12345$5");
            app.getHelperUser().login(user);
            logger.info("The login was needed with user : " +user.toString());

        }
    }

    @Test(groups = {"smoke"})
    public void addCarSuccess(){

        Random random = new Random();
        int i = random.nextInt(1000)+100;



        Car car = Car.builder()
                .location("Haifa,Israel")
                .make("BMW")
                .model("M6")
                .year("2019")
                .engine("2")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("4")
                .seats("7")
                .clasS("C")
                .fuelConsumption("66")
                .serialNumber("12-9782"+i)
                .price("60")
                .distanceInclued("900")
                .featurs("Type of featurs")
                .about("no smoking")
                .build();
        logger.info("Test start with data --->" +car.toString());

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("E:\\images.jpg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
        logger.info("In assert checked message 'Car added' in dialog  ");

    }
    @Test(dataProvider = "carValidData",dataProviderClass = DataProviderCar.class)
    public void addCarSuccessDP(Car car) {
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        car.setSerialNumber("112-53" +i);

        logger.info("Test start with data --->" + car.toString());
        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("E:\\images.jpg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Car added");
        logger.info("In assert checked message 'Car added' in dialog  ");
    }
        @AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.helperCar().returnToHomePage();
    }

}
