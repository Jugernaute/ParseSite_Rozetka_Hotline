package compare.site.dto.searchProduct;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;

public class DtoSearchObjectDeserilizer extends JsonDeserializer<DtoSearchObject> {

    @Override
    public DtoSearchObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        DtoSearchObject dtoSearchObject = new DtoSearchObject();
        ProductSite productSite = new ProductSite();


        String site = treeNode.get("site").asText();
        String product = treeNode.get("product").asText();

        for ( EnumSite enumSite1 : EnumSite.values() ) {
            if( enumSite1.toString().equalsIgnoreCase(site) ) {
                productSite.setSite(enumSite1);
            }
        }
        for (EnumProducts enumProducts : EnumProducts.values()){
            if (enumProducts.name().equalsIgnoreCase(product)){
                productSite.setProduct(enumProducts);
            }
        }
        dtoSearchObject.setProductSite(productSite);
        dtoSearchObject.setSearch(treeNode.get("search").asText());

        Integer page = Integer.valueOf(treeNode.get("page").asText())-1;
        Integer size = Integer.valueOf(treeNode.get("size").asText());
        PageRequest of = PageRequest.of(page, size);
        dtoSearchObject.setPageable(of);

        return dtoSearchObject;
    }

}
