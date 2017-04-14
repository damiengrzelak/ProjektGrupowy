package com.example.damian.projektgrupowy.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import com.example.damian.projektgrupowy.R;
import com.example.damian.projektgrupowy.model.interfaces.TitleCardViewListener;
import com.inverce.mod.core.Screen;
import com.inverce.mod.core.Ui;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyUtils;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.example.damian.projektgrupowy.App.getAppContext;

/**
 * Created by Damian on 14.04.2017.
 */

public class TitleCardView  extends FrameLayout implements AdapterView.OnItemSelectedListener {
    private RelativeLayout headerLayout;
    private TextView titleText;
    private TextView subtitleText;
    private ViewGroup contentFrame;
    private CardView cardView;
    private Spinner spinner;
    private SpinnerAdapter adapter;

    @ColorInt
    private int accentColor;
    private int radius;
    private int elevation;
    private int verticalPadding, horizontalPadding;
    private boolean borderVisible;
    private boolean subtitleVisible;
    private boolean inflateFinished;
    private String textTitle;
    private String textSubtitle;
    private boolean isSpinnerVisible;

    private TitleCardViewListener listener;

    public TitleCardView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public TitleCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public TitleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(getContext(), R.layout.view_title_card, this);

        headerLayout = (RelativeLayout) findViewById(R.id.card_title_layout);
        titleText = (TextView) findViewById(R.id.card_title_text);
        subtitleText = (TextView) findViewById(R.id.card_subtitle_text);
        contentFrame = (ViewGroup) findViewById(R.id.card_content);
        cardView = (CardView) findViewById(R.id.card_view_view);
        spinner = (Spinner) findViewById(R.id.card_spinner);
        inflateFinished = true;

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleCardView, defStyleAttr, defStyleRes);
            try {
                // TITLE TEXT
                setTitleText(a.getString(R.styleable.TitleCardView_titleText));
                setTitleColor(a.getColor(R.styleable.TitleCardView_titleTextColor, color(android.R.color.white)));
                setTitleGravity(a.getInt(R.styleable.TitleCardView_titleGravity, Gravity.CENTER) | Gravity.CENTER_VERTICAL);
                setTitleFontSize(a.getDimensionPixelSize(R.styleable.TitleCardView_titleTextSize, -1));
                setTitleFontFamily(a.getInt(R.styleable.TitleCardView_titleFont, 0));

                // SUBTITLE TEXT
                setSubtitleVisibility(a.getBoolean(R.styleable.TitleCardView_subtitleVisibility, false));
                setSubtitleGravity(a.getInt(R.styleable.TitleCardView_titleGravity, Gravity.CENTER) | Gravity.CENTER_VERTICAL);
                setSubtitleTextColor(a.getColor(R.styleable.TitleCardView_subtitleTextColor, color(android.R.color.white)));
                setSubtitleTextSize(a.getDimensionPixelSize(R.styleable.TitleCardView_subtitleTextSize, Screen.dpToPx(13)));

                // CARDVIEW
                setTitleVerticalPadding(a.getDimensionPixelSize(R.styleable.TitleCardView_titleVerticalPaddings, Screen.dpToPx(17)));
                setTitleHorizontalPadding(a.getDimensionPixelSize(R.styleable.TitleCardView_titleHorizontalPaddings, Screen.dpToPx(10)));
                setCardviewCornerRadius(a.getDimensionPixelSize(R.styleable.TitleCardView_cardviewRadius, Screen.dpToPx(5)));
                setCardViewElevation(a.getDimensionPixelSize(R.styleable.TitleCardView_cardviewElevation, Screen.dpToPx(5)));
                setVisibleBorder(a.getBoolean(R.styleable.TitleCardView_visibleBorder, false));

                // OTHER
                setAccentColor(a.getColor(R.styleable.TitleCardView_colorAccentBright, color(R.color.gray_light)));
                setSpinnerVisibilty(a.getBoolean(R.styleable.TitleCardView_spinnerVisibility, false));

            } finally {
                a.recycle();
            }
        }
    }

    public void setSpinnerData(List<String> spinnerList) {
        if (isSpinnerVisible) {
            adapter = new SpinnerAdapter(spinnerList);
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(this);
        }
    }

    public void setSubtitleTextColor(@ColorInt int color) {
        subtitleText.setTextColor(color);
    }

    public void setSubtitleTextSize(int px) {
        if (px > 0) {
            subtitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
        }
    }

    public void setSubtitleText(String textSubtitle) {
        if (subtitleVisible) {
            this.textSubtitle = textSubtitle;
            subtitleText.setText(this.textSubtitle);
        }
    }

    public void setSubtitleVisibility(boolean isVisible) {
        this.subtitleVisible = isVisible;
        Ui.visible(subtitleText, isVisible);
    }

    public void setTitleText(String textTitle) {
        this.textTitle = textTitle;
        titleText.setText(textTitle);
    }

    public void setTitleColor(@ColorInt int color) {
        titleText.setTextColor(color);
    }

    public void setTitleFontSize(int px) {
        if (px > 0) {
            titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
        }
    }

    public TitleCardView setTitleGravity(int gravity) {
        titleText.setGravity(gravity);
        if (gravity == 19) {
            int padding = (int) ((float) 6 * getResources().getDisplayMetrics().density);
            titleText.setPadding(padding, 0, padding, 0);
        }
        return this;
    }

    public TitleCardView setSubtitleGravity(int gravity) {
        subtitleText.setGravity(gravity);
        if (gravity == 19) {
            int padding = (int) ((float) 6 * getResources().getDisplayMetrics().density);
            subtitleText.setPadding(padding, 0, padding, 0);
        }
        return this;
    }

    public void setTitleFontFamily(int fontType) {
        switch (fontType) {
            case 0:
                // let it stay unchanged
                break;
            case 1:
                CalligraphyUtils.applyFontToTextView(getAppContext(), titleText, "fonts/roboto-condensed.bold.ttf");
                break;
        }
    }

    public void setCardviewCornerRadius(int radius) {
        this.radius = radius;
        cardView.setRadius(radius);
    }

    public void setCardViewElevation(int elevation) {
        this.elevation = elevation;
        if (elevation == 0) {
            cardView.setCardElevation(elevation);
            cardView.setFadingEdgeLength(elevation);
            cardView.setUseCompatPadding(false);
        }
    }

    public void setTitleVerticalPadding(int padding) {
        verticalPadding = padding;
        validateTitlePaddings();
    }

    public void setTitleHorizontalPadding(int padding) {
        horizontalPadding = padding;
        validateTitlePaddings();
    }

    private void validateTitlePaddings() {
        if (borderVisible) {
            headerLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding - Screen.dpToPx(BORDER_WIDTH));
        } else {
            headerLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }
    }

    private void revalidateBorder(boolean borderVisible) {
        headerLayout.setBackgroundColor(accentColor);

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        shape.setColor(0xFFFFFFFF);
        if (borderVisible) {
            shape.setStroke(Screen.dpToPx(BORDER_WIDTH), accentColor);
            validateTitlePaddings();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            contentFrame.setBackground(shape);
        } else {
            contentFrame.setBackgroundDrawable(shape);
        }
    }

    public void setAccentColor(@ColorInt int accentColor) {
        this.accentColor = accentColor;
        revalidateBorder(borderVisible);
    }

    public void setSpinnerVisibilty(boolean visible) {
        this.isSpinnerVisible = visible;
        Ui.visible(spinner, this.isSpinnerVisible);
    }


    public void setVisibleBorder(boolean visibleBorder) {
        this.borderVisible = visibleBorder;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(res, null);
        } else {
            //noinspection deprecation
            return getResources().getColor(res);
        }
    }

    @Override
    public void addView(View child) {
        if (inflateFinished) {
            contentFrame.addView(child);
        } else {
            super.addView(child);
        }
    }

    @Override
    public void addView(View child, int index) {
        if (inflateFinished) {
            contentFrame.addView(child, index);
        } else {
            super.addView(child, index);
        }
    }

    @Override
    public void addView(View child, int width, int height) {
        if (inflateFinished) {
            contentFrame.addView(child, width, height);
        } else {
            super.addView(child, width, height);
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (inflateFinished) {
            contentFrame.addView(child, params);
        } else {
            super.addView(child, params);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (inflateFinished) {
            contentFrame.addView(child, index, params);
        } else {
            super.addView(child, index, params);
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        int newHeightMode = params.height == WRAP_CONTENT ? WRAP_CONTENT : MATCH_PARENT;
        Ui.Layout.on(contentFrame).height(newHeightMode, true).done();
        Ui.Layout.on(getChildAt(0)).height(newHeightMode, true).done();
    }

    public void setOnSpinnerListener(TitleCardViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.onItemSelected(parent, view, position, id);
        }
//        spinner.getItemSe
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (listener != null) {
            listener.onNothingSelected(parent);
        }
    }
}
