package teachmakitra.microservices.spring.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teachmakitra.microservices.spring.product.dto.ProductDto;
import teachmakitra.microservices.spring.product.model.ProductModel;

@Mapper
public interface ProductModelMapper {

    ProductModelMapper INSTANCE = Mappers.getMapper(ProductModelMapper.class);

    ProductDto toProductDto(ProductModel productModel);


}
