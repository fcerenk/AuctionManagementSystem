package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Gallery;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.GalleryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gallery not found"));
    }
}