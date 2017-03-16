package com.shinemo.openapi.demo.sdk.addressbook;

import com.shinemo.openapi.demo.sdk.factory.OpenapiClientFactory;
import com.shinemo.openapi.java.sdk.client.OpenapiClient;
import com.shinemo.openapi.java.sdk.exception.OpenapiException;
import com.shinemo.openapi.java.sdk.request.AddressBookDeptManageRequest;
import com.shinemo.openapi.java.sdk.request.AddressBookUserManageRequest;
import com.shinemo.openapi.java.sdk.response.AddressBookManageResponse;

public class AddressBookManageDemo {
    public static void main(String[] args) {
        // 获取部门列表:getDeptList(Long deptId:"部门id")
        AddressBookDeptManageRequest request = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().getDeptList(0L).build();
        // 创建部门:createDept(String name:"部门名称", Long parentid:"父部门id")
        AddressBookDeptManageRequest request1 = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().createDept("测试部门", 28L).build();
        // 修改部门:updateDept(String name:"部门名", Long parentid:"父部门id", Long id:"当前部门id")
        AddressBookDeptManageRequest request2 = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().updateDept("测试部门", 28L, 36L).build();
        // 删除部门:deleteDept(Long id:"当前部门id")
        AddressBookDeptManageRequest request3 = new AddressBookDeptManageRequest.AddressBookDeptManageBuilder().deleteDept(36L).build();
        // 获取部门下所有人员:getUserList(Long deptId:"部门id")
        AddressBookUserManageRequest request4 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().getUserList(3L).build();
        // 新增人员:createUser(Long deptId:"用户所在部门id", String mobile:"用户手机号", String name:"用户名")
        AddressBookUserManageRequest request5 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().createUser(3L, "15158114270", "测试小号").build();
        // 修改人员基础信息:updateUserBasicInfo(Long deptId:"用户所在部门id", Long uid:"用户id", String mobile:"用户手机号", String name:"用户名")
        AddressBookUserManageRequest request6 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().updateUserBasicInfo(3L, 126687464L, "15158114271", "测试小号更名").build();
        // 修改人员所在部门:updateUserDept(Long deptId:"用户新部门id", Long oldDeptId:"用户老部门id", Long uid:"用户id")
        AddressBookUserManageRequest request7 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().updateUserDept(32L, 3L, 126687464L).build();
        // 删除用户:deleteUser(Long uid:"用户id", Long deptId:"用户所在部门id")
        AddressBookUserManageRequest request8 = new AddressBookUserManageRequest.AddressBookUserManageBuilder().deleteUser(126687464L, 32L).build();
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
            AddressBookManageResponse response = client.execute(request8, "MDAwMEZDMzMzRThGODU0OUFDMjhBOEJEMzk5MDAxOTgwMzU2MTczMTM2NzY4RTAxREU3RDBFNEZFODg4NEM1QTcwOENCMTBDRDUzRkJDRkU1QTkyOEM1MDVGREY3M0QxMTEyNw==");
            System.out.println(response.toString());
        } catch (OpenapiException e) {
            e.printStackTrace();
        }
    }
}
