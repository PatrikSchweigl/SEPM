package at.qe.sepm.asn_app.models.general;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 19.03.2017
 *
 * Only children and employees have an attribute that refers to religion.
 * For simplicity this enum only contains five of the major world religions and atheism.
 * @see at.qe.sepm.asn_app.models.child.Child
 * @see at.qe.sepm.asn_app.models.employee.Employee
 */
public enum Religion {
    ISLAM,
    CHRISTIANITY,
    JUDAISM,
    BUDDHISM,
    HINDUISM,
    ATHEISM
}
