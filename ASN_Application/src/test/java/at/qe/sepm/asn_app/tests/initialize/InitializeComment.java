package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.general.Comment;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 16:16 CEST.
 */
public class InitializeComment {

    public static Comment initialize1() {
        Comment comment = new Comment();
        comment.setComment("I am a comment.");
        comment.setDate(new Date());
        comment.setPictureName("PictureName1");
        comment.setUsername("Username1");
        return comment;
    }
}
