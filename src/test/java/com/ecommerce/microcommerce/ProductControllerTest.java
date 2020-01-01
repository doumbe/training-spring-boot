package com.ecommerce.microcommerce;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ProductControllerTest {

    @MockBean
    private ProductDao productDao;

    @Autowired
    private MockMvc restCodificationMockMvc;

    private Product product;

    @Test
    void getAllProduits() throws Exception {
        List<Product> ProduitList = new ArrayList<Product>();
        ProduitList.add(new Product(1,"Eat thrice",200,300));
        ProduitList.add(new Product(1,"Eat ",20,300));
        when(productDao.findAll()).thenReturn(ProduitList);

        restCodificationMockMvc.perform(MockMvcRequestBuilders.get("/Produits")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

}
