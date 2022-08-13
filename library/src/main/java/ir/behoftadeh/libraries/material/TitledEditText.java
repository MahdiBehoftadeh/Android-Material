package ir.behoftadeh.libraries.material;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import com.github.ybq.android.spinkit.SpinKitView;

import ir.behoftadeh.libraries.material.Classes.Functions;


public class TitledEditText extends LinearLayout {

    LinearLayout lin_layout, lin_ed_icon;
    TextView tv_title, tv_counter, tv_error;
    EditText ed_input;
    ImageView img_icon, img_line;
    SpinKitView progressbar_loading;

    String title = "";
    String hint = "";
    String text = "";
    String error_message = "";
    int counter_max = 0;
    Drawable icon = null;
    int line_color = 0;
    boolean counter_enabled = false;
    boolean icon_enabled = false;
    boolean error_enabled = false;
    int inputType;

    int current_input_length = 0;
    int error_color;

    boolean was_icon_enabled = false;

    public TitledEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        inflate(context, R.layout.custom_view_titled_edittext, this);

        line_color = ContextCompat.getColor(context, R.color.theme_color_secondary);
        error_color = ContextCompat.getColor(context, R.color.error_color_red);

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TitledEditText, 0, 0);

        try {

            title = typedArray.getString(R.styleable.TitledEditText_te_title);
            hint = typedArray.getString(R.styleable.TitledEditText_te_hint);
            text = typedArray.getString(R.styleable.TitledEditText_te_text);
            error_message = typedArray.getString(R.styleable.TitledEditText_te_error_message);
            counter_max = typedArray.getInt(R.styleable.TitledEditText_te_counter_max, 0);
            icon = typedArray.getDrawable(R.styleable.TitledEditText_te_icon);
            line_color = typedArray.getColor(R.styleable.TitledEditText_te_line_color, line_color);
            counter_enabled = typedArray.getBoolean(R.styleable.TitledEditText_te_counter_enabled, false);
            icon_enabled = typedArray.getBoolean(R.styleable.TitledEditText_te_icon_enabled, false);
            error_enabled = typedArray.getBoolean(R.styleable.TitledEditText_te_error_enabled, false);
            inputType = typedArray.getInt(R.styleable.TitledEditText_android_inputType, 0x00000001);

        } finally {
            typedArray.recycle();
        }

        initComponents(context);

    }

    private void initComponents(Context context) {

        lin_layout = (LinearLayout) findViewById(R.id.custom_view_titled_edittext_lin_layout);
        lin_ed_icon = (LinearLayout) findViewById(R.id.custom_view_titled_edittext_lin_ed_icon);

        tv_title = (TextView) findViewById(R.id.custom_view_titled_edittext_tv_title);
        tv_counter = (TextView) findViewById(R.id.custom_view_titled_edittext_tv_counter);
        tv_error = (TextView) findViewById(R.id.custom_view_titled_edittext_tv_error);

        ed_input = (EditText) findViewById(R.id.custom_view_titled_edittext_ed_input);

        img_icon = (ImageView) findViewById(R.id.custom_view_titled_edittext_img_icon);
        img_line = (ImageView) findViewById(R.id.custom_view_titled_edittext_img_line);

        progressbar_loading = (SpinKitView) findViewById(R.id.custom_view_titled_edittext_progressbar_loading);

        tv_title.setText(title);
        ed_input.setHint(hint);

        if (text != null && !text.isEmpty())
            ed_input.setText(text);

        tv_error.setText(error_message);
        tv_counter.setText(current_input_length + getContext().getString(R.string.slash_with_space) + counter_max);

        if (icon != null)
            img_icon.setBackground(icon);

        if (line_color != 0)
            img_line.setBackgroundColor(line_color);

        if (counter_enabled)
            tv_counter.setVisibility(VISIBLE);
        else
            tv_counter.setVisibility(GONE);

        if (icon_enabled)
            img_icon.setVisibility(VISIBLE);
        else
            img_icon.setVisibility(GONE);

        if (error_enabled)
            tv_error.setVisibility(VISIBLE);
        else
            tv_error.setVisibility(GONE);

        ed_input.setInputType(inputType);

        ed_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int length = ed_input.getText().length();
                current_input_length = length;
                tv_counter.setText(length + getContext().getString(R.string.slash_with_space) + counter_max);

                if (current_input_length > counter_max)
                    showError(context, error_message);
                else
                    removeError();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lin_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_input.requestFocus();
            }
        });

    }

    public LinearLayout getLin_layout() {
        return lin_layout;
    }

    public LinearLayout getLin_ed_icon() {
        return lin_ed_icon;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public TextView getTv_counter() {
        return tv_counter;
    }

    public TextView getTv_error() {
        return tv_error;
    }

    public EditText getEd_input() {
        return ed_input;
    }

    public ImageView getImg_icon() {
        return img_icon;
    }

    public ImageView getImg_line() {
        return img_line;
    }

    public int getCurrent_input_length() {
        return current_input_length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        ed_input.setHint(hint);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        ed_input.setText(text);
    }

    public String getError_message() {
        return error_message;
    }

    public int getCounter_max() {
        return counter_max;
    }

    public void setCounter_max(int counter_max) {
        this.counter_max = counter_max;
        tv_counter.setText(current_input_length + getContext().getString(R.string.slash_with_space) + counter_max);
        if (tv_counter.getVisibility() != View.VISIBLE)
            tv_counter.setVisibility(VISIBLE);
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
        img_icon.setBackground(icon);
        if (img_icon.getVisibility() != View.VISIBLE)
            img_icon.setVisibility(VISIBLE);
    }

    public int getLine_color() {
        return line_color;
    }

    public void setLine_color(int line_color) {
        this.line_color = line_color;
        img_line.setBackgroundColor(line_color);
    }

    public boolean isCounter_enabled() {
        return counter_enabled;
    }

    public void setCounter_enabled(boolean counter_enabled) {
        this.counter_enabled = counter_enabled;
        if (counter_enabled && tv_counter.getVisibility() != View.VISIBLE)
            tv_counter.setVisibility(VISIBLE);

    }

    public boolean isIcon_enabled() {
        return icon_enabled;
    }

    public void setIcon_enabled(boolean icon_enabled) {
        this.icon_enabled = icon_enabled;
        if (icon_enabled && img_icon.getVisibility() != View.VISIBLE)
            img_icon.setVisibility(VISIBLE);
    }

    public boolean isError_enabled() {
        return error_enabled;
    }

    public void setError_enabled(boolean error_enabled) {
        this.error_enabled = error_enabled;
        if (error_enabled && tv_error.getVisibility() != View.VISIBLE)
            tv_error.setVisibility(VISIBLE);
    }

    public int getError_color() {
        return error_color;
    }

    public void setError_color(int error_color) {
        this.error_color = error_color;
        tv_error.setTextColor(error_color);
        img_line.setBackgroundColor(error_color);
        img_icon.setColorFilter(error_color, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public boolean isEnabled() {
        return lin_layout.isEnabled() || ed_input.isEnabled();
    }

    public void setEnabled(final boolean isEnabled) {

        lin_layout.setEnabled(isEnabled);
        ed_input.setEnabled(isEnabled);

    }

    public void reverseIconGravity() {

        Functions.reverseLinearLayout(lin_ed_icon);

    }

    public void showError(Context context, String error_message) {

        this.error_message = error_message;
        tv_error.setText(error_message);
        img_line.setBackgroundColor(error_color);
        img_icon.setBackground(AppCompatResources.getDrawable(context, R.drawable.ic_error));
        tv_error.setVisibility(VISIBLE);
        img_icon.setVisibility(VISIBLE);

    }

    public void removeError() {

        img_line.setBackgroundColor(line_color);
        tv_error.setVisibility(GONE);
        img_icon.setVisibility(GONE);
    }

    public void load(boolean freeze) {

        setEnabled(freeze);

        was_icon_enabled = img_icon.getVisibility() == View.VISIBLE;

        if (was_icon_enabled)
            img_icon.setVisibility(View.GONE);

        if (progressbar_loading.getVisibility() != View.VISIBLE)
            progressbar_loading.setVisibility(View.VISIBLE);
    }

    public void finishLoading() {

        if (!isEnabled())
            setEnabled(true);

        if (progressbar_loading.getVisibility() == View.VISIBLE)
            progressbar_loading.setVisibility(View.GONE);

        if (was_icon_enabled)
            img_icon.setVisibility(View.VISIBLE);

    }

}