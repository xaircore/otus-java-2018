package com.xairlab.otus.hibernate;

import com.xairlab.otus.hibernate.entity.AddressDataSet;
import com.xairlab.otus.hibernate.entity.PhoneDataSet;
import com.xairlab.otus.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static User createUser(long id, String name, int age, String street, List<String> phones) {

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);

        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet(street);
        user.setAddressDataSet(addressDataSet);

        List<PhoneDataSet> phoneDataSetList = new ArrayList<>();
        for (String phone : phones) {
            PhoneDataSet phoneDataSet = new PhoneDataSet();
            phoneDataSet.setNumber(phone);
            phoneDataSetList.add(phoneDataSet);
        }
        user.setPhoneDataSet(phoneDataSetList);
        return user;
    }
}
