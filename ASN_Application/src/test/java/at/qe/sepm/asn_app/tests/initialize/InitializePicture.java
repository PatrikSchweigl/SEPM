package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.general.Comment;
import at.qe.sepm.asn_app.models.nursery.Picture;

import java.util.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 16:21 CEST.
 */
public class InitializePicture {

    public static Picture initialize1() {
        Set<Comment> comments = new HashSet<>();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 11, 16, 47);
        Date date = calendar.getTime();

        Picture picture = new Picture();
        picture.setComment(comments);
        picture.setDate(date);
        picture.setPublisher(InitializeUserData.initialize1());
        picture.setTitle("PictureTitle1");
        picture.setUrl("PictureUrl1");

        return picture;
    }
}
