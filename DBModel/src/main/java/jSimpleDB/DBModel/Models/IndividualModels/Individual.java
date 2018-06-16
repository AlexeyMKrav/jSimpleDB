package jSimpleDB.DBModel.Models.IndividualModels;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="individuals")
@Data
public class Individual {
    @Id
    @GeneratedValue(generator = "GUID", strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "GUID",
            strategy = "org.hibernate.id.GUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;
    private String name;
    private String position;
    private String login;
    private LocalDate loginExpired;
    private String email;
    private String phone;

}
