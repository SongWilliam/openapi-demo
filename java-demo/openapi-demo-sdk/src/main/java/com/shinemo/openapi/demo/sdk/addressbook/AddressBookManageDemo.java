package com.shinemo.openapi.demo.sdk.addressbook;

import com.shinemo.openapi.demo.sdk.factory.OpenapiClientFactory;
import com.shinemo.openapi.java.sdk.client.OpenapiClient;
import com.shinemo.openapi.java.sdk.exception.OpenapiException;
import com.shinemo.openapi.java.sdk.request.AddressBookDeptManageRequest;
import com.shinemo.openapi.java.sdk.request.AddressBookUserManageRequest;
import com.shinemo.openapi.java.sdk.response.AddressBookManageResponse;

public class AddressBookManageDemo {
    public static void main(String[] args) {
        // 获取部门列表
        AddressBookDeptManageRequest request = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().getDeptList(0L).build();
        // 创建部门
        AddressBookDeptManageRequest request1 = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().createDept("测试部门", 28L).build();
        // 修改部门
        AddressBookDeptManageRequest request2 = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().updateDept("测试部门", 28L, 36L).build();
        // 删除部门
        AddressBookDeptManageRequest request3 = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().deleteDept(36L).build();
        // 获取部门下所有人员
        AddressBookUserManageRequest request4 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().getUserList(3L).build();
        // 新增人员
        AddressBookUserManageRequest request5 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().createUser(3L, "15158114270", "测试小号").build();
        // 修改人员基础信息
        AddressBookUserManageRequest request6 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().updateUserBasicInfo(3L, 126687464L, "15158114271", "测试小号更名").build();
        // 修改人员所在部门
        AddressBookUserManageRequest request7 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().updateUserDept(32L, 3L, 126687464L).build();
        // 删除用户
        AddressBookUserManageRequest request8 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().deleteUser(126687464L, 32L).build();
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
            AddressBookManageResponse response = client.execute(request8, "MDAwMEZENEQ4RUQyOTRDNjJFQjAxMkY1RkI2NDVBQjU4RjQ1OTBFN0MxNTU1REU5NjUxNjJGNEY1QTkwMjAwQjgwRjBDNkIwQUU3NUIxRkU3OTZCMjA2RDc3RUI4OEZFQzlFNw==");
            System.out.println(response.toString());
        } catch (OpenapiException e) {
            e.printStackTrace();
        }
    }
}
