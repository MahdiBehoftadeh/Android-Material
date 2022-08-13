package ir.behoftadeh.libraries.material.Classes;

import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Functions {

    public static void reverseLinearLayout(LinearLayout linearLayout){

        ArrayList<View> views = new ArrayList<View>();
        for (int x = 0; x < linearLayout.getChildCount(); x++) {
            views.add(linearLayout.getChildAt(x));
        }
        linearLayout.removeAllViews();
        for (int x = views.size() - 1; x >= 0; x--) {
            linearLayout.addView(views.get(x));
        }

    }

}