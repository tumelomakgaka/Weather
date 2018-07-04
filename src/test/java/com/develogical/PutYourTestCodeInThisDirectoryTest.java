package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class PutYourTestCodeInThisDirectoryTest {
    @Test
    public void getsFromDelegateIfNotSeenBefore() {
        IForcaster delegate = mock(IForcaster.class);
        when(delegate.forecastFor(Region.LONDON, Day.FRIDAY)).thenReturn(new Forecast("cold", 6));

        CachingForecaster cf = new CachingForecaster(delegate);

        Forecast f = cf.forecastFor(Region.LONDON, Day.FRIDAY);

        verify(delegate).forecastFor(Region.LONDON, Day.FRIDAY);
        assertThat(f.temperature(), equalTo(6));
        assertThat(f.summary(), equalTo("cold"));
    }

    @Test
    public void doesNotGetFromDelegateIfSeenBefore() {
        IForcaster delegate = mock(IForcaster.class);
        when(delegate.forecastFor(Region.LONDON, Day.FRIDAY)).thenReturn(new Forecast("cold", 6));

        CachingForecaster cf = new CachingForecaster(delegate);

        Forecast f = cf.forecastFor(Region.LONDON, Day.FRIDAY);

        verify(delegate).forecastFor(Region.LONDON, Day.FRIDAY);
        assertThat(f.temperature(), equalTo(6));
        assertThat(f.summary(), equalTo("cold"));

        Forecast t = cf.forecastFor(Region.LONDON, Day.FRIDAY);
        verify(delegate,never()).forecastFor(Region.LONDON, Day.FRIDAY);
        assertThat(t.temperature(), equalTo(6));
        assertThat(t.summary(), equalTo("cold"));

    }
}