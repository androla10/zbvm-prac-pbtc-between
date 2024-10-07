package pe.com.zbvm.infrastructure.adapters.input.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * <br/> PriceManagementAdapterTest <br/>
 * <b>Class</b>: PriceManagementAdapterTest<br/>
 * Copyright: &copy; 2024 Inditex.<br/>
 * Company: Inditex.<br/>
 *
 * @author Inditex <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>Zeler Benji Villarreal Marcelo</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Oct 06, 2024 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class PriceManagementAdapterTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Given The Data Is Loaded With Four Records When Request Data Of The Service Then The Api Shows All Four Records")
  public void givenTheDataIsLoadedWithFourRecords_WhenRequestDataOfTheService_ThenTheApiShowsAllFourRecords()
      throws Exception {
    var expectedLoadedRecords = 4;

    ResultActions resultActions = mockMvc.perform(get("/prices"));

    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(expectedLoadedRecords)));
  }

  @DisplayName("Given The Data Is Loaded With Four Records ")
  @ParameterizedTest(name =
      "When service data is requested at {0}:00 hrs on the {1}th for product {2} and brand {3}" +
          " Then The final price is {4}")
  @MethodSource("returnAllCasesForPrices")
  public void test1(int hour, int day, Long productId, Long brandId, BigDecimal priceExpected)
      throws Exception {
    var expectedRecord = 1;
    var consultationDate = OffsetDateTime.of(2020, 6, day, hour, 0, 0, 0, ZoneOffset.UTC);

    ResultActions resultActions = mockMvc.perform(get("/prices")
        .param("consultationDate", consultationDate.toString())
        .param("productId", productId.toString())
        .param("brandId", brandId.toString()));

    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(expectedRecord)))
        .andExpect(jsonPath("$[0].price").value(priceExpected.doubleValue()));
  }

  static Stream<Arguments> returnAllCasesForPrices() {
    return Stream.of(Arguments.of(10, 14, 35455L, 1L, new BigDecimal("35.5")),
        Arguments.of(16, 14, 35455L, 1L, new BigDecimal("25.45")),
        Arguments.of(21, 14, 35455L, 1L, new BigDecimal("35.5")),
        Arguments.of(10, 15, 35455L, 1L, new BigDecimal("30.5")),
        Arguments.of(21, 16, 35455L, 1L, new BigDecimal("38.95"))
    );
  }

}