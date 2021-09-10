package com.starrydecisis.stardec.model.immutableLookupTables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
//@Document(indexName = "bodytypeindex")  // Should this even have an index? It's a lookup table
public class BodyType {

    private static final Logger logger = LoggerFactory.getLogger(BodyType.class);

    @Id
    @NotNull
    @Field(type = FieldType.Text, name = "abbreviation")
    private String abbreviation;  // body_id

    @NotNull
    @Field(type = FieldType.Text, name = "type_name")
    private String typeName;

    public BodyType() {};

    public BodyType(String abbreviation, String typeName) {
        this.abbreviation = abbreviation;
        this.typeName = typeName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "abbreviation='" + abbreviation + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}

//ASTER  Asterism
//BRTNB  Bright Nebula
//CL+NB  Cluster with Nebulosity
//DRKNB  Dark Nebula
//GALCL  Galaxy cluster
//GALXY  Galaxy
//GLOCL  Globular Cluster
//GX+DN  Diffuse Nebula in a Galaxy
//GX+GC  Globular Cluster in a Galaxy
//G+C+N  Cluster with Nebulosity in a Galaxy
//LMCCN  Cluster with Nebulosity in the LMC
//LMCDN  Diffuse Nebula in the LMC
//LMCGC  Globular Cluster in the LMC
//LMCOC  Open cluster in the LMC
//NONEX  Nonexistent
//OPNCL  Open Cluster
//PLNNB  Planetary Nebula
//SMCCN  Cluster with Nebulosity in the SMC
//SMCDN  Diffuse Nebula in the SMC
//SMCGC  Globular Cluster in the SMC
//SMCOC  Open cluster in the SMC
//SNREM  Supernova Remnant
//QUASR  Quasar
//#STAR  # Stars (#=1, 2, 3, 4, 5, etc.)

