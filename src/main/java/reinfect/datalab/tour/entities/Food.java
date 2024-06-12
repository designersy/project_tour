package reinfect.datalab.tour.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "content_url")
    private String contentUrl;

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

    @Column(nullable = false)
    private String access;

    @Column(nullable = false, name = "main_food")
    private String mainFood;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

}
