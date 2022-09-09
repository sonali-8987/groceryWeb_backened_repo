package com.example.dailyneed_backened_repo.quantity;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QuantityTest {

    @Test
    void shouldEquateOneKGToOneKG() {
        Quantity oneKilogram = Quantity.createKilogram(BigDecimal.ONE);
        Quantity anotherOneKilogram = Quantity.createKilogram(BigDecimal.ONE);

        assertThat(oneKilogram, is(equalTo(anotherOneKilogram)));
    }

}