package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryDummyBuilder;

public class ProductDummyBuilder {
    private final ProductBuilder productBuilder;

    public ProductDummyBuilder(final ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }

   public ProductBuilder builder(){

       final CategoryBuilder categoryBuilder = CategoryBuilder.builder();
       final CategoryDummyBuilder categoryDummyBuilder = new CategoryDummyBuilder(categoryBuilder);
       this.productBuilder
                .withId(ProductID.generate())
                .withName("Product Name")
                .withCategory(categoryDummyBuilder.builder().build());
        return this.productBuilder;

   }
   public Product build(){
       return new Product(this.productBuilder);
   }

}
