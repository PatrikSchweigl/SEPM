package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Component
@Scope("view")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    public Collection<Picture> getPictures(){
        return pictureService.getAllPictures();
    }
}
