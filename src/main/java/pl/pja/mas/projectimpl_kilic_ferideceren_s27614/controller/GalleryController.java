package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Gallery;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.service.GalleryService;

@Controller
@RequestMapping("/galleries")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping
    public String listGalleries(Model model) {
        model.addAttribute("galleries", galleryService.getAllGalleries());
        return "galleries/index";
    }

    @GetMapping("/{id}")
    public String viewGalleryDetails(@PathVariable Long id, Model model) {
        Gallery gallery = galleryService.getGalleryById(id);
        model.addAttribute("gallery", gallery);
        model.addAttribute("auctions", gallery.getAuctions());
        model.addAttribute("exhibitions", gallery.getExhibitions());
        return "galleries/details";
    }
}