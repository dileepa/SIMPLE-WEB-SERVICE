package com.dileepa.impl;

import dileepa.com._2015._05.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * USER : Dileepa
 * DATE : 5/24/15
 * TIME : 6:54 PM
 */

@WebService(name = "UserManagementService", targetNamespace = "http://dileepa/com/2015/05/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ObjectFactory.class
})
public class UserManagerServiceImpl implements UserManagementService
{
    @Override
    @WebMethod(operationName = "GetAllUsers", action = "/GetAllUsers")
    @WebResult(name = "getAllUsersResponse", targetNamespace = "http://dileepa/com/2015/05/", partName = "parameters")
    public GetAllUsersResponse getAllUsers( @WebParam(name = "getAllUsersRequest", targetNamespace = "http://dileepa/com/2015/05/", partName = "parameters")
                                                GetAllUsersRequest parameters)
    {
        List userList = getUserList();

        GetAllUsersResponse getAllUsersResponse = new GetAllUsersResponse();
        getAllUsersResponse.getUserList().addAll(userList);

    return  getAllUsersResponse;

    }

    @WebMethod(operationName = "SearchUsers", action = "/SearchUsers")
    @WebResult(name = "searchUsersResponse", targetNamespace = "http://dileepa/com/2015/05/", partName = "parameters")
    public SearchUsersResponse searchUsers(
            @WebParam(name = "searchUsersRequest", targetNamespace = "http://dileepa/com/2015/05/", partName = "parameters")
            SearchUsersRequest parameters)


    {
        UserSearchCriteria userSearchCriteria = parameters.getUserSearchCriteria();

        List searchUserList = new ArrayList<User>();

        List userList = getUserList();
        for (User user  : (ArrayList<User>)userList)
        {
            if (user.getStatus().name().equals(userSearchCriteria.getStatus()) &&
                    user.getCity().equals(userSearchCriteria.getCity()))
            {
                searchUserList.add(user);
            }
        }

        SearchUsersResponse searchUsersResponse = new SearchUsersResponse();
        searchUsersResponse.getUserList().addAll(searchUserList);
        return searchUsersResponse;

    }

    private List getUserList() {
        List userList = new ArrayList<User>();

        User user1 = new User();
        user1.setId(1);
        user1.setUsername("dileepa");
        user1.setCity("matara");
        user1.setStatus(UserStatus.ACCEPTED);

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("anjula");
        user2.setCity("narammala");
        user2.setStatus(UserStatus.INVALID);

        User user3 = new User();
        user3.setId(3);
        user3.setUsername("rachitha");
        user3.setCity("mathugama");
        user3.setStatus(UserStatus.BLOCKED);

        User user4 = new User();
        user4.setId(4);
        user4.setUsername("roshan");
        user4.setCity("hikkaduwa");
        user4.setStatus(UserStatus.ACCEPTED);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        return userList;
    }
}
