package com.starrydecisis.stardec.deepskybody;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table
public class DeepSkyBody {
    @Id
    @SequenceGenerator(
            name = "deepSkyBody_sequence",
            sequenceName = "deepSkyBody_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "deepSkyBody_sequence"
    )
    private Long id;
//    @Transient
    private Timestamp ingestionDate;
//    private TimeStamp lastUpdated;  // TODO

    private String bodyName;
    private String otherName;
    private String bodyType; // CONVERT TO ENUM
    private String constellation; // e.g. AND    .   Constellation in which the object is found in IAU forma


    // Visualization for coordinate system https://skyandtelescope.org/astronomy-resources/right-ascension-declination-celestial-coordinates/

//    public Double rightAscension;
//    Declination
//    Magnitude
//    Surface brightness
//    ...

    public DeepSkyBody() {
    }

    // without id
    public DeepSkyBody(String bodyName, String otherName, String bodyType, String constellation) {
        this.ingestionDate = new Timestamp(System.currentTimeMillis());  // TODO - BUG - INGESTION SHOULD BE TIMESTAMP, NOT DATESTAMP

        this.bodyName = bodyName;
        this.otherName = otherName;
        this.bodyType = bodyType;
        this.constellation = constellation;
    }

    // all fields
    public DeepSkyBody(Long id,
                       String bodyName,
                       String otherName,
                       String bodyType,
                       String constellation) {
        this.id = id;
        this.ingestionDate = new Timestamp(System.currentTimeMillis());
        this.bodyName = bodyName;
        this.otherName = otherName;
        this.bodyType = bodyType;
        this.constellation = constellation;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getIngestionDate() {
        return this.ingestionDate;
    }

    public void setIngestionDate(Timestamp ingestionDate) {
        this.ingestionDate = ingestionDate;
    }

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    @Override
    public String toString() {
        return "DeepSkyBody{" +
                "id=" + id +
                ", ingestionDate=" + ingestionDate +
                ", bodyName='" + bodyName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", constellation='" + constellation + '\'' +
                '}';
    }
}
