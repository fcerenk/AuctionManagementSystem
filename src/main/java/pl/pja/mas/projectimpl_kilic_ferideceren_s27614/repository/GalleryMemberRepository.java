package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.GalleryMember;
@Repository
public interface GalleryMemberRepository extends JpaRepository<GalleryMember, Long> {
}
