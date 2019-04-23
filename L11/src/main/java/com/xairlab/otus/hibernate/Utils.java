package com.xairlab.otus.hibernate;

import com.xairlab.otus.hibernate.entity.AddressDataSet;
import com.xairlab.otus.hibernate.entity.PhoneDataSet;
import com.xairlab.otus.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    static int counter = 0;


    public static User createUser(long id, String name, int age, String street, List<String> phones) {

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);

        counter++;

        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setId(counter);
        addressDataSet.setStreet(street);
        addressDataSet.setUser(user);
        user.setAddressDataSet(addressDataSet);
        counter++;

        List<PhoneDataSet> phoneDataSetList = new ArrayList<>();
        for (String phone : phones) {
            PhoneDataSet phoneDataSet = new PhoneDataSet();
            phoneDataSet.setId(counter);
            phoneDataSet.setNumber(phone);
            phoneDataSet.setUser(user);
            phoneDataSetList.add(phoneDataSet);
            counter++;
        }
        user.setPhoneDataSet(phoneDataSetList);
        return user;
    }
}
