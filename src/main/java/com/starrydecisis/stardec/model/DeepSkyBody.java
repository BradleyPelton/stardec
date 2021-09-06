package com.starrydecisis.stardec.model;

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


//    @SequenceGenerator(
//            name = "deepSkyBody_sequence",
//            sequenceName = "deepSkyBody_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "deepSkyBody_sequence"
//    )
//    @Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;  // body_id
    @Field(type = FieldType.Text, name = "body_name")
    private String bodyName;
    @Field(type = FieldType.Text, name = "other_name")
    private String otherName;
    @Field(type = FieldType.Text, name = "body_type")
    private String bodyType; // (CONVERT TO ENUM
    @Field(type = FieldType.Text, name = "constellation")
    private String constellation; // e.g. AND    .   Constellation in which the object is found in IAU forma
    @Field(type = FieldType.Text, name = "description")
    private String description; // observable qualities of the celestial body(e.g. eF;vS;mE;vF*v nr)=(very Far, dim, etc.)
    @Field(type = FieldType.Text, name = "notes")
    private String notes; // technical notes, famous names ("Blue snowball nebula")

    // Visualization for coordinate system https://skyandtelescope.org/astronomy-resources/right-ascension-declination-celestial-coordinates/

    // TODO - Add technical fields: coordinates, brightness, etc.
//    public Double rightAscension;
//    Declination
//    Magnitude
//    Surface brightness
//    ...

    // TODO add ingestion,lastupdated columns for debugging
//    @Transient
//    @Field(type = FieldType.Date, name = "ingestionDate")
//    private Timestamp ingestionDate;
//    private TimeStamp lastUpdated;

    public DeepSkyBody() {
    }

    // without id
    public DeepSkyBody(String bodyName,
                       String otherName,
                       String bodyType,
                       String constellation,
                       String description,
                       String notes) {
//        this.ingestionDate = new Timestamp(System.currentTimeMillis());  // TODO - BUG - INGESTION SHOULD BE TIMESTAMP, NOT DATESTAMP
        this.bodyName = bodyName;
        this.otherName = otherName;
        this.bodyType = bodyType;
        this.constellation = constellation;
        this.description = description;
        this.notes = notes;
    }

    // all fields
    public DeepSkyBody(Long id,
                       String bodyName,
                       String otherName,
                       String bodyType,
                       String constellation,
                       String description,
                       String notes) {
        this.id = id;
//        this.ingestionDate = new Timestamp(System.currentTimeMillis());
        this.bodyName = bodyName;
        this.otherName = otherName;
        this.bodyType = bodyType;
        this.constellation = constellation;
        this.description = description;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setBodyId(Long bodyId) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
