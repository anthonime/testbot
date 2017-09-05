package com.github.anthonime.testbot;

import com.github.anthonime.testbot.w3c.html.Tag;
import com.github.anthonime.testbot.w3c.xpath.XPathBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by schio on 9/12/2017.
 */
public class TestXPathBuilder {

    @Test
    public void fromAnywhere() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere();
        assertThat(builder.build()).isEqualTo("//");
    }

    @Test
    public void fromAnywhereTag() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere(Tag.a);
        assertThat(builder.build()).isEqualTo("//a");
    }

    @Test
    public void fromRelative() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRelative();
        assertThat(builder.build()).isEqualTo(".");
    }

    @Test
    public void fromRoot() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot();
        assertThat(builder.build()).isEqualTo("/");
    }

    @Test
    public void append() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().append("blab");
        assertThat(builder.build()).isEqualTo("/blab");
    }

    @Test
    public void descendantTag() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere().tag(Tag.h1);
        assertThat(builder.build()).isEqualTo("//h1");
    }

    @Test
    public void descendantDescendant() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere().tag(Tag.h1).descendant().tag(Tag.p);
        assertThat(builder.build()).isEqualTo("//h1//p");
    }

    @Test
    public void descendantDescendantShort() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.h1).descendant(Tag.p);
        assertThat(builder.build()).isEqualTo("//h1//p");
    }

    @Test
    public void descendantChild() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere().tag(Tag.h1).child().tag(Tag.p);
        assertThat(builder.build()).isEqualTo("//h1/p");
    }


    @Test
    public void any() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.div).child().any();
        assertThat(builder.build()).isEqualTo("//div/*");
    }

    @Test
    public void anyChild() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.div).anyChild();
        assertThat(builder.build()).isEqualTo("//div/*");
    }

    @Test
    public void anyDescendant() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.div).anyDescendant();
        assertThat(builder.build()).isEqualTo("//div//*");
    }


    @Test
    public void descendantChildShort() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.h1).child(Tag.p);
        assertThat(builder.build()).isEqualTo("//h1/p");
    }


    @Test
    public void first() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.ul).child(Tag.li).first();
        assertThat(builder.build()).isEqualTo("//ul/li[1]");
    }

    @Test
    public void firstWithId() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.li).predicate().idEquals("blih").end().first();
        assertThat(builder.build()).isEqualTo("//li[@id=\'blih\'][1]");
    }

    @Test
    public void nth() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.ul).child(Tag.li).nth(2);
        assertThat(builder.build()).isEqualTo("//ul/li[2]");
    }

    @Test
    public void last() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.ul).child(Tag.li).last();
        assertThat(builder.build()).isEqualTo("//ul/li[last()]");
    }


    @Test
    public void hasNoAttribute() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.h1).predicate().hasNoAttribute("blih").end();
        assertThat(builder.build()).isEqualTo("//h1[not(@blih)]");
    }


    @Test
    public void textEquals() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.button).predicate().hasTextEqualsTo("Submit").end();
        assertThat(builder.build()).isEqualTo("//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')=\'Submit\']");
    }

    @Test
    public void textContains() throws Exception {
        XPathBuilder builder = XPathBuilder.empty().descendant(Tag.button).predicate().hasTextContaining("Go").end();
        assertThat(builder.build()).isEqualTo("//button[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),\'Go\')]");
    }

    @Test
    public void classCheck() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere(Tag.div).predicate().hasClass("foobar").end();
        assertThat(builder.build()).isEqualTo("//div[contains(concat(\' \',normalize-space(@class),\' \'),\' foobar \')]");
    }

    @Test
    public void unionStatic() throws Exception {
        XPathBuilder builder = XPathBuilder.union(XPathBuilder.fromAnywhere(Tag.a), XPathBuilder.fromAnywhere(Tag.span));
        assertThat(builder.build()).isEqualTo("//a | //span");
    }

    @Test
    public void hasAnyChild() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere(Tag.ul).hasAnyChild();
        assertThat(builder.build()).isEqualTo("//ul[*]");
    }

    @Test
    public void hasTagChild() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere(Tag.ul).hasTagChild(Tag.li);
        assertThat(builder.build()).isEqualTo("//ul[li]");
    }

    @Test
    public void tagPredicate() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere(Tag.ul).predicate().tag(Tag.li).end();
        assertThat(builder.build()).isEqualTo("//ul[li]");
    }


    @Test
    public void nestingPredicates() throws Exception {
        XPathBuilder builder = XPathBuilder.fromAnywhere(Tag.section).predicate().descendant(Tag.h1).predicate().idEquals("hi").end();
        assertThat(builder.build()).isEqualTo("//section[//h1[@id=\'hi\']]");
    }


    //hasClass

//    @Test
//    public void not() throws Exception {
//        XPathBuilder builder = XPathBuilder.fromRoot().predicate().not("expression");
//        assertThat(builder.build()).isEqualTo("/not(expression)");
//    }

    @Test
    public void parent() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().parent();
        assertThat(builder.build()).isEqualTo("/..");
    }

    @Test
    public void tag() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().tag(Tag.applet);
        assertThat(builder.build()).isEqualTo("/applet");
    }

    @Test
    public void hasAttribute() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttribute("test").end();
        assertThat(builder.build()).isEqualTo("/[@test]");
    }

    @Test
    public void hasAttributeEquals() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttributeEqualsTo("test", "truc").end();
        assertThat(builder.build()).isEqualTo("/[@test=\'truc\']");
    }

    @Test
    public void hasAttributeContains() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttributeContaining("test", "truc").end();
        assertThat(builder.build()).isEqualTo("/[contains(@test,\'truc\')]");
    }

    @Test
    public void hasAttributeStartsWith() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttributeStartingWith("test", "truc").end();
        assertThat(builder.build()).isEqualTo("/[starts-with(@test,\'truc\')]");
    }

    @Test
    public void hasAttributeEndsWith() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttributeEndingWith("test", "truc").end();
        assertThat(builder.build()).isEqualTo("/[ends-with(@test,\'truc\')]");
    }

    @Test
    public void predicateWithAnd() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttributeEndingWith("test", "truc").and().hasAttribute("bloh").end();
        assertThat(builder.build()).isEqualTo("/[ends-with(@test,\'truc\') and @bloh]");
    }

    @Test
    public void predicateWithOr() throws Exception {
        XPathBuilder builder = XPathBuilder.fromRoot().predicate().hasAttributeEndingWith("test", "truc").or().hasAttribute("bloh").end();
        assertThat(builder.build()).isEqualTo("/[ends-with(@test,\'truc\') or @bloh]");
    }

}
