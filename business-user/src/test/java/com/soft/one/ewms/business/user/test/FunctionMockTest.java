package com.soft.one.ewms.business.user.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.OperationRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Title：测试controller,类似postman
 * Description：MockMvc测试
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FunctionMockTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testInsert() throws Exception{
        FunctionMenu dto = new FunctionMenu();
        dto.setFId("22");
        dto.setFName("666");
        // 参数dto,post的链接
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/function/insert/menu");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzX3VzZXIiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1ODI0NTkxNzcsImF1dGhvcml0aWVzIjpbIkZ1bmN0aW9uIiwiT3BlcmF0aW9uIiwiVXNlciJdLCJqdGkiOiI4ODRmMDk2YS01N2Q3LTQyZmMtYTFkYy1hOGQ0NmY1MTk0YWQiLCJjbGllbnRfaWQiOiJld21zIn0.vDekHcHaq1tzHqotIPyvpVw-dkYcEHu2-WQKvs1nRL0")
                .content(mapper.writeValueAsBytes(dto));
        // 打印输出
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdate() throws Exception {
        FunctionMenu dto = new FunctionMenu();
        dto.setFId("22");
        dto.setFName("666");
        dto.setDescription("测试");
        dto.setFUrl("666");
        // 参数dto,post的链接
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/function/update/menu");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzX3VzZXIiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1ODI0NTkxNzcsImF1dGhvcml0aWVzIjpbIkZ1bmN0aW9uIiwiT3BlcmF0aW9uIiwiVXNlciJdLCJqdGkiOiI4ODRmMDk2YS01N2Q3LTQyZmMtYTFkYy1hOGQ0NmY1MTk0YWQiLCJjbGllbnRfaWQiOiJld21zIn0.vDekHcHaq1tzHqotIPyvpVw-dkYcEHu2-WQKvs1nRL0")
                .content(mapper.writeValueAsBytes(dto));
        // 打印输出
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testSelect() throws Exception {
        FunctionMenu dto = new FunctionMenu();
        //dto.setFId("1");
        //dto.setFName("功能");
        dto.setDescription("用户");
        //to.setFUrl("Function");
        // 参数dto,post的链接
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/function/select/menu");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzX3VzZXIiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1ODI0NTkxNzcsImF1dGhvcml0aWVzIjpbIkZ1bmN0aW9uIiwiT3BlcmF0aW9uIiwiVXNlciJdLCJqdGkiOiI4ODRmMDk2YS01N2Q3LTQyZmMtYTFkYy1hOGQ0NmY1MTk0YWQiLCJjbGllbnRfaWQiOiJld21zIn0.vDekHcHaq1tzHqotIPyvpVw-dkYcEHu2-WQKvs1nRL0")
                .content(mapper.writeValueAsBytes(dto));
        // 打印输出
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
