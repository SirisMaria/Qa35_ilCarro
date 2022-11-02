package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {


@DataProvider
public Iterator<Object[]> datalogin(){

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"mashka77@gmail.com","mashkA12345$5"});
        list.add(new Object[]{"mashapetrova77@gmail.com","mashkA12345%6"});
        list.add(new Object[]{"mashkavasina77@gmail.com","mashkA12345$7"});


        return list.iterator();

        }

    @DataProvider
    public Iterator<Object[]> XXX(){
        List<Object[]> list = new ArrayList<>();


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> regDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
        //list.add(new Object[]{new User().withName()})
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ilcarro.csv")));
        String line =reader.readLine();  // Mashka Vasina,Ivanov,mashka@gmail.com,mashkA12345$5"
        while (line!=null){
            String [] split =line.split(",");  //  ["Mashka Vasina"] ["Ivanov"] ["mashka@gmail.com"] ["mashkA12345$5"]
            list.add(new Object[]{new User().withName(split[0]).withLastname(split[1]).withEmail(split[2]).withPassword(split[3])});
            line =reader.readLine();  // null
        }


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataModelUser(){

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"mashka@gmail.com","mashkA12345$5"});
        list.add(new Object[]{"mashapetrova@gmail.com","mashkA12345%6"});
        list.add(new Object[]{"mashkavasina@gmail.com","mashkA12345$7"});

        return list.iterator();
    }



}