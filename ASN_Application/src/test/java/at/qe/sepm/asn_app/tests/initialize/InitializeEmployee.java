package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.employee.Status;
import at.qe.sepm.asn_app.models.employee.WorkRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 16:35 CEST.
 */
public class InitializeEmployee {

    public static Employee initialize1() {
        Employee employee = new Employee();
        employee.setBirthday("23/11/1990");
        employee.setEmail("EmployeeEmail@google.com");
        employee.setFamilyStatus(FamilyStatus.VERWITTWED);
        employee.setFirstName("EmployeeFirstName");
        employee.setImgName("EmployeeImgName");
        employee.setLastName("EmployeeLastName");
        employee.setLocation("EmpoyeeLocation");
        employee.setNotification(false);
        employee.setPassword("passwd");
        employee.setPhoneNumber("437589234502");
        employee.setPostcode("6020");
        employee.setReligion(Religion.ISLAM);
        employee.setStreetName("EmployeeStreetName");
        employee.setUsername("EmployeeUsername");
        employee.setUserRole(UserRole.EMPLOYEE);
        employee.setWorkingState(Status.ABWESEND);
        employee.setWorkRole(WorkRole.PRAKTIKANT);
        return employee;
    }
}
