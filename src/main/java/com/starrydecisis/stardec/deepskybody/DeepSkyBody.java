package com.starrydecisis.stardec.deepskybody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;

@Entity
@Table
@Document(indexName = "deepskybodyindex")
public class DeepSkyBody {

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBody.class);

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

//    @Field(type = FieldType.Date, name = "ingestionDate")
//    private Timestamp ingestionDate;
//    private TimeStamp lastUpdated;  // TODO

    @Field(type = FieldType.Text, name = "bodyName")
    private String bodyName;
    @Field(type = FieldType.Text, name = "otherName")
    private String otherName;
    @Field(type = FieldType.Text, name = "bodyType")
    private String bodyType; // CONVERT TO ENUM
    @Field(type = FieldType.Text, name = "constellation")
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
//        this.ingestionDate = new Timestamp(System.currentTimeMillis());  // TODO - BUG - INGESTION SHOULD BE TIMESTAMP, NOT DATESTAMP

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
//        this.ingestionDate = new Timestamp(System.currentTimeMillis());
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

//    public Timestamp getIngestionDate() {
//        return this.ingestionDate;
//    }

//    public void setIngestionDate(Timestamp ingestionDate) {
//        this.ingestionDate = ingestionDate;
//    }

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
//                ", ingestionDate=" + ingestionDate +
                ", bodyName='" + bodyName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", constellation='" + constellation + '\'' +
                '}';
    }
}
