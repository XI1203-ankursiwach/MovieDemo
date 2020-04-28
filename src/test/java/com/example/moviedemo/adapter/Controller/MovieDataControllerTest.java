package com.example.moviedemo.adapter.Controller;

import com.example.moviedemo.domain.model.dto.MovieDataRequest;
import com.example.moviedemo.domain.model.dto.MovieDataResponse;
import com.example.moviedemo.domain.service.MovieDataServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviedemo.common.RestConstant.MOVIE_DATA_API;
import static com.example.moviedemo.common.RestConstant.MovieData.MOVIE_API;
import static com.example.moviedemo.common.RestConstant.MovieData.MOVIE_API_BY_ID;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieDataController.class)
public class MovieDataControllerTest {

    @Autowired
    MockMvc mockMVC;

    @MockBean
    private MovieDataServiceImpl movieDataService;

    @Test
    public void createMovieData() throws Exception {
        MovieDataRequest movieDataRequest = MovieDataRequest.of(1, "xyz", "action", 4.0);
        MovieDataResponse movieDataResponse = MovieDataResponse.of(1, "xyz", "action", 4.0);

        when(movieDataService.saveMovieData(movieDataRequest)).thenReturn(movieDataResponse);

        ObjectMapper mapper = new ObjectMapper();
        this.mockMVC.perform(MockMvcRequestBuilders.post(MOVIE_DATA_API + MOVIE_API)
                .content(mapper.writeValueAsString(movieDataRequest)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("xyz"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("action"));
    }

    @Test
    public void getAllMovieData() throws Exception {
        List<MovieDataResponse> movieDataResponseList = new ArrayList<>();
        MovieDataResponse movieDataResponse = MovieDataResponse.of(1, "xyz", "action", 4.0);
        movieDataResponseList.add(movieDataResponse);

        when(movieDataService.getAllMovieData()).thenReturn(movieDataResponseList);

        this.mockMVC.perform(MockMvcRequestBuilders.get(MOVIE_DATA_API + MOVIE_API))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getMovieDataById() throws Exception {
        MovieDataResponse movieDataResponse = MovieDataResponse.of(1, "xyz", "action", 4.0);
        when(movieDataService.getMovieDataById(1)).thenReturn(movieDataResponse);

        this.mockMVC.perform(MockMvcRequestBuilders.get(MOVIE_DATA_API + MOVIE_API_BY_ID, 1))
                .andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("xyz"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value("4.0"));
    }

    @Test
    public void updateMovieData() throws Exception {
        MovieDataRequest movieDataRequest = MovieDataRequest.of(1, "xyz", "action", 4.0);
        MovieDataResponse movieDataResponse = MovieDataResponse.of(1, "xyz", "action", 4.0);

        when(movieDataService.updateMovieData(movieDataRequest)).thenReturn(movieDataResponse);

        ObjectMapper mapper = new ObjectMapper();
        this.mockMVC.perform(MockMvcRequestBuilders.put(MOVIE_DATA_API + MOVIE_API).content(mapper.writeValueAsString(movieDataRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("xyz"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("action"));
    }

    @Test
    public void deleteMovieDataById() throws Exception {
        doNothing().when(movieDataService).deleteMovieData(1);

        this.mockMVC.perform(MockMvcRequestBuilders.delete(MOVIE_DATA_API + MOVIE_API_BY_ID, 1))
                .andDo(print()).andExpect(status().isOk());
    }
}