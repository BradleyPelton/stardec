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
//@Document(indexName = "namednonstarbodies")  // Should this even have an index? It's a lookup table
public class NamedNonStarBody {

    private static final Logger logger = LoggerFactory.getLogger(NamedNonStarBody.class);

    @Id
    @NotNull
    @Field(type = FieldType.Text, name = "name")
    private String name;

    @NotNull
    @Field(type = FieldType.Text, name = "catalogue_designations")
    private String catalogueDesignations;

    @NotNull
    @Field(type = FieldType.Text, name = "constellation")
    private String constellation;

    public NamedNonStarBody(String name,
                            String catalogueDesignations,
                            String constellation) {
        this.name = name;
        this.catalogueDesignations = catalogueDesignations;
        this.constellation = constellation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogueDesignations() {
        return catalogueDesignations;
    }

    public void setCatalogueDesignations(String catalogueDesignations) {
        this.catalogueDesignations = catalogueDesignations;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    @Override
    public String toString() {
        return "NamedNonStarBody{" +
                "name='" + name + '\'' +
                ", catalogueDesignations='" + catalogueDesignations + '\'' +
                ", constellation='" + constellation + '\'' +
                '}';
    }
}
// ('47 TUCANAE','NGC 104','TUC')
//,('BARNARDS GALAXY','NGC 6822','SGR')
//,('BEAR PAW GALAXY','NGC 2537','UMA')
//,('BLINKING PLANETARY','NGC 6826','CYG')
//,('BLUE FLASH','NGC 6905','DEL')
//,('BAXENDELLS UNPHOTOGRAPHABLE NEBULA','NGC 7088','AQR')
//,('BUBBLE NEBULA','NGC 7635','CAS')
//,('BUG NEBULA','NGC 6302','SCO')
//,('CALIFORNIA NEBULA','NGC 1499','PER')
//,('CATS EYE','NGC 6543','DRA')
//,('CBS EYE','NGC 3242','HYA')
//,('CHRISTMAS TREE CLUSTER','NGC 2264','MON')
//,('CLOWN FACE NEBULA','NGC 2392','GEM')
//,('CONE NEBULA','NGC 2264','MON')
//,('CRESCENT','NGC 6445','SGR')
//,('CRESCENT (ALSO 6445)','NGC 6888','CYG')
//,('EIGHT-BURST','NGC 3132','ANT')
//,('ESKIMO PLANETARY','NGC 2392','GEM')
//,('ETA CARINA NEBULA','NGC 3372','CAR')
//,('GHOST OF JUPITER','NGC 3242','HYA')
//,('H PERSEI','NGC 869','PER')
//,('HELIX NEBULA','NGC 7293','AQR')
//,('HINDS VARIABLE NEBULA','NGC 1555','TAU')
//,('HUBBLES VARIABLE NEBULA','NGC 2261','MON')
//,('JEWEL BOX','NGC 4755','CRU')
//,('KEYHOLE NEB(ETA CAR)','NGC 3372','CAR')
//,('LITTLE GEM','NGC 6818','SGR')
//,('LITTLE GHOST','NGC 6369','OPH')
//,('NORTH AMERICAN NEBULA','NGC 7000','CYG')
//,('OMEGA CEN CLUSTER','NGC 5139','CEN')
//,('PHANTOM STREAK','NGC 6741','AQL')
//,('PIPE NEB','NGC 1267','OPH')
//,('POLARISSIMA AUSTRALIS','NGC 2573','OCT')
//,('POLARISSIMA BOREALIS','NGC 3172','UMI')
//,('RINGTAIL GAL','NGC 4038','CRV')
//,('ROSETTE','NGC 2244','MON')
//,('SATURN NEB','NGC 7009','AQR')
//,('SPINDLE NEB','NGC 3115','SEX')
//,('STRUVES LOST NEBULA','NGC 1554','TAU')
//,('TABLE OF SCORPIUS','NGC 6231','SCO')
//,('TANK TRACK NEBULA','NGC 2024','ORI')
//,('TOM THUMB CLUSTER','NGC 6451','SCO')
//,('VEIL NEB','NGC 6960,NGC 6992','CYG')
//,('X PER','NGC 884','PER')
//,('ANDROMEDA GALAXY','NGC 224,Messier 31','AND')
//,('BARBELL NEBULA','NGC 650,Messier 76','PER')
//,('BEEHIVE CLUSTER (PRAESEPE)','NGC 2632,Messier 44','CNC')
//,('BLACKEYE NEBULA','NGC 4826,Messier 64','COM')
//,('DUMBBELL NEBULA','NGC 6853,Messier 27','VUL')
//,('EAGLE NEBULA','NGC 6611,Messier 16','SER')
//,('HERCULES CLUSTER','NGC 6205,Messier 13','HER')
//,('LAGOON NEBULA','NGC 6523,Messier 8','SGR')
//,('LITTLE DUMBBELL NEBULA','NGC 650,Messier 76','PER')
//,('OMEGA NEBULA','NGC 6618,Messier 17','SGR')
//,('ORION NEBULA','NGC 1976,Messier 42','ORI')
//,('OWL NEBULA','NGC 3587,Messier 97','UMA')
//,('PINWHEEL GAL','NGC 598,Messier 33','TRI')
//,('RING NEB','NGC 6720,Messier 57','LYR')
//,('SOMBRERO GAL','NGC 4594,Messier 107','VIR')
//,('SUNFLOWER GAL','NGC 5055,Messier 63','CVN')
//,('TRIFID','NGC 6514,Messier 20','SGR')
//,('WHIRLPOOL GAL','NGC 5194,Messier 51','CVN')
//,('CRAB NEBULA','NGC 1952,Messier 1','TAU')
//,('PLEIADES','Mel 22,Messier 45','TAU')
//,('BARNARDS LOOP','Sh2-276','ORI')
//,('CAVE NEBULA','Sh2-155','CEP')
//,('FOOTPRINT NEBULA','M1-92,Minkowski 92','CYG')
//,('LOWERS NEBULA','Sh2-261','ORI')
//,('WITCH-HEAD NEB','IC 2118','ERI')
//,('SIAMESE TWINS','NGC 4567,NGC 4568','VIR')
//,('BROCCHIS CLUSTER','Cr 399','VUL')
//,('COATHANGER','Cr 399','VUL')
//,('TUFT IN THE TAIL OF THE DOG','Cr 140','CMA')
//,('ANTENNAE','NGC 4038,NGC 4039','CRV')
//,('COCOON NEBULA','IC 5146','CYG')
//,('CODDINGTONS GALAXY','IC 2574','UMA')
//,('FLAMING STAR NEBULA','IC 405','AUR')
//,('WHITE EYED PEA','IC 4593','SER')
//,('TOBY JUG NEBULA','IC 2220','CAR')
//,('PELICAN NEBULA','IC 5067','CYG')
//,('HORSEHEAD NEBULA','IC 434','ORI')
//,('RUNNING CHICKEN NEBULA','IC 2944','CEN')
//,('FISH ON A PLATTER','B 144','CYG')
//,('INKSPOT','B 86','SGR')
//,('PARROTS HEAD','B 87','SGR')
//,('INTEGRAL SIGN GALAXY','UGC 3697','CAM')
//,('MEDUSA NEBULA','ABELL 21','GEM')
//,('HYADES','Mel 25','TAU')
//,('COAL SACK','Caldwell 99','CRU')
//,('MINKOWSKIS OBJECT (GALXY IN ABELL 194 CLUS)','UNKNOWN','CET');
