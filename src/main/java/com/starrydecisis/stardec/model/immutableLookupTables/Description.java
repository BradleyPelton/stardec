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
public class Description {

    private static final Logger logger = LoggerFactory.getLogger(Description.class);

    @Id
    @NotNull
    @Field(type = FieldType.Text, name = "abbreviation")
    private String abbreviation;

    @NotNull
    @Field(type = FieldType.Text, name = "name")
    private String name;

    public Description() {};

    public Description(String abbreviation, String name) {
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
        return "Description{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
//('am','among')
//('att','attached')
//('bet','between')
//('B','bright')
//('b','brighter')
//('C','compressed')
//('c','considerably')
//('Cl','cluster')
//('D','double')
//('def','defined')
//('deg','degrees')
//('diam','diameter')
//('dif','diffuse')
//('E','elongated')
//('e','extremely')
//('er','easily resolved')
//('F','faint')
//('f','following')
//('g','gradually')
//('iF','irregular figure')
//('inv','involved')
//('irr','irregular')
//('L','large')
//('l','little')
//('mag','magnitude')
//('M','middle')
//('m','much')
//('n','north')
//('N','nucleus')
//('neb','nebula, nebulosity')
//('P w','paired with')
//('p','pretty (before F,B,L,S)')
//('p','preceding')
//('P','poor')
//('R','round')
//('Ri','rich')
//('r','not well resolved')
//('rr','partially resolved')
//('rrr','well resolved')
//('S','small')
//('s','suddenly')
//('s','south')
//('sc','scattered')
//('susp','suspected')
//('st','star or stellar')
//('v','very')
//('var','variable')
//('nf','north following')
//('np','north preceding')
//('sf','south following')
//('sp','south preceding')
//('11m','11th magnitude')
//('8...','8th mag and fainter')
//('9...13','9th to 13th magnitude')