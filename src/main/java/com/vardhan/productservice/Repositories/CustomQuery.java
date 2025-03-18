package com.vardhan.productservice.Repositories;

public class CustomQuery {

    public static final String GET_PRODUCTS_FROM_CATEGORY_NAME = "SELECT * FROM products where category_id in (SELECT category_id from category where name = :categoryName)";
}
