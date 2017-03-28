package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.employee.Employee;

import javax.transaction.Transactional;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Transactional
public interface EmployeeRepository extends UserBaseRepository<Employee>, AbstractRepository<Employee,Long>{
}
