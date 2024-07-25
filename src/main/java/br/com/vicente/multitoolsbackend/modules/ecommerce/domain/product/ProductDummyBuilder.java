package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryBuilder;

public class ProductDummyBuilder {
    private final ProductBuilder productBuilder;

    public ProductDummyBuilder(final ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }

   public ProductBuilder builder(){

       this.productBuilder
                .withId(ProductID.generate())
                .withName("Product Name")
                .withCategory(CategoryBuilder.builderDummy().rebuild());

        return this.productBuilder;

   }
   public Product build(){
       return new Product(this.productBuilder);
   }

}
