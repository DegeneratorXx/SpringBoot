package net.LakshitJournal.journalApp.Services;

import net.LakshitJournal.journalApp.ResponseAPI.WeatherResponse;
import net.LakshitJournal.journalApp.cache.AppCache;
import net.LakshitJournal.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    public  String apikey;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse=redisService.get("weather_of_"+city,WeatherResponse.class);
        if(weatherResponse!=null)
        {
        return weatherResponse;
        }
        else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY ,apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if(body!=null)
                redisService.set("weather_of_"+city, body,3001);
            return body;

        }
    }
}
