package reinfect.datalab.tour.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, name = "post_url")
    private String postUrl;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "new_address")
    private String newAddress;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false, name = "business_time", columnDefinition = "text")
    private String businessTime;

    @Column(nullable = false, name = "business_day", columnDefinition = "text")
    private String businessDay;

    @Column(nullable = false, name = "break_date", columnDefinition = "text")
    private String breakDate;

    @Column(nullable = false)
    private String access;

    @Column(nullable = false)
    private String tags;

    @Column(nullable = false)
    private String handicap;

    @Column(nullable = false)
    private String position;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceRating> placeRatings;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceReview> placeReviews;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

}
