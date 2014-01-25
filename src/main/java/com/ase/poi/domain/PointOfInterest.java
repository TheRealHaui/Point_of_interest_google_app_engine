package com.ase.poi.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PointOfInterest {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    @NotNull
    private Double latitude;

    /**
     */
    @NotNull
    private Double longitude;

    /**
     */
    @NotNull
    private String creator;

    /**
     */
    @NotNull
    private String description;

    /**
     */
    @NotNull
    private String category;
}
