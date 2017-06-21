package at.qe.sepm.asn_app.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.qe.sepm.asn_app.models.general.Comment;

public interface CommentRepository extends AbstractRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.pictureName = :pictureName")
    Collection<Comment> getAllCommentsByPicture(@Param("pictureName") String pictureName);

}
