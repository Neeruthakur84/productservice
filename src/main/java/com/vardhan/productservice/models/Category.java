package com.vardhan.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity

public class Category extends BaseModel{

    private String description;


//    @OneToMany(mappedBy = "category")
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER )
    @Fetch(FetchMode.JOIN )
    private List<Product> products;

}
