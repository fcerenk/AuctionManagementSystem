package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.AuctionItemStatus;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Auction;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.AuctionItem;

import java.util.List;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    List<AuctionItem> findByStatusIn(List<AuctionItemStatus> statuses);
}
