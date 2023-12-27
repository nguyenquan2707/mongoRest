package com.example.reactivemongo;

import com.example.reactivemongo.controller.ProductController;
import com.example.reactivemongo.dto.ProductDto;
import com.example.reactivemongo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class ReactiveMongoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    public void addProductTest() {

        Mono<ProductDto> productDto = Mono.just(new ProductDto("1,", "MacPro", 2, 10.0));
        Mockito.when(productService.saveProduct(productDto))
                .thenReturn(productDto);

        webTestClient.post().uri("/products")
//                .body(Mono.just(productDto),ProductDto.class)
                .body(productDto,ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getProductsTest() {
        Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("1,", "MacPro", 2, 10.0),
                new ProductDto("2,", "MacPro2", 3 , 10.0));


        Mockito.when(productService.getProducts()).thenReturn(productDtoFlux);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto("1,", "MacPro", 2, 10.0))
                .expectNext(new ProductDto("2,", "MacPro2", 3, 10.0))
                .verifyComplete();
    }

    @Test
    public void getProduct() {
        Mono<ProductDto> productDto = Mono.just(new ProductDto("1,", "MacPro", 2, 10.0));

        Mockito.when(productService.getProduct(Mockito.anyString())).thenReturn(productDto);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products/102")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(productDtoResponse -> productDtoResponse.getName().equals("MacPro"))
                .verifyComplete();
    }

    @Test
    public void updateProductTest() {
        Mono<ProductDto> productDto = Mono.just(new ProductDto("1,", "MacPro", 2, 10.0));
        Mockito.when(productService.updateProduct(productDto, "102")).thenReturn(productDto);

        WebTestClient.ResponseSpec ok = webTestClient.put().uri("/products/update/102")
                .body(productDto, ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deleteProductTest() {
        Mono<ProductDto> productDto = Mono.just(new ProductDto("1,", "MacPro", 2, 10.0));
        Mockito.when(productService.delete(Mockito.anyString())).thenReturn(Mono.empty());

        webTestClient.delete().uri("/products/102")
                .exchange()
                .expectStatus().isOk();
    }

}
