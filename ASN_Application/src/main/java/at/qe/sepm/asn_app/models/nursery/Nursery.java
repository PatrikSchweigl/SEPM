package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.general.PairTime;

import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */
public class Nursery {
    private List<Occupancy> listMaxOccupancy;
    private PairTime bringTime;
    private PairTime pickUpTime;
    private AuditLog auditLog;
    private Message messageboard;
    private PictureGallery pictureGallery;
    private List<Room> listRooms;
    private Terminal terminal;
}
