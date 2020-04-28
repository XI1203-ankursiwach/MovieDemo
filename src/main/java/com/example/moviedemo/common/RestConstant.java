package com.example.moviedemo.common;

public interface RestConstant {

    String SLASH = "/";

    String API = SLASH + "api";

    String VERSION_v1 = "v1";

    String CURRENT_VERSION = VERSION_v1;

    String MOVIE_DATA_API = API + SLASH + CURRENT_VERSION;

    interface MovieData {

        String MOVIE_API = SLASH + "movie";

        String MOVIE_API_BY_ID = SLASH + "movie" + SLASH + "{id}";
    }
}
