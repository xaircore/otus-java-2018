package com.xairlab.otus.hibernate;

import com.xairlab.otus.hibernate.entity.AddressDataSet;
import com.xairlab.otus.hibernate.entity.PhoneDataSet;
import com.xairlab.otus.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static User createUser(String name, int age, String street, List<String> phones) {

        User user = new User();
        user.setName(name);
        user.setAge(age);

        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet(street);
        addressDataSet.setUser(user);
        user.setAddressDataSet(addressDataSet);

        List<PhoneDataSet> phoneDataSetList = new ArrayList<>();
        for (String phone : phones) {
            PhoneDataSet phoneDataSet = new PhoneDataSet();
            phoneDataSet.setNumber(phone);
            phoneDataSet.setUser(user);
            phoneDataSetList.add(phoneDataSet);
        }
        user.setPhoneDataSet(phoneDataSetList);
        return user;
    }
}
