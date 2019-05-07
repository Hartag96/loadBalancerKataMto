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
        return expectedLoad == server.currentLoadPercentage || Math.abs(expectedLoad - server.currentLoadPercentage) < 0.01d;
    }

    @Override public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(expectedLoad);
    }
}
