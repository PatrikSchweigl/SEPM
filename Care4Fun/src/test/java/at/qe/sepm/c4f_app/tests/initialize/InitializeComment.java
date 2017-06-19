package at.qe.sepm.c4f_app.tests.initialize;

import at.qe.sepm.c4f_app.models.general.Comment;

import java.util.Date;

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
