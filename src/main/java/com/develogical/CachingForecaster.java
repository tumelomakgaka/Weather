package com.develogical;


import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.ArrayList;

public class CachingForecaster implements IForcaster {
    private final IForcaster delegate;
    ArrayList<ForecastData> f=new ArrayList<ForecastData>();

    public CachingForecaster(IForcaster delegate) {
        this.delegate = delegate;
    }

    @Override
    public Forecast forecastFor(Region region, Day day) {

        int k=0;
        Forecast result = delegate.forecastFor(region, day);
        while (k<f.size()-1)
        {
            ForecastData q = f.get(k);

            if (q.tempRegion == region && q.tempDay == day){
                Forecast tempForecast=new Forecast(q.tempSummary,q.tempTemperature);
                return tempForecast;
            }
            f.add(new ForecastData(region,day,result.summary(), result.temperature()));

            k=k+1;
        }

        return result;





    }
}

