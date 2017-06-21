package at.qe.sepm.asn_app.configs;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at> on
 * 05.06.2017
 */
@Configuration
@EnableScheduling
public class NotificationConfig {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private LunchService lunchService;

    @Scheduled(cron = "0 0 10 * * *") // 60000 for one minute - "0 0 10 * * *"
    // 10 AM everyday
    public void taskReminder() {
        Collection<UserData> list = userService.getParentsByNotification();
        String footer = "Das Kinderkrippen-Team bedankt sich für Ihre Mitarbeit!";

        Date today = new Date();
        Date dateToCompare = new Date();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        cal.setTime(today);

        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milli = cal.get(Calendar.MILLISECOND);

        today.setTime(today.getTime() - (min * 60 * 1000) - (hrs * 60 * 60 * 1000) - (sec * 1000) - milli);

        if (!list.isEmpty()) {
            Iterator<UserData> itL = list.iterator();
            while (itL.hasNext()) {
                Parent p = parentService.loadParent(itL.next().getUsername());
                String taskMsg = "";
                boolean send = false;
                Collection<Task> tasks = taskService.getAllTasksByReceiver(p.getUsername());

                Iterator<Task> itT = tasks.iterator();
                while (itT.hasNext()) {
                    Task t = itT.next();
                    cal.setTime(t.getBeginDate());
                    hrs = cal.get(Calendar.HOUR_OF_DAY);
                    min = cal.get(Calendar.MINUTE);

                    dateToCompare.setTime(t.getBeginDate().getTime() - (min * 60 * 1000) - (hrs * 60 * 60 * 1000));

                    if ((dateToCompare.getTime() - today.getTime()) == 345600000) { // 4
                        // days
                        // before
                        // the
                        // task
                        send = true;
                        taskMsg += t.getDescription() + "\t|\t" + t.getFormattedDate(t.getBeginDate()) + "\t|\t"
                                + t.getFormattedDate(t.getEndingDate()) + "\n";
                    }
                }
                if (send) {
                    mailService.sendEmail(p.getEmail(), "Erinnerung - Anstehende Aufgaben",
                            "Guten Tag " + p.getFirstName() + " " + p.getLastName()
                                    + "!\n\nSie haben noch 4 Tage zeit, um folgende Aufgabe(n) zu erledigen:\n\n"
                                    + taskMsg + "\n\n" + footer);
                }

            }
        }
    }

    @Scheduled(cron = "0 10 10 28-31 * ?")
    public void monthlyLunchCalc() {
        String footer = "Das Kinderkrippen-Team bedankt sich für Ihre Mitarbeit!";
        Collection<Parent> list = parentService.getAllParents();
        Iterator<Parent> iter = list.iterator();
        while (iter.hasNext()) {
            double costPerMonth = 0.0;
            Parent p = iter.next();
            for (Child c : p.getChildren()) {
                Date start = new Date();
                start.setDate(1);
                start.setMonth((start.getMonth() + 0) % 12);
                Date end = new Date();
                end.setDate(1);
                end.setMonth((end.getMonth() + 0 + 1) % 12);
                if (end.getMonth() == 0) {
                    end.setYear(end.getYear() + 1);
                }
                Collection<Lunch> lunch = lunchService.getLunchInTimeWindowIE(start, end);
                for (Lunch l : lunch) {
                    if (l.getChildrenIds().contains(c.getId()))
                        costPerMonth += l.getCost();
                }
            }
            mailService.sendEmail(p.getEmail(), "Essensabrechnung",
                    "Guten Tag " + p.getFirstName() + " " + p.getLastName()
                            + "!\n\nWir senden Ihnen die monatliche Abrechnung für die konsumierten Mahlzeiten.:\n\n"
                            + "Offener Betrag:  " + costPerMonth + " zahlbar binnen 14 Tagen."
                            + "\n\n" + footer);
        }
    }
}
