package pe.com.zbvm.infrastructure.adapters.input.rest.handlers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pe.com.zbvm.infrastructure.adapters.input.rest.PriceManagementAdapter;

/**
 * <br/> GlobalExceptionHandlerTest <br/>
 * <b>Class</b>: GlobalExceptionHandlerTest<br/>
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
 * <li>Apr 02, 2025 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@WebMvcTest(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PriceManagementAdapter priceManagementAdapter;

  @Test
  void handleIllegalArgumentException() throws Exception {
    ResultActions resultActions = mockMvc.perform(get("/prices")
        .param("consultationDate", "2020-06-16T21:00:00-05:00sss"));

    resultActions
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(400))
        .andExpect(jsonPath("$.message").value(
            "El parámetro 'consultationDate' recibió el valor '2020-06-16T21:00:00-05:00sss' que no es del tipo esperado 'OffsetDateTime'."))
        .andExpect(jsonPath("$.type").value("BAD_REQUEST"));
  }
}