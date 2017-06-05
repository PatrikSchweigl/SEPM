package at.qe.sepm.asn_app.models.employee;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 21.03.2017
 *
 * WorkRole is specifically assigned to an employee and determines whether
 * the employee is a pedagogue or a trainee.
 * @see Employee
 * @see at.qe.sepm.asn_app.ui.controllers.EnumController
 */
public enum WorkRole {
    PAEDAGOGE,
    PRAKTIKANT
}
