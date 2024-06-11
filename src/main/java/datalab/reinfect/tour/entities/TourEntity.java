package datalab.reinfect.tour.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tours")
public class TourEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// 수정 필요 @@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//	@OneToMany(fetch = FetchType.EAGER)
//	private ReviewEntity review;
	
	// 별점
	@Column(nullable = true)
	private String rating;
	
	// 고유번호
	@Column(nullable = false)
	private String POSTSN;
	
	// 관광지명
	@Column(nullable = false)
	private String POSTSJ;
	
	// 관광지 URL
	@Column(nullable = false, columnDefinition = "text")
	private String POSTURL;
	
	// 관광지 주소
	@Column(nullable = false)
	private String ADDRESS;
	
	// 관광지 신주소
	@Column(nullable = false)
	private String NEWADDRESS;
	
	// 관광지 전화번호
	@Column(nullable = false)
	private String CMMNTELNO;
	
	// 관광지 팩스번호
	@Column(nullable = false)
	private String CMMNFAX;
	
	// 관광지 웹사이트
	@Column(nullable = false, columnDefinition = "text")
	private String CMMNHMPGURL;
	
	// 관광지 운영 시간
	@Column(nullable = false, columnDefinition = "text")
	private String CMMNUSETIME;
	
	// 관광지 요일
	@Column(nullable = false)
	private String CMMNBSNDE;
	
	// 관광지 휴무일
	@Column(nullable = false)
	private String CMMNRSTDE;
	
	// 관광지 교통정보
	@Column(nullable = false)
	private String SUBWAYINFO;
	
	// 관광지 태그
	@Column(nullable = false)
	private String TAG;
	
	// 관광지 장애인 편의시설
	@Column(nullable = false)
	private String BFDESC;
	
	// 관광지 정보 생성일
	@Column(nullable = false)
	private String createDate;
	
	// 관광지 정보 수정일
	@Column(nullable = false)
	private String updateDate;
	

}
