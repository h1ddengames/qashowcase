package com.h1ddengames.framework.matcher;

import java.lang.*;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Important note: THIS IS NOT MY OWN WORK.
 * It was found in this tutorial: https://www.vogella.com/tutorials/Hamcrest/article.html
 */
public class RegexMatcher extends TypeSafeMatcher<String> {
    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches regular expression=`" + regex + "`");
    }

    @Override
    public boolean matchesSafely(final String string) {
        return string.matches(regex);
    }

    // matcher method you can call on this matcher class
    public static RegexMatcher regexMatches(final String regex) {
        return new RegexMatcher(regex);
    }
}
