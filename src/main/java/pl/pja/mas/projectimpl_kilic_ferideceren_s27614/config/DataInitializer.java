package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.AuctionItemStatus;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.Category;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.*;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final GalleryRepository galleryRepository;
    private final AuctionItemRepository auctionItemRepository;
    private final AuctionRepository auctionRepository;
    private final GalleryMemberRepository galleryMemberRepository;
    private final ArtWorkRepository artWorkRepository;
    private final ArtistRepository artistRepository;
    private final CollectorRepository collectorRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final BidRepository bidRepository;
    private final EmployeeRepository employeeRepository;
    private final RecordingRepository recordingRepository;

    @Override
    public void run(String ... args) {


        Gallery gallery1 = Gallery.builder()
                .galleryName("Digital Art Gallery Warsaw")
                .establishYear("2020").contactNumber("345 678 901")
                .location("Warsaw, Poland").build();
        galleryRepository.save(gallery1);

        Gallery gallery2 = Gallery.builder()
                .galleryName("Modern Canvas Krakow")
                .establishYear("2018").contactNumber("123 456 789")
                .location("Krakow, Poland").build();
        galleryRepository.save(gallery2);

        Gallery gallery3 = Gallery.builder()
                .galleryName("New World Contemporary")
                .establishYear("2015").contactNumber("123 456 049383")
                .location("Gdansk, Poland").build();
        galleryRepository.save(gallery3);

        Gallery gallery4 = Gallery.builder()
                .galleryName("Apollo Masters Gallery")
                .establishYear("2013").contactNumber("567 8392 485")
                .location("Lodz, Poland").build();
        galleryRepository.save(gallery4);

        Gallery gallery5 = Gallery.builder()
                .galleryName("Neon Art House")
                .establishYear("2022").contactNumber("987 654 321")
                .location("Katowice, Poland").build();
        galleryRepository.save(gallery5);


        Employee emp1 = Employee.builder()
                .name("Clark").surname("Kent")
                .password("admin!123").age(30)
                .email("clark@gallery1.com")
                .knownLanguages(List.of("English", "Polish", "French"))
                .gallery(gallery1).build();
        employeeRepository.save(emp1);

        Employee emp2 = Employee.builder()
                .name("Lena").surname("Novak")
                .password("lena!456K").age(28)
                .email("lena@gallery2.com")
                .knownLanguages(List.of("Polish", "German"))
                .gallery(gallery2).build();
        employeeRepository.save(emp2);

        Employee emp3 = Employee.builder()
                .name("James").surname("Okoro")
                .password("james!789J").age(35)
                .email("james@gallery3.com")
                .knownLanguages(List.of("English", "French", "Spanish"))
                .gallery(gallery3).build();
        employeeRepository.save(emp3);

        GalleryMember dualRoleMember = GalleryMember.builder()
                .name("Marcus").surname("Vance")
                .password("securepass123@L").age(45)
                .email("marcus@art.com").memberId(100L).build();
        galleryMemberRepository.save(dualRoleMember);

        Artist artist1 = Artist.builder()
                .biography("A wealthy contemporary artist blending digital media with physical sculptures. Known for provocative pieces that merge the virtual and physical worlds.")
                .member(dualRoleMember).build();
        dualRoleMember.addArtist(artist1);
        artistRepository.save(artist1);

        Collector dualCollector = Collector.builder()
                .balance(85000.54).member(dualRoleMember).build();
        dualRoleMember.addCollector(dualCollector);
        collectorRepository.save(dualCollector);

        GalleryMember artistMember2 = GalleryMember.builder()
                .name("Sofia").surname("Nguyen")
                .password("artPass99@S").age(32)
                .email("sofia@art.com").memberId(101L).build();
        galleryMemberRepository.save(artistMember2);

        Artist artist2 = Artist.builder()
                .biography("Contemporary sculptor exploring the intersection of organic forms and digital fabrication. Her works challenge traditional boundaries of materiality.")
                .member(artistMember2).build();
        artistMember2.addArtist(artist2);
        artistRepository.save(artist2);

        GalleryMember artistMember3 = GalleryMember.builder()
                .name("Arthur").surname("Pendleton")
                .password("arthur!2345K").age(52)
                .email("arthur@gmail.com").memberId(102L).build();
        galleryMemberRepository.save(artistMember3);

        Artist artist3 = Artist.builder()
                .biography("A master of traditional oil painting techniques. Arthur spends months on a single canvas, focusing on dramatic lighting and historical landscapes.")
                .member(artistMember3).build();
        artistMember3.addArtist(artist3);
        artistRepository.save(artist3);

        GalleryMember artistMember4 = GalleryMember.builder()
                .name("Yuki").surname("Tanaka")
                .password("yuki!2345K").age(28)
                .email("yuki@gmail.com").memberId(103L).build();
        galleryMemberRepository.save(artistMember4);

        Artist artist4 = Artist.builder()
                .biography("Award-winning urban photographer capturing the fast-paced life of modern metropolises. Finalist at Warsaw Photo Festival 2025.")
                .member(artistMember4).build();
        artistMember4.addArtist(artist4);
        artistRepository.save(artist4);


        GalleryMember collectorMember1 = GalleryMember.builder()
                .name("Bruce").surname("Wayne")
                .password("84849ndk!Gls").age(36)
                .email("bruce@wayne.com").memberId(200L).build();
        galleryMemberRepository.save(collectorMember1);
        Collector collector1 = Collector.builder()
                .balance(50000.00).member(collectorMember1).build();
        collectorMember1.addCollector(collector1);
        collectorRepository.save(collector1);

        GalleryMember collectorMember2 = GalleryMember.builder()
                .name("Diana").surname("Prince")
                .password("wonder99@Dp").age(29)
                .email("diana@amazon.com").memberId(201L).build();
        galleryMemberRepository.save(collectorMember2);

        Collector collector2 = Collector.builder()
                .balance(30000.00).member(collectorMember2).build();
        collectorMember2.addCollector(collector2);
        collectorRepository.save(collector2);

        GalleryMember collectorMember3 = GalleryMember.builder()
                .name("Tony").surname("Stark")
                .password("ironPass1@Ts").age(41)
                .email("tony@stark.com").memberId(202L).build();
        galleryMemberRepository.save(collectorMember3);

        Collector collector3 = Collector.builder()
                .balance(99000.00).member(collectorMember3).build();
        collectorMember3.addCollector(collector3);
        collectorRepository.save(collector3);

        GalleryMember brokeMember = GalleryMember.builder()
                .name("Peter").surname("Parker")
                .password("spidey$kdkdk").age(22)
                .email("peter@daily.com").memberId(205L).build();
        galleryMemberRepository.save(brokeMember);
        Collector brokeCollector = Collector.builder()
                .balance(50.30).member(brokeMember).build();
        brokeMember.addCollector(brokeCollector);
        collectorRepository.save(brokeCollector);


        ArtWork art1 = ArtWork.builder()
                .title("Cyberpunk Cityscape").price(5000.0).madeYear("2024")
                .category(Category.DIGITAL_ART)
                .dimension(Dimension.builder().width(1920).height(1080).depth(0).build())
                .artist(artist1).gallery(gallery1).build();
        artWorkRepository.save(art1);

        ArtWork art2 = ArtWork.builder()
                .title("Neural Garden").price(3500.0).madeYear("2023")
                .category(Category.DIGITAL_ART)
                .dimension(Dimension.builder().width(2400).height(1600).depth(0).build())
                .artist(artist1).gallery(gallery1).build();
        artWorkRepository.save(art2);

        ArtWork art3 = ArtWork.builder()
                .title("Fragments of Memory").price(8000.0).madeYear("2022")
                .category(Category.SCULPTURE)
                .dimension(Dimension.builder().width(60).height(90).depth(40).build())
                .artist(artist2).gallery(gallery1).build();
        artWorkRepository.save(art3);

        ArtWork art4 = ArtWork.builder()
                .title("Echoes of Tomorrow").price(4200.0).madeYear("2024")
                .category(Category.PAINTING)
                .dimension(Dimension.builder().width(100).height(80).depth(0).build())
                .artist(artist2).gallery(gallery1).build();
        artWorkRepository.save(art4);

        ArtWork art5 = ArtWork.builder()
                .title("Starry Algorithm").price(6000.0).madeYear("2023")
                .category(Category.OIL_IN_CANVAS)
                .dimension(Dimension.builder().width(73).height(92).depth(0).build())
                .artist(artist1).gallery(gallery2).build();
        artWorkRepository.save(art5);

        ArtWork art5b = ArtWork.builder()
                .title("Crimson Threshold").price(4800.0).madeYear("2022")
                .category(Category.PAINTING)
                .dimension(Dimension.builder().width(90).height(70).depth(0).build())
                .artist(artist3).gallery(gallery2).build();
        artWorkRepository.save(art5b);

        ArtWork art6b = ArtWork.builder()
                .title("Autumn in Gdansk").price(5500.0).madeYear("2021")
                .category(Category.PHOTOGRAPHY)
                .dimension(Dimension.builder().width(80).height(60).depth(0).build())
                .artist(artist4).gallery(gallery3).build();
        artWorkRepository.save(art6b);

        ArtWork art6 = ArtWork.builder()
                .title("The Last Embers of Rome").price(15000.0).madeYear("2018")
                .category(Category.OIL_IN_CANVAS)
                .dimension(Dimension.builder().width(120).height(90).depth(0).build())
                .artist(artist3).gallery(gallery3).build();
        artWorkRepository.save(art6);


        ArtWork art7 = ArtWork.builder()
                .title("Neon Nights of Tokyo").price(2500.0).madeYear("2025")
                .category(Category.PHOTOGRAPHY)
                .dimension(Dimension.builder().width(60).height(40).depth(0).build())
                .artist(artist4).gallery(gallery4).build();
        artWorkRepository.save(art7);

        ArtWork art7b = ArtWork.builder()
                .title("Steel & Silk").price(7200.0).madeYear("2023")
                .category(Category.SCULPTURE)
                .dimension(Dimension.builder().width(35).height(80).depth(35).build())
                .artist(artist2).gallery(gallery4).build();
        artWorkRepository.save(art7b);

        ArtWork art8 = ArtWork.builder()
                .title("Digital Bronze Flow").price(8500.0).madeYear("2024")
                .category(Category.SCULPTURE)
                .dimension(Dimension.builder().width(45).height(110).depth(45).build())
                .artist(artist2).gallery(gallery5).build();
        artWorkRepository.save(art8);

        ArtWork art9 = ArtWork.builder()
                .title("Portrait of a Noble").price(9000.0).madeYear("2026")
                .category(Category.PAINTING)
                .dimension(Dimension.builder().width(60).height(80).depth(0).build())
                .artist(artist3).gallery(gallery1).build();
        artWorkRepository.save(art9);

        ArtWork art10 = ArtWork.builder()
                .title("Electric Pastoral").price(3200.0).madeYear("2025")
                .category(Category.DIGITAL_ART)
                .dimension(Dimension.builder().width(3840).height(2160).depth(0).build())
                .artist(artist1).gallery(gallery5).build();
        artWorkRepository.save(art10);


        saveRecording(emp1, art1, LocalDateTime.now().minusDays(30),
                "Initial registration: artwork received from artist studio. Dimensions and category verified.", recordingRepository);
        saveRecording(emp1, art1, LocalDateTime.now().minusDays(12),
                "Pre-auction condition report: no damage detected, canvas intact. Certificate of authenticity attached.", recordingRepository);

        saveRecording(emp1, art2, LocalDateTime.now().minusDays(25),
                "Digital artwork registered. Metadata extracted: resolution 2400x1600, file format PNG.", recordingRepository);
        saveRecording(emp1, art2, LocalDateTime.now().minusDays(8),
                "Artwork reviewed for exhibition eligibility. Rejected due to pending auction assignment.", recordingRepository);

        saveRecording(emp1, art3, LocalDateTime.now().minusDays(40),
                "Sculpture cataloged. Weight approx. 18kg. Fragile — requires custom crating for transport.", recordingRepository);
        saveRecording(emp3, art3, LocalDateTime.now().minusDays(10),
                "Second inspection before auction: minor surface dust cleaned. Structural integrity confirmed.", recordingRepository);

        saveRecording(emp1, art4, LocalDateTime.now().minusDays(20),
                "Painting registered. Medium: acrylic on canvas. Provenance: purchased directly from artist.", recordingRepository);

        saveRecording(emp2, art5, LocalDateTime.now().minusDays(35),
                "Oil on canvas received at Krakow gallery. Stretcher bars in good condition. UV-protective glass installed.", recordingRepository);
        saveRecording(emp2, art5, LocalDateTime.now().minusDays(15),
                "Valuation updated following Art Index report. Reserve price adjusted accordingly.", recordingRepository);

        saveRecording(emp2, art5b, LocalDateTime.now().minusDays(18),
                "Artwork received and cataloged. Provenance documents verified with artist studio.", recordingRepository);

        saveRecording(emp3, art6, LocalDateTime.now().minusDays(60),
                "Monumental oil painting cataloged. Condition: excellent. No restoration work required.", recordingRepository);
        saveRecording(emp3, art6, LocalDateTime.now().minusDays(7),
                "Exhibition preparation: artwork cleaned and reframed. Lighting arrangement confirmed with curator.", recordingRepository);

        saveRecording(emp3, art6b, LocalDateTime.now().minusDays(22),
                "Photography print registered. Edition 1/5. Archival pigment on fine art paper, framed.", recordingRepository);

        saveRecording(emp1, art7, LocalDateTime.now().minusDays(14),
                "Photography edition received: 2/5. Fine art print on aluminium dibond. No damage.", recordingRepository);

        saveRecording(emp1, art7b, LocalDateTime.now().minusDays(28),
                "Mixed-media sculpture cataloged. Materials: steel wire, silk fabric. Requires humidity control.", recordingRepository);

        saveRecording(emp1, art8, LocalDateTime.now().minusDays(45),
                "Sculpture received at Neon Art House. Bronze casting verified with artist certificate.", recordingRepository);
        saveRecording(emp2, art8, LocalDateTime.now().minusDays(5),
                "Pre-auction inspection: patina consistent, no cracks. Insurance valuation updated to $10,000.", recordingRepository);

        saveRecording(emp1, art9, LocalDateTime.now().minusDays(10),
                "Painting arrived freshly completed. Varnish still curing — handle with care.", recordingRepository);

        saveRecording(emp1, art10, LocalDateTime.now().minusDays(6),
                "Digital artwork: 4K resolution, certified by artist as unique edition. File stored on gallery server.", recordingRepository);

        Exhibition exhibition1 = Exhibition.builder()
                .name("Digital Horizons 2026").theme("Future of Art")
                .timeOfYear("Spring 2026").gallery(gallery1).build();
        gallery1.addExhibition(exhibition1);
        exhibitionRepository.save(exhibition1);
        exhibition1.addArtwork(art4);
        exhibitionRepository.save(exhibition1);

        Exhibition exhibition2 = Exhibition.builder()
                .name("Masters of Colour").theme("Post-Impressionism Revisited")
                .timeOfYear("Summer 2026").gallery(gallery2).build();
        gallery2.addExhibition(exhibition2);
        exhibitionRepository.save(exhibition2);
        exhibition2.addArtwork(art5b);
        exhibitionRepository.save(exhibition2);

        Exhibition exhibition3 = Exhibition.builder()
                .name("Classic Masters Renaissance").theme("Historical Landscapes")
                .timeOfYear("Autumn 2026").gallery(gallery3).build();
        gallery3.addExhibition(exhibition3);
        exhibitionRepository.save(exhibition3);
        exhibition3.addArtwork(art6);
        exhibition3.addArtwork(art6b);
        exhibitionRepository.save(exhibition3);

        Exhibition exhibition4 = Exhibition.builder()
                .name("Form & Material").theme("Contemporary Sculpture")
                .timeOfYear("Winter 2026").gallery(gallery4).build();
        gallery4.addExhibition(exhibition4);
        exhibitionRepository.save(exhibition4);
        exhibition4.addArtwork(art7b);
        exhibitionRepository.save(exhibition4);

        Exhibition exhibition5 = Exhibition.builder()
                .name("Neon Dreams").theme("Digital Art & New Media")
                .timeOfYear("Spring 2026").gallery(gallery5).build();
        gallery5.addExhibition(exhibition5);
        exhibitionRepository.save(exhibition5);
        exhibition5.addArtwork(art10);
        exhibitionRepository.save(exhibition5);


        Auction auction1 = Auction.builder()
                .name("Spring Digital Exclusives 2026")
                .startTime(LocalDateTime.now().minusDays(2))
                .endTime(LocalDateTime.now().plusDays(5))
                .gallery(gallery1).build();
        auctionRepository.save(auction1);

        Auction auction2 = Auction.builder()
                .name("Last Chance Sculpture Gala")
                .startTime(LocalDateTime.now().minusDays(3))
                .endTime(LocalDateTime.now().plusMinutes(3))
                .gallery(gallery1).build();
        auctionRepository.save(auction2);

        Auction auction3 = Auction.builder()
                .name("Krakow Masters Invitational")
                .startTime(LocalDateTime.now().minusDays(1))
                .endTime(LocalDateTime.now().plusDays(4))
                .gallery(gallery2).build();
        auctionRepository.save(auction3);

        Auction auction4 = Auction.builder()
                .name("Urban Lens Photography Sale")
                .startTime(LocalDateTime.now().minusDays(1))
                .endTime(LocalDateTime.now().plusDays(3))
                .gallery(gallery4).build();
        auctionRepository.save(auction4);

        Auction auction5 = Auction.builder()
                .name("Avant-Garde Neon Sculpture Sale")
                .startTime(LocalDateTime.now().minusDays(2))
                .endTime(LocalDateTime.now().plusDays(1))
                .gallery(gallery5).build();
        auctionRepository.save(auction5);


        AuctionItem item1 = AuctionItem.builder()
                .openingPrice(5000.0).reservePrice(7000.0)
                .currentHighestBid(5250.0)
                .status(AuctionItemStatus.ACTIVE_BIDDING)
                .minimumIncrement(500.0)
                .artWork(art1).auction(auction1).build();
        auctionItemRepository.save(item1);


        AuctionItem itemSold = AuctionItem.builder()
                .openingPrice(1000.0).reservePrice(2000.0)
                .currentHighestBid(2750.0)
                .status(AuctionItemStatus.SOLD)
                .minimumIncrement(100.0)
                .artWork(art9).auction(auction2).build();
        auctionItemRepository.save(itemSold);

        AuctionItem itemWithdrawn = AuctionItem.builder()
                .openingPrice(3500.0).reservePrice(6000.0)
                .currentHighestBid(3500.0)
                .status(AuctionItemStatus.WITHDRAWN)
                .minimumIncrement(100.0)
                .artWork(art2).auction(auction2).build();
        auctionItemRepository.save(itemWithdrawn);

        AuctionItem item3 = AuctionItem.builder()
                .openingPrice(7000.0).reservePrice(9000.0)
                .currentHighestBid(7000.0)
                .status(AuctionItemStatus.ACTIVE_BIDDING)
                .minimumIncrement(500.0)
                .artWork(art3).auction(auction2).build();
        auctionItemRepository.save(item3);

        AuctionItem item4 = AuctionItem.builder()
                .openingPrice(5500.0).reservePrice(8000.0)
                .currentHighestBid(5500.0)
                .status(AuctionItemStatus.ACTIVE_BIDDING)
                .minimumIncrement(500.0)
                .artWork(art5).auction(auction3).build();
        auctionItemRepository.save(item4);

        AuctionItem item5 = AuctionItem.builder()
                .openingPrice(2500.0).reservePrice(4000.0)
                .currentHighestBid(2500.0)
                .status(AuctionItemStatus.ACTIVE_BIDDING)
                .minimumIncrement(100.0)
                .artWork(art7).auction(auction4).build();
        auctionItemRepository.save(item5);

        AuctionItem item6 = AuctionItem.builder()
                .openingPrice(8500.0).reservePrice(12000.0)
                .currentHighestBid(8500.0)
                .status(AuctionItemStatus.ACTIVE_BIDDING)
                .minimumIncrement(500.0)
                .artWork(art8).auction(auction5).build();
        auctionItemRepository.save(item6);


        Bid bid1 = Bid.builder()
                .bidAmount(5000.0).bidTime(LocalDateTime.now().minusHours(48))
                .collector(collector2).auctionItem(item1).build();
        item1.addBid(bid1);
        bidRepository.save(bid1);

        Bid bid2 = Bid.builder()
                .bidAmount(5100.0).bidTime(LocalDateTime.now().minusHours(36))
                .collector(collector3).auctionItem(item1).build();
        item1.addBid(bid2);
        bidRepository.save(bid2);

        Bid bid3 = Bid.builder()
                .bidAmount(5250.0).bidTime(LocalDateTime.now().minusHours(12))
                .collector(collector1).auctionItem(item1).build();
        item1.addBid(bid3);
        bidRepository.save(bid3);

        Bid bidSold1 = Bid.builder()
                .bidAmount(1500.0).bidTime(LocalDateTime.now().minusHours(5))
                .collector(collector1).auctionItem(itemSold).build();
        itemSold.addBid(bidSold1);
        bidRepository.save(bidSold1);

        Bid bidSold2 = Bid.builder()
                .bidAmount(2000.0).bidTime(LocalDateTime.now().minusHours(3))
                .collector(collector3).auctionItem(itemSold).build();
        itemSold.addBid(bidSold2);
        bidRepository.save(bidSold2);

        Bid bidSold3 = Bid.builder()
                .bidAmount(2750.0).bidTime(LocalDateTime.now().minusHours(1))
                .collector(collector2).auctionItem(itemSold).build();
        itemSold.addBid(bidSold3);
        bidRepository.save(bidSold3);

        Bid bidWithdrawn1 = Bid.builder()
                .bidAmount(3500.0).bidTime(LocalDateTime.now().minusDays(2))
                .collector(collector1).auctionItem(itemWithdrawn).build();
        itemWithdrawn.addBid(bidWithdrawn1);
        bidRepository.save(bidWithdrawn1);

        auctionItemRepository.save(item1);
        auctionItemRepository.save(itemSold);
        auctionItemRepository.save(itemWithdrawn);

        System.out.println("Sample data loaded successfully.");
    }

    private void saveRecording(Employee employee, ArtWork artWork,
                               LocalDateTime date, String description,
                               RecordingRepository repo) {
        Recording rec = Recording.builder()
                .recordByDate(date)
                .description(description)
                .employee(employee)
                .artWork(artWork)
                .build();
        artWork.addRecording(rec);
        repo.save(rec);
    }
}