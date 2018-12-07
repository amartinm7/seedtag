package org.amm.seedtag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amm.seedtag.model.message.RequestMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RadarControllerMvcIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarControllerMvcIT.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        final String expected = "{\"x\":0,\"y\":40}";
        this.mockMvc.perform(get("/radar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }


    public void doPost(String provided, String expected) throws Exception {
        this.mockMvc.perform(post("/radar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(provided)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void test1() throws Exception {
        final String provided = "{\"protocols\":[\"avoid-mech\"],\"scans\":[{\"coordinates\":{\"x\":0,\"y\":40},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":80},\"allies\":5,\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        final String expected = "{\"x\":0,\"y\":40}";
        doPost(provided, expected);
    }

    @Test
    public void test2() throws Exception {
        final String provided = "{\"protocols\":[\"prioritize-mech\"],\"scans\":[{\"coordinates\":{\"x\":0,\"y\":40},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":80},\"allies\":5,\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final String expected = "{\"x\":0,\"y\":80}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test3() throws Exception {
        final String provided = "{\"protocols\":[\"closest-enemies\"],\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"y\":35,\"x\":5}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":30,\"x\":10}}]}";
        final String expected = "{\"x\":10,\"y\":30}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test4() throws Exception {
        final String provided = "{\"protocols\":[\"furthest-enemies\"],\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"y\":35,\"x\":5}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":30,\"x\":10}}]}";
        final String expected = "{\"x\":5,\"y\":35}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test5() throws Exception {
        final String provided = "{\"protocols\":[\"assist-allies\"],\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"allies\":3,\"coordinates\":{\"y\":35,\"x\":5}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":5,\"x\":35}}]}";
        final String expected = "{\"x\":5,\"y\":35}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test6() throws Exception {
        final String provided = "{\"protocols\":[\"avoid-crossfire\"],\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"allies\":3,\"coordinates\":{\"y\":35,\"x\":5}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":5,\"x\":35}}]}";
        final String expected = "{\"x\":35,\"y\":5}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test7() throws Exception {
        final String provided = "{\"protocols\":[\"furthest-enemies\"],\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"y\":35,\"x\":5}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":30,\"x\":10}}]}";
        final String expected = "{\"x\":5,\"y\":35}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test8() throws Exception {
        final String provided = "{\"protocols\":[\"closest-enemies\",\"avoid-mech\"],\"scans\":[{\"coordinates\":{\"x\":0,\"y\":1},\"enemies\":{\"type\":\"mech\",\"number\":1}},{\"coordinates\":{\"x\":0,\"y\":10},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":99},\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final String expected = "{\"x\":0,\"y\":10}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test9() throws Exception {
        final String provided = "{\"protocols\":[\"closest-enemies\"],\"scans\":[{\"coordinates\":{\"x\":89,\"y\":13},\"enemies\":{\"type\":\"mech\",\"number\":1}},{\"coordinates\":{\"x\":11,\"y\":35},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":19,\"y\":49},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":38,\"y\":21},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":10,\"y\":39},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":13,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":13,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":15,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":22,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":94,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":90,\"y\":18},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":80,\"y\":51},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":70,\"y\":91},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":95},\"enemies\":{\"type\":\"mech\",\"number\":20}},{\"coordinates\":{\"x\":1,\"y\":89},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":3,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":54,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":22,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":3,\"y\":10},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":43,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":51,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":91,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":11,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":91,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":51,\"y\":22},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":10},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":11,\"y\":84},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":91,\"y\":65},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":81,\"y\":53},\"enemies\":{\"type\":\"mech\",\"number\":30}},{\"coordinates\":{\"x\":15,\"y\":70},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":19,\"y\":83},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":11,\"y\":46},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":59,\"y\":26},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":98,\"y\":57},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":11,\"y\":58},\"enemies\":{\"type\":\"mech\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":39},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":83,\"y\":37},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":11},\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final String expected = "{\"x\":3,\"y\":10}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test10() throws Exception {
        final String provided = "{\"protocols\":[\"furthest-enemies\"],\"scans\":[{\"coordinates\":{\"x\":89,\"y\":13},\"enemies\":{\"type\":\"mech\",\"number\":1}},{\"coordinates\":{\"x\":11,\"y\":35},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":19,\"y\":49},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":38,\"y\":21},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":10,\"y\":39},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":13,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":13,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":15,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":22,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":94,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":90,\"y\":18},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":80,\"y\":51},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":70,\"y\":91},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":95},\"enemies\":{\"type\":\"mech\",\"number\":20}},{\"coordinates\":{\"x\":1,\"y\":89},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":3,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":54,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":22,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":3,\"y\":10},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":43,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":51,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":91,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":11,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":91,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":51,\"y\":22},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":10},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":11,\"y\":84},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":91,\"y\":65},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":81,\"y\":53},\"enemies\":{\"type\":\"mech\",\"number\":30}},{\"coordinates\":{\"x\":15,\"y\":70},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":19,\"y\":83},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":11,\"y\":46},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":59,\"y\":26},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":98,\"y\":57},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":11,\"y\":58},\"enemies\":{\"type\":\"mech\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":39},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":83,\"y\":37},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":11},\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final String expected = "{\"x\":30,\"y\":95}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test11() throws Exception {
        final String provided = "{\"protocols\":[\"furthest-enemies\",\"avoid-mech\"],\"scans\":[{\"coordinates\":{\"x\":89,\"y\":13},\"enemies\":{\"type\":\"mech\",\"number\":1}},{\"coordinates\":{\"x\":11,\"y\":35},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":19,\"y\":49},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":38,\"y\":21},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":10,\"y\":39},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":13,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":13,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":15,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":22,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":94,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":90,\"y\":18},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":80,\"y\":51},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":70,\"y\":91},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":95},\"enemies\":{\"type\":\"mech\",\"number\":20}},{\"coordinates\":{\"x\":1,\"y\":89},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":3,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":54,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":22,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":3,\"y\":10},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":43,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":51,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":91,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":11,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":91,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":51,\"y\":22},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":10},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":11,\"y\":84},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":91,\"y\":65},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":81,\"y\":53},\"enemies\":{\"type\":\"mech\",\"number\":30}},{\"coordinates\":{\"x\":15,\"y\":70},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":19,\"y\":83},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":11,\"y\":46},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":59,\"y\":26},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":98,\"y\":57},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":11,\"y\":58},\"enemies\":{\"type\":\"mech\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":39},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":83,\"y\":37},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":11},\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final String expected = "{\"x\":91,\"y\":30}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test12() throws Exception {
        final String provided = "{\"protocols\":[\"closest-enemies\",\"prioritize-mech\"],\"scans\":[{\"enemies\":{\"number\":1,\"type\":\"mech\"},\"coordinates\":{\"x\":89,\"y\":13}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"allies\":3,\"coordinates\":{\"y\":35,\"x\":11}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"coordinates\":{\"y\":49,\"x\":19}},{\"enemies\":{\"type\":\"soldier\",\"number\":30},\"allies\":5,\"coordinates\":{\"y\":21,\"x\":38}},{\"enemies\":{\"number\":30,\"type\":\"soldier\"},\"allies\":8,\"coordinates\":{\"x\":10,\"y\":39}},{\"enemies\":{\"type\":\"soldier\",\"number\":15},\"coordinates\":{\"x\":13,\"y\":38}},{\"enemies\":{\"number\":60,\"type\":\"soldier\"},\"coordinates\":{\"x\":13,\"y\":15}},{\"enemies\":{\"number\":40,\"type\":\"soldier\"},\"coordinates\":{\"y\":19,\"x\":30}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"number\":20,\"type\":\"soldier\"}},{\"coordinates\":{\"x\":15,\"y\":19},\"allies\":11,\"enemies\":{\"number\":80,\"type\":\"soldier\"}},{\"coordinates\":{\"x\":22,\"y\":15},\"allies\":13,\"enemies\":{\"number\":10,\"type\":\"soldier\"}},{\"coordinates\":{\"y\":19,\"x\":10},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"allies\":15,\"coordinates\":{\"x\":94,\"y\":11}},{\"enemies\":{\"number\":30,\"type\":\"soldier\"},\"coordinates\":{\"x\":10,\"y\":19}},{\"enemies\":{\"type\":\"soldier\",\"number\":30},\"allies\":16,\"coordinates\":{\"x\":90,\"y\":18}},{\"enemies\":{\"number\":15,\"type\":\"soldier\"},\"allies\":5,\"coordinates\":{\"y\":51,\"x\":80}},{\"enemies\":{\"type\":\"soldier\",\"number\":60},\"allies\":5,\"coordinates\":{\"y\":91,\"x\":70}},{\"enemies\":{\"type\":\"soldier\",\"number\":40},\"coordinates\":{\"y\":11,\"x\":30}},{\"enemies\":{\"type\":\"mech\",\"number\":20},\"coordinates\":{\"y\":95,\"x\":30}},{\"enemies\":{\"type\":\"soldier\",\"number\":80},\"allies\":8,\"coordinates\":{\"x\":1,\"y\":89}},{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"x\":3,\"y\":11}},{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"x\":54,\"y\":19}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"coordinates\":{\"x\":22,\"y\":38}},{\"enemies\":{\"number\":30,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"y\":10,\"x\":3}},{\"coordinates\":{\"x\":43,\"y\":13},\"enemies\":{\"number\":30,\"type\":\"soldier\"}},{\"enemies\":{\"number\":15,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"x\":51,\"y\":13}},{\"coordinates\":{\"y\":30,\"x\":91},\"allies\":10,\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"y\":30,\"x\":11},\"enemies\":{\"number\":40,\"type\":\"soldier\"}},{\"enemies\":{\"type\":\"soldier\",\"number\":20},\"coordinates\":{\"x\":91,\"y\":15}},{\"enemies\":{\"number\":80,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"y\":22,\"x\":51}},{\"coordinates\":{\"x\":91,\"y\":10},\"enemies\":{\"number\":10,\"type\":\"mech\"}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"coordinates\":{\"x\":11,\"y\":84}},{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"x\":91,\"y\":65}},{\"enemies\":{\"number\":30,\"type\":\"mech\"},\"allies\":3,\"coordinates\":{\"y\":53,\"x\":81}},{\"coordinates\":{\"y\":70,\"x\":15},\"allies\":4,\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"enemies\":{\"type\":\"soldier\",\"number\":15},\"allies\":4,\"coordinates\":{\"y\":83,\"x\":19}},{\"enemies\":{\"number\":60,\"type\":\"soldier\"},\"coordinates\":{\"y\":46,\"x\":11}},{\"coordinates\":{\"y\":26,\"x\":59},\"allies\":6,\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"allies\":6,\"coordinates\":{\"x\":98,\"y\":57}},{\"enemies\":{\"number\":80,\"type\":\"mech\"},\"coordinates\":{\"x\":11,\"y\":58}},{\"enemies\":{\"number\":10,\"type\":\"mech\"},\"coordinates\":{\"x\":91,\"y\":39}},{\"coordinates\":{\"x\":83,\"y\":37},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"enemies\":{\"type\":\"mech\",\"number\":1},\"allies\":6,\"coordinates\":{\"y\":11,\"x\":0}}]}";
        final String expected = "{\"x\":0,\"y\":11}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

    @Test
    public void test13() throws Exception {
        final String provided = "{\"protocols\":[\"closest-enemies\",\"prioritize-mech\",\"avoid-crossfire\"],\"scans\":[{\"enemies\":{\"number\":1,\"type\":\"mech\"},\"coordinates\":{\"x\":89,\"y\":13}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"allies\":3,\"coordinates\":{\"y\":35,\"x\":11}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"coordinates\":{\"y\":49,\"x\":19}},{\"enemies\":{\"type\":\"soldier\",\"number\":30},\"allies\":5,\"coordinates\":{\"y\":21,\"x\":38}},{\"enemies\":{\"number\":30,\"type\":\"soldier\"},\"allies\":8,\"coordinates\":{\"x\":10,\"y\":39}},{\"enemies\":{\"type\":\"soldier\",\"number\":15},\"coordinates\":{\"x\":13,\"y\":38}},{\"enemies\":{\"number\":60,\"type\":\"soldier\"},\"coordinates\":{\"x\":13,\"y\":15}},{\"enemies\":{\"number\":40,\"type\":\"soldier\"},\"coordinates\":{\"y\":19,\"x\":30}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"number\":20,\"type\":\"soldier\"}},{\"coordinates\":{\"x\":15,\"y\":19},\"allies\":11,\"enemies\":{\"number\":80,\"type\":\"soldier\"}},{\"coordinates\":{\"x\":22,\"y\":15},\"allies\":13,\"enemies\":{\"number\":10,\"type\":\"soldier\"}},{\"coordinates\":{\"y\":19,\"x\":10},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"allies\":15,\"coordinates\":{\"x\":94,\"y\":11}},{\"enemies\":{\"number\":30,\"type\":\"soldier\"},\"coordinates\":{\"x\":10,\"y\":19}},{\"enemies\":{\"type\":\"soldier\",\"number\":30},\"allies\":16,\"coordinates\":{\"x\":90,\"y\":18}},{\"enemies\":{\"number\":15,\"type\":\"soldier\"},\"allies\":5,\"coordinates\":{\"y\":51,\"x\":80}},{\"enemies\":{\"type\":\"soldier\",\"number\":60},\"allies\":5,\"coordinates\":{\"y\":91,\"x\":70}},{\"enemies\":{\"type\":\"soldier\",\"number\":40},\"coordinates\":{\"y\":11,\"x\":30}},{\"enemies\":{\"type\":\"mech\",\"number\":20},\"coordinates\":{\"y\":95,\"x\":30}},{\"enemies\":{\"type\":\"soldier\",\"number\":80},\"allies\":8,\"coordinates\":{\"x\":1,\"y\":89}},{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"x\":3,\"y\":11}},{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"x\":54,\"y\":19}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"coordinates\":{\"x\":22,\"y\":38}},{\"enemies\":{\"number\":30,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"y\":10,\"x\":3}},{\"coordinates\":{\"x\":43,\"y\":13},\"enemies\":{\"number\":30,\"type\":\"soldier\"}},{\"enemies\":{\"number\":15,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"x\":51,\"y\":13}},{\"coordinates\":{\"y\":30,\"x\":91},\"allies\":10,\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"y\":30,\"x\":11},\"enemies\":{\"number\":40,\"type\":\"soldier\"}},{\"enemies\":{\"type\":\"soldier\",\"number\":20},\"coordinates\":{\"x\":91,\"y\":15}},{\"enemies\":{\"number\":80,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"y\":22,\"x\":51}},{\"coordinates\":{\"x\":91,\"y\":10},\"enemies\":{\"number\":10,\"type\":\"mech\"}},{\"enemies\":{\"type\":\"soldier\",\"number\":10},\"coordinates\":{\"x\":11,\"y\":84}},{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"allies\":10,\"coordinates\":{\"x\":91,\"y\":65}},{\"enemies\":{\"number\":30,\"type\":\"mech\"},\"allies\":3,\"coordinates\":{\"y\":53,\"x\":81}},{\"coordinates\":{\"y\":70,\"x\":15},\"allies\":4,\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"enemies\":{\"type\":\"soldier\",\"number\":15},\"allies\":4,\"coordinates\":{\"y\":83,\"x\":19}},{\"enemies\":{\"number\":60,\"type\":\"soldier\"},\"coordinates\":{\"y\":46,\"x\":11}},{\"coordinates\":{\"y\":26,\"x\":59},\"allies\":6,\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"allies\":6,\"coordinates\":{\"x\":98,\"y\":57}},{\"enemies\":{\"number\":80,\"type\":\"mech\"},\"coordinates\":{\"x\":11,\"y\":58}},{\"enemies\":{\"number\":10,\"type\":\"mech\"},\"coordinates\":{\"x\":91,\"y\":39}},{\"coordinates\":{\"x\":83,\"y\":37},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"enemies\":{\"type\":\"mech\",\"number\":1},\"allies\":6,\"coordinates\":{\"y\":11,\"x\":0}}]}";
        final String expected = "{\"x\":11,\"y\":58}";
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        doPost(provided, expected);
    }

}
