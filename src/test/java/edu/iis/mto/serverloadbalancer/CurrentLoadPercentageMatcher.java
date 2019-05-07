package edu.iis.mto.serverloadbalancer;

import com.load.Server;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedLoad;

    public CurrentLoadPercentageMatcher(double expectedLoad) {
        this.expectedLoad = expectedLoad;
    }

    @Override protected boolean matchesSafely(Server server) {
        return doublesAreEqual(expectedLoad, server.currentLoadPercentage);
    }

    private boolean doublesAreEqual(double a, double b){
        return a == b || Math.abs(a - b) < 0.01d;
    }

    @Override public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(expectedLoad);
    }

    public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedLoad){
        return new CurrentLoadPercentageMatcher(expectedLoad);
    }
}
