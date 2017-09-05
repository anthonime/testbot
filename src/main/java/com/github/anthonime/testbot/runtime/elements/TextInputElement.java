package com.github.anthonime.testbot.runtime.elements;

import com.github.anthonime.testbot.w3c.ArrowKey;

/**
 * Created by schio on 9/10/2017.
 */
public interface TextInputElement extends Element, HasText, ClickableElement, Focusable<TextInputElement> {

    TextInputElement typeTab();

    TextInputElement type(char c);

    TextInputElement type(CharSequence charSequence);

    TextInputElement type(String string);

    TextInputElement type(ArrowKey arrow);

    TextInputElement typeDel();

    TextInputElement typeEnter();

    TextInputElement typeEscape();

    TextInputElement deleteAllText();

    TextInputElement selectAll();

}
