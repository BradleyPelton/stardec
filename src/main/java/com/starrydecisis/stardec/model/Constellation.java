package com.starrydecisis.stardec.model;

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
//@Document(indexName = "constellationindex")  // Should this even have an index? It's a lookup table
public class Constellation {

    private static final Logger logger = LoggerFactory.getLogger(Constellation.class);

    @Id
    @NotNull
    @Field(type = FieldType.Text, name = "abbreviation")
    private String abbreviation;  // body_id
    @NotNull
    @Field(type = FieldType.Text, name = "name")
    private String name;

    public Constellation(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Constellation{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

//ANDROMEDA,AND
//ANTLIA,ANT
//APUS,APS
//AQUARIUS,AQR
//AQUILA,AQL
//ARA,ARA
//ARIES,ARI
//AURIGA,AUR
//BOOTES,BOO
//CAELUM,CAE
//CAMELOPARDALIS,CAM
//CANCER,CNC
//CANESVENATICI,CVN
//CANISMAJOR,CMA
//CANISMINOR,CMI
//CAPRICORNUS,CAP
//CARINA,CAR
//CASSIOPEIA,CAS
//CENTAURUS,CEN
//CEPHEUS,CEP
//CETUS,CET
//CHAMAELEON,CHA
//CIRCINUS,CIR
//COLUMBA,COL
//COMABERENICES,COM
//CORONAAUSTRALIS,CRA
//CORONABOREALIS,CRB
//CORVUS,CRV
//CRATER,CRT
//CRUX,CRU
//CYGNUS,CYG
//DELPHINUS,DEL
//DORADO,DOR
//DRACO,DRA
//EQUULEUS,EQU
//ERIDANUS,ERI
//FORNAX,FOR
//GEMINI,GEM
//GRUS,GRU
//HERCULES,HER
//HOROLOGIUM,HOR
//HYDRA,HYA
//HYDRUS,HYI
//INDUS,IND
//LACERTA,LAC
//LEO,LEO
//LEOMINOR,LMI
//LEPUS,LEP
//LIBRA,LIB
//LUPUS,LUP
//LYNX,LYN
//LYRA,LYR
//MENSA,MEN
//MICROSCOPIUM,MIC
//MONOCEROS,MON
//MUSCA,MUS
//NORMA,NOR
//OCTANS,OCT
//OPHIUCHUS,OPH
//ORION,ORI
//PAVO,PAV
//PEGASUS,PEG
//PERSEUS,PER
//PHOENIX,PHE
//PICTOR,PIC
//PISCES,PSC
//PISCESAUSTRINUS,PSA
//PUPPIS,PUP
//PYXIS,PYX
//RETICULUM,RET
//SAGITTA,SGE
//SAGITTARIUS,SGR
//SCORPIUS,SCO
//SCULPTOR,SCL
//SCUTUM,SCT
//SERPENS,SER
//SEXTANS,SEX
//TAURUS,TAU
//TELESCOPIUM,TEL
//TRIANGULUMAUSTRALE,TRA
//TRIANGULUM,TRI
//TUCANA,TUC
//URSAMAJOR,UMA
//URSAMINOR,UMI
//VELA,VEL
//VIRGO,VIR
//VOLANS,VOL
//VULPECULA,VUL
