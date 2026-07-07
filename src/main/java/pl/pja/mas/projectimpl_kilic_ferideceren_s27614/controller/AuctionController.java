package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Auction;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.service.AuctionService;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.service.BidService;

@Controller
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final BidService bidService;
    private final AuctionService auctionService;


    @GetMapping("/{id}")
    public String viewAuctionDetails(@PathVariable Long id, Model model) {
        Auction auction = auctionService.getAuctionById(id);
        model.addAttribute("auction", auction);
        model.addAttribute("auctionItems", auction.getAuctionItems());
        return "auctions/details";
    }

    @PostMapping("/{auctionId}/items/{itemId}/bid")
    public String addBid(@PathVariable Long auctionId,
                         @PathVariable Long itemId,
                         @RequestParam(required = false) Long collectorId,
                         @RequestParam double bidAmount,
                         Model model) {

        if (collectorId == null) {
            model.addAttribute("error", "Please select a user from the top menu to place a bid!");
            return returnDetails(auctionId, model);
        }

        try {
            bidService.placeBid(collectorId, itemId, bidAmount);
            return "redirect:/auctions/" + auctionId + "?success=true";

        } catch (IllegalStateException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return returnDetails(auctionId, model);
        }
    }

    private String returnDetails(Long auctionId, Model model) {
        Auction auction = auctionService.getAuctionById(auctionId);
        model.addAttribute("auction", auction);
        model.addAttribute("auctionItems", auction.getAuctionItems());
        return "auctions/details";
    }
}