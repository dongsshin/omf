package com.rap.omc.util.core;

import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;

public class PatternUtil{

    private static final Pattern ESCAPE_PATTERN = Pattern.compile("(\\.|\\\\|\\[|\\]|\\^|\\$|\\+|\\{|\\}|\\(|\\)|\\|)");
    private static final Pattern ASTERISK_PATTERN = Pattern.compile("\\*");
    private static final Pattern QUESTION_PATTERN = Pattern.compile("\\?");
    private static final Pattern MULTIPLE_PATTERN = Pattern.compile("\\;");
    public static Pattern compileWildcardPattern(String wildcard)
    {
        return compileWildcardPattern(wildcard, false);
    }
    public static Pattern[] compileWildcardPattern(List<String> wildcard)
    {
        Pattern[] patterns = new Pattern[wildcard.size()];
        for (int i = 0; i < wildcard.size(); ++i) {
            patterns[i] = compileWildcardPattern(((String)wildcard.get(i)).trim(), false);
        }
        /*  86 */ return patterns;
        /*     */ }

    /*     */
    /*     */ public static boolean matches(Pattern pattern, String string)
    /*     */ {
        /*  97 */ return pattern.matcher(string).matches();
        /*     */ }

    /*     */
    /*     */ public static boolean matches(Pattern[] patterns, String string)
    /*     */ {
        /* 108 */ for (int i = 0; i < patterns.length; ++i) {
            /* 109 */ if (patterns[i].matcher(string).matches()) return true;
            /*     */ }
        /* 111 */ return false;
        /*     */ }

    /*     */
    /*     */ public static Pattern compileWildcardPattern(String wildcard, boolean ignoreCase)
    /*     */ {
        /* 122 */ String wildcardLocal = wildcard;
        /* 123 */ String wildcardPattern = "";
        /*     */
        /* 125 */ Matcher escapeMatcher = ESCAPE_PATTERN.matcher(wildcardLocal);
        /* 126 */ wildcardLocal = escapeMatcher.replaceAll("\\\\$1");
        /*     */
        /* 128 */ Matcher asteriskMatcher = ASTERISK_PATTERN.matcher(wildcardLocal);
        /* 129 */ wildcardLocal = asteriskMatcher.replaceAll("(.*)");
        /*     */
        /* 131 */ Matcher questionmarkMatcher = QUESTION_PATTERN.matcher(wildcardLocal);
        /* 132 */ wildcardLocal = questionmarkMatcher.replaceAll("(.)");
        /*     */
        /* 134 */ Matcher multipleMacher = MULTIPLE_PATTERN.matcher(wildcardLocal);
        /* 135 */ wildcardLocal = multipleMacher.replaceAll(")|(");
        /*     */
        /* 137 */ wildcardPattern = "(" + wildcardLocal + ")";
        /*     */
        /* 139 */ if (!(ignoreCase)) {
            /* 140 */ return Pattern.compile(wildcardPattern);
            /*     */ }
        /* 142 */ return Pattern.compile(wildcardPattern, 2);
        /*     */ }
    /*     */ }
