package jSimpleDB.DBModel.Models.OrganizationModels;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="organizations")
@Data
public class Organization {
	@Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "uuid",
            strategy = "uuid2"
    )
//    @GenericGenerator(
//            name = "GUID",
//            strategy = "org.hibernate.id.GUIDGenerator"
//    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;
	public String Name;
    public boolean isCounterparty;

}
