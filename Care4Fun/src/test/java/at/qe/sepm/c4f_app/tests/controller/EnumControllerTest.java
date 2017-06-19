package at.qe.sepm.c4f_app.tests.controller;

import at.qe.sepm.c4f_app.models.employee.WorkRole;
import at.qe.sepm.c4f_app.models.general.FamilyStatus;
import at.qe.sepm.c4f_app.models.general.Religion;
import at.qe.sepm.c4f_app.ui.controllers.EnumController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 10.06.17 14:58 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EnumControllerTest {

    @Autowired
    private EnumController enumController;


    @Test
    public void testGetFamilyStatus() {
        Map<String, String> familyStatus = enumController.getFamilyStatus();

        for (FamilyStatus value : FamilyStatus.values()) {
            assertTrue(familyStatus.containsKey(value.toString()));
            assertTrue(familyStatus.containsValue(value.toString()));
        }
    }


    @Test
    public void testGetReligions() {
        Map<String, String> religions = enumController.getReligions();

        for (Religion value : Religion.values()) {
            assertTrue(religions.containsKey(value.toString()));
            assertTrue(religions.containsValue(value.toString()));
        }
    }


    @Test
    public void testGetWorkRoles() {
        Map<String, String> roles = enumController.getWorkRoles();

        for (WorkRole value : WorkRole.values()) {
            assertTrue(roles.containsKey(value.toString()));
            assertTrue(roles.containsValue(value.toString()));
        }
    }
}
