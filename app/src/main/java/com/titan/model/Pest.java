package com.titan.model;

import android.graphics.Bitmap;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by whs on 2017/10/26
 * 有害生物
 */
@Entity
public class Pest implements Serializable{
    /**主键*/
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    /**中文学名*/
    @Property(nameInDb = "CNAME")
    private String cname;
    /**拉丁学名*/
    @Property(nameInDb = "LNAME")
    private String lname;
    /**英文学名*/
    @Property(nameInDb = "ENAME")
    private String ename;
    /**别名*/
    @Property(nameInDb = "ALIAS")
    private String alias;
    /**界*/
    @Property(nameInDb = "KINGDOM")
    private String kindom;
    /**门*/
    @Property(nameInDb = "PHYLUM")
    private String phylum;
    /**纲*/
    @Property(nameInDb = "CLASS")
    private String tclass;
    /**目*/
    @Property(nameInDb = "TORDER")
    private String orider;
    /**科*/
    @Property(nameInDb = "Family")
    private String family;
    /**属*/
    @Property(nameInDb = "GENUS")
    private String genus;
    /**种*/
    @Property(nameInDb = "SPECIES")
    private String species;
    /**分布*/
    @Property(nameInDb = "Distribution")
    private String distribution;
    /**特征*/
    @Property(nameInDb = "FEATURE")
    private String feature;
    /**类型*/
    @Property(nameInDb = "TYPE")
    private String type;
    /**防治措施*/
    @Property(nameInDb = "CONTROL")
    private String control;
    /**寄主*/
    @Property(nameInDb = "HOST")
    private String host;
    @Transient
    private Bitmap bitmap;

    @Transient
    private boolean hasimg=false;

    @Generated(hash = 733795870)
    public Pest(Long id, String cname, String lname, String ename, String alias,
            String kindom, String phylum, String tclass, String orider,
            String family, String genus, String species, String distribution,
            String feature, String type, String control, String host) {
        this.id = id;
        this.cname = cname;
        this.lname = lname;
        this.ename = ename;
        this.alias = alias;
        this.kindom = kindom;
        this.phylum = phylum;
        this.tclass = tclass;
        this.orider = orider;
        this.family = family;
        this.genus = genus;
        this.species = species;
        this.distribution = distribution;
        this.feature = feature;
        this.type = type;
        this.control = control;
        this.host = host;
    }
    @Generated(hash = 495447472)
    public Pest() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCname() {
        return this.cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getLname() {
        return this.lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getEname() {
        return this.ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getKindom() {
        return this.kindom;
    }
    public void setKindom(String kindom) {
        this.kindom = kindom;
    }
    public String getPhylum() {
        return this.phylum;
    }
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }
    public String getTclass() {
        return this.tclass;
    }
    public void setTclass(String tclass) {
        this.tclass = tclass;
    }
    public String getOrider() {
        return this.orider;
    }
    public void setOrider(String orider) {
        this.orider = orider;
    }
    public String getFamily() {
        return this.family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public String getGenus() {
        return this.genus;
    }
    public void setGenus(String genus) {
        this.genus = genus;
    }
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getDistribution() {
        return this.distribution;
    }
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }
    public String getFeature() {
        return this.feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getControl() {
        return this.control;
    }
    public void setControl(String control) {
        this.control = control;
    }
    public String getHost() {
        return this.host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isHasimg() {
        return hasimg;
    }

    public void setHasimg(boolean hasimg) {
        this.hasimg = hasimg;
    }
}
