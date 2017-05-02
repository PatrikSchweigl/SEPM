package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Component
@Scope("application")
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Collection<Picture> getAllPictures(){
        return pictureRepository.findAll();
    }


}
