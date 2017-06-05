package br.estacio.cadastrodeclientes.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class UbuntuBoldTextView extends TextView {

    public UbuntuBoldTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public UbuntuBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public UbuntuBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Ubuntu-B.ttf", context);
        setTypeface(customFont, Typeface.BOLD);
    }
}
