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
//@Document(indexName = "bodynameindex")  // Should this even have an index? It's a lookup table
public class BodyName {

    private static final Logger logger = LoggerFactory.getLogger(BodyName.class);

    @Id
    @NotNull
    @Field(type = FieldType.Text, name = "abbreviation")
    private String abbreviation;

    @NotNull
    @Field(type = FieldType.Text, name = "name")
    private String name;

    public BodyName() {};

    public BodyName(String abbreviation, String name) {
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
        return "BodyName{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

//"abbreviation","name"
//"3C",Third Cambridge Catalog of Radio Wave Sources
//Abell,George Abell (planetary nebulae and galaxy clusters)
//ADS,Aitken Double Star catalog
//AM,Arp-Madore (globular clusters)
//Antalova,(open clusters)
//Ap,Apriamasvili (planetary nebulae)
//Arp,Halton Arp (interacting galaxies)
//Bark,Barkhatova (open clusters)
//B,Barnard (dark nebulae)
//Basel,(open clusters)
//BD,Bonner Durchmusterung (stars)
//Berk,Berkeley (open clusters)
//Be,Bernes (dark nebulae)
//Biur,Biurakan (open clusters)
//Blanco,(open clusters)
//Bochum,(open clusters)
//Ced,Cederblad (bright nebulae)
//CGCG,Catalog of Galaxies and Clusters of Galaxies
//Cr,Collinder (open clusters)
//Czernik,(open clusters)
//DDO,David Dunlap Observatory (dwarf galaxies)
//Do,Dolidze (open clusters)
//DoDz,Dolidze-Dzimselejsvili (open clusters)
//Dun,Dunlop (Southern objects of all types)
//ESO,European Southern Observatory (Southern objects)
//Fein,Feinstein (open clusters)
//Frolov,(open clusters)
//Gum,(bright nebulae)
//H,William Herschel (globular clusters)
//Haffner,(open clusters)
//Harvard,(open clusters)
//Hav-Moffat,Havermeyer and Moffat (open clusters)
//He,Henize (planetary nebulae)
//Hogg,(open clusters)
//Ho,Holmberg (galaxies)
//HP,Haute Provence (globular clusters)
//Hu,Humason (planetary nebulae)
//IC,"1st and 2nd Index Catalogs to the NGC"
//Isk,Iskudarian (open clusters)
//J,Jonckheere (planetary nebulae)
//K,Kohoutek (planetary nebulae)
//Kemble,Father Lucian Kemble (asterisms)
//King,(open clusters)
//Kr,Krasnogorskaja (planetary nebulae)
//Lac,Lacaille (globular clusters)
//Loden,(open clusters)
//LBN,Lynds (bright nebula)
//LDN,Lynds (dark nebulae)
//NPM1G,"Northern Proper Motion, 1st part, Galaxies"
//Lynga,(open clusters)
//M,Messier (all types of objects except dark nebula)
//MCG,Morphological Catalog of Galaxies
//Me,Merrill (plantary nebulae)
//Mrk,Markarian (open clusters and galaxies)
//Mel,Melotte (open clusters)
//M1 thru M4,Minkowski (planetary nebulae)
//New,"""New"" galaxies in the Revised Shapley-Ames Catalog"
//NGC,New General Catalog of Nebulae & Clusters of Stars.
//Pal,Palomar (globular clusters)
//PB,Peimbert and Batiz (planetary nebulae)
//PC,Peimbert and Costero (planetary nebulae)
//Pismis,(open clusters)
//PK,Perek & Kohoutek (planetary nebulae)
//RCW,"Rodgers, Campbell, & Whiteoak (bright nebulae)"
//Roslund,(open clusters)
//Ru,Ruprecht (open clusters)
//Sa,Sandqvist (dark nebulae)
//Sher,(open clusters)
//Sh,Sharpless (bright nebulae)
//SL,"Sandqvist & Lindroos (dark nebulae), Shapley & Lindsay (clusters in LMC)"
//Steph,Stephenson (open clusters)
//Stock,(open clusters)
//Ter,Terzan (globular clusters)
//Tombaugh,(open clusters)
//Ton,Tonantzintla (globular clusters)
//Tr,Trumpler (open clusters)
//UGC,Uppsala General Catalog (galaxies)
//UGCA,"Uppsala General Catalog, Addendum (galaxies)"
//UKS,United Kingdom Schmidt (globular clusters)
//Upgren,(open clusters)
//V V,Vorontsov-Velyaminov (interacting galaxies)
//vdB,"van den Bergh (open clusters, bright nebulae)"
//vdBH,van den Bergh & Herbst (bright nebulae)
//vdB-Ha,van den Bergh-Hagen (open clusters)
//Vy,Vyssotsky (planetary nebulae)
//Waterloo,(open clusters)
//Winnecke,Double Star (Messier 40)
//ZwG,Zwicky (galaxies)