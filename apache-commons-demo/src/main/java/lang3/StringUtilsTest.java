package lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

import static tool.DemoUtils.println;

public class StringUtilsTest {


    @Test
    public void test_isEmpty_01() {
        println( StringUtils.isEmpty(null) );   // true
        println( StringUtils.isEmpty("") );     // true
        println( StringUtils.isEmpty(" ") );    // false
        println( StringUtils.isEmpty(" \n") );  // false
        println( StringUtils.isEmpty(" a ") );  // false
    }

    @Test
    public void test_isNotEmpty_01() {
        println( StringUtils.isNotEmpty(null) );   // false
        println( StringUtils.isNotEmpty("") );     // false
        println( StringUtils.isNotEmpty(" ") );    // true
        println( StringUtils.isNotEmpty(" \n") );  // true
        println( StringUtils.isNotEmpty(" a ") );  // true
    }


    @Test
    public void test_isBlank_01() {
        println( StringUtils.isBlank(null) );   // true
        println( StringUtils.isBlank("") );     // true
        println( StringUtils.isBlank(" ") );    // true
        println( StringUtils.isBlank(" \n") );  // true
        println( StringUtils.isBlank(" a ") );  // false
    }

    @Test
    public void test_isNotBlank_01() {
        println( StringUtils.isNotBlank(null) );   // false
        println( StringUtils.isNotBlank("") );     // false
        println( StringUtils.isNotBlank(" ") );    // false
        println( StringUtils.isNotBlank(" \n") );  // false
        println( StringUtils.isNotBlank(" a ") );  // true
    }

    @Test
    public void test_contains_01() {
        println( StringUtils.contains("", "") );        // true
        println( StringUtils.contains("abc", "") );     // true
        println( StringUtils.contains("abc", "ab") );   // true
        println( StringUtils.contains("abc", "d") );    // false
    }

    /**
     * 对应的，有rightPad
     */
    @Test
    public void test_leftPad() {
        println( StringUtils.leftPad("abc", 1) );   // "abc"
        println( StringUtils.leftPad("abc", 3) );   // "abc"
        println( StringUtils.leftPad("abc", 6) );   // "   abc"
        println( StringUtils.leftPad("abc", 6, "-") );  // "---abc"
        println( StringUtils.leftPad("abc", 6, "opqrst") );  // "opqabc"
    }

    @Test
    public void test_repeat() {
        println( StringUtils.repeat("abc", 0) );   // ""
        println( StringUtils.repeat("abc", 1) );   // "abc"
        println( StringUtils.repeat("abc", 2) );   // "abcabc"
    }

    /**
     * 类似的，有 endsWith
     * 忽略大小写版本：startsWithIgnoreCase、endsWithIgnoreCase
     */
    @Test
    public void test_startsWith() {
        println( StringUtils.startsWith(null, null) );  // true
        println( StringUtils.startsWith("abc", null) ); // false
        println( StringUtils.startsWith("abc", "a") );  // true
        println( StringUtils.startsWith("abc", "bc") ); // false
    }

    /**
     * 虽然 String 本身有 trim 方法，但是若是null，则会 NPE
     */
    @Test
    public void test_strip_01() {
        println( StringUtils.strip(null) );  // null
        println( StringUtils.strip("") );    // ""
        println( StringUtils.strip(" \tabc ") );    // "abc"
    }

    @Test
    public void test_capitalize() {
        println( StringUtils.capitalize("foo") ); // Foo
        println( StringUtils.capitalize("foo bar") );  // Foo bar
        println( StringUtils.capitalize("Foo Bar") );  // Foo bar
        println( StringUtils.capitalize("FOO bar") );  // FOO bar
    }

    @Test
    public void test_uncapitalize() {
        println( StringUtils.uncapitalize("foo") ); // foo
        println( StringUtils.uncapitalize("Foo bar") );  // foo bar
        println( StringUtils.uncapitalize("FOO bar") );  // fOO bar
    }

    @Test
    public void test_join_01() {
        println( StringUtils.join("foo", "bar") );   // foobar
        println( StringUtils.join("foo", "bar", 2) ); // foobar2
        println( StringUtils.join(new String[]{"foo", "bar"}) );  // foobar
        println( StringUtils.join(new Object[]{"foo", "bar", 2}) ); // foobar2
        println( StringUtils.join(new Integer[]{1, 2, 3})); // 123
        // 下面的不支持
        println( StringUtils.join(Arrays.asList(1, 2, 3, 4)) ); // [1, 2, 3, 4]
        println( StringUtils.join(new int[]{1, 2, 3}) ); // [I@ba8a1dc
    }

    @Test
    public void test_join_02() {
        println( StringUtils.join(new String[]{"foo", "bar"}, "---") );  // foo---bar
        println( StringUtils.join(new Object[]{"foo", "bar", 2}, "/") ); // foo/bar/2
        println( StringUtils.join(new Integer[]{1, 2, 3}, "/") ); // 1/2/3
        println( StringUtils.join(Arrays.asList(1, 2, 3, 4), "/") ); // 1/2/3/4
        // 下面的不支持
        println( StringUtils.join(new int[]{1, 2, 3}, "/")); // [I@1b9e1916/
    }

    @Test
    public void test_splitByWholeSeparator_01() {
        String[] result;
        result = StringUtils.splitByWholeSeparator("ab:cd:ef", ":");
        println( Arrays.toString(result) ); // [ab, cd, ef]

        result = StringUtils.splitByWholeSeparator("ab::cd:ef", ":");
        println( Arrays.toString(result) ); // [ab, cd, ef]

        result = StringUtils.splitByWholeSeparator(":ab::cd:ef", ":");
        println( Arrays.toString(result) ); // [ab, cd, ef]
    }

    @Test
    public void test_splitPreserveAllTokens_01() {
        String[] result;
        result = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":");
        println( Arrays.toString(result) ); // [ab, cd, ef] =》 ["ab", "cd", "ef"]

        result = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":");
        println( Arrays.toString(result) ); // [ab, , cd, ef] =》["ab", "", "cd", "ef"]

        result = StringUtils.splitPreserveAllTokens(":ab::cd:ef", ":");
        println( Arrays.toString(result) ); // [, ab, , cd, ef] =》["", "ab", "", "cd", "ef"]
    }



}
