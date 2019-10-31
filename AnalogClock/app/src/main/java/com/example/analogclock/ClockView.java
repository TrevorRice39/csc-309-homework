package com.example.analogclock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ClockView extends View {
    Paint blackPaint;         // color for white squares
    Context c;

    // Constructors
    public ClockView(Context context) {
        super(context);
        c = context;
        init();
    }

    // alternate constructor
    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        c = context;

        init();
    }

    // initialize stuff
    protected void init() {
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle( Paint.Style.STROKE );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        int centerX = width / 2;
        int centerY = height / 2;


        int radius = Math.min(height, width) / 2 - 50;

        float hourLength = 50;
        float minuteLength = 20;


        blackPaint.setStrokeWidth(20);
        canvas.drawCircle(centerX, centerY, radius, blackPaint);

        blackPaint.setStrokeWidth(20);
        drawCircularLines(centerX, centerY, radius, hourLength, 12, canvas);
        blackPaint.setStrokeWidth(5);
        drawCircularLines(centerX, centerY, radius, minuteLength, 60, canvas);
    }

    private void drawCircularLines(float centerX, float centerY, float radius, float length, float divisions,  Canvas canvas) {
        for (float angle = 0; angle < 361; angle = angle + 360/divisions) {   //move round the circle to each point
            float displacedAngle = angle - 90;
            float x1 = centerX + ((float)Math.cos(Math.toRadians(displacedAngle)) * (radius - 30)); //convert angle to radians for x and y coordinates
            float y1 = centerY + ((float)Math.sin(Math.toRadians(displacedAngle)) * (radius - 30));
            float x2 = 0;
            float y2 = 0;
            if (centerX == x1) {
                x2 = x1;
                if (y1 > centerY) {
                    y2 = y1 - length;
                }
                else {
                    y2 = y1 + length;
                }
            }
            else {
                float m = (y1 - centerY) / (x1 - centerX);
                // y = mx + b
                //b = y - mx
                float b = y1 - m * x1;
                if (x1 < centerX) {
                    x2 = x1 - (length * (x1 - centerX)) / radius;
                }
                else {
                    x2 = x1 - (length * (x1 - centerX)) / radius;
                }
                y2 = m * x2 + b;
            }
            canvas.drawLine(x1, y1, x2, y2, blackPaint); //draw a line from center point back to the point
        }
    }
}
