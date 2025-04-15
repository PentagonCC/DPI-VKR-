package com.example.vkr.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductAttributesId implements Serializable {

    private Long product;
    private Long attribute;

    public ProductAttributesId(Long product, Long attribute) {
        this.product = product;
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAttributesId)) return false;
        ProductAttributesId that = (ProductAttributesId) o;
        return Objects.equals(product, that.product) && Objects.equals(attribute, that.attribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, attribute);
    }

}
