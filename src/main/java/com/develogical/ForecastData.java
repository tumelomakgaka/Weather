package com.develogical;

import com.weather.Day;
import com.weather.Region;

public class ForecastData {
    public final Day tempDay;
    public final Region tempRegion;
    public final int tempTemperature;
    public final String tempSummary;

    public ForecastData(Region region, Day day, String summary, int temperature) {
        tempRegion=region;
        tempDay=day;
        tempSummary=summary;
        tempTemperature=temperature;
    }


}
