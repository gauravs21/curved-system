package com.ahytech.kopite.curveddata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView header;
    RelativeLayout relativeLayout;
    int startPointX, startPointY, endPointX, endPointY;
    Curve drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.linearLayout_parent);
        header = (TextView) findViewById(R.id.textView_header);
        header.post(new Runnable() {
            @Override
            public void run() {
                startPointX = header.getLeft();
                startPointY = header.getTop();
                endPointX = header.getRight();
                endPointY = header.getBottom();
                drawView = new Curve(MainActivity.this);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, startPointY + endPointY);
                params.addRule(RelativeLayout.ALIGN_BOTTOM, header.getId());
                drawView.setLayoutParams(params);
                relativeLayout.addView(drawView);
                header.bringToFront();


//                RelativeLayout.LayoutParams layoutParams=(new RelativeLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT));

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    header.setForeground(drawView);
//                }
//                setContentView(drawView);
            }
        });
    }

//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        int[] location = new int[2];
//        header.getLocationInWindow(location);
//        int x = location[0];
//        int y = location[1];
//        Log.e("DB", "x " + x + " y " + y);
//
//        int height = appBarLayout.getHeight();
//        Log.e("DB", String.valueOf(height));
//        Rect offsetViewBounds = new Rect();
////        relativeLayout.offsetDescendantRectToMyCoords(header, offsetViewBounds);
////        header.getGlobalVisibleRect(offsetViewBounds);
////        header.getDrawingRect(offsetViewBounds);
//        header.getLocalVisibleRect(offsetViewBounds);
//
////        header.getGlobalVisibleRect(offsetViewBounds);
//
//        startPointX = header.getLeft();
//        startPointY = header.getTop();
//        endPointX = header.getRight();
//        endPointY = header.getBottom();
////        maxPointY = maxPointX = (int) (/*(header.getX() + header.getWidth() / 2) + */(16 * scale + 0.5));
//        maxPointY = (int) offsetViewBounds.centerX();
//        maxPointX = (int) offsetViewBounds.centerY();
//        int startY = offsetViewBounds.top;
//        Log.e("DB", "startX " + startPointX + " startY " + startPointY + " midX " + maxPointX + " midY " + maxPointY +
//                " endX " + endPointX + " endY " + endPointY + " start " + startY);
//
//
//        drawView = new SinWave(MainActivity.this);
//        drawView.setBackgroundColor(Color.WHITE);
//        setContentView(drawView);
////        setContentView(R.layout.activity_main);
//
//
////        Paint paint=new Paint();
////        paint.setColor(Color.RED);                    // set the color
////        paint.setStrokeWidth(24);               // set the size
////        paint.setDither(true);                    // set the dither to true
////        paint.setStyle(Paint.Style.STROKE);       // set to STOKE
////        paint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
////        paint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
////        paint.setPathEffect(new CornerPathEffect(10) );   // set the path effect when they join.
////        paint.setAntiAlias(true);
////
////        Path path = new Path();
////        path.moveTo(startPointX, startPointY);
////        path.lineTo(endPointX,endPointY);
////        path.cubicTo(startPointX,endPointY,maxPointX,maxPointY,endPointX,endPointY);
////        Canvas canvas = new Canvas();
////        canvas.drawPath(path,paint);
//
////        Paint paint = new Paint() {
////            {
////                setColor(Color.BLACK);
////                setStyle(Paint.Style.STROKE);
////                setStrokeCap(Paint.Cap.ROUND);
////                setStrokeWidth(5.0f);
////                setAntiAlias(true);
////            }
////        };
////
////        final Path path = new Path();
////        path.moveTo(x, y);
////
////        final float x2 = (endPointX + startPointX) / 2;
////        final float y2 = (startY + endPointY) / 2;
////        path.quadTo(x2, y2, endPointX, endPointY);
////        canvas.drawPath(path, paint);
//    }

    public class Curve extends View {

        public Curve(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public Curve(MainActivity context) {
            super(context);

        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);

            //get last coordinates of screen
            Display display = getWindowManager().getDefaultDisplay();
            Point displayPoint = new Point();
            display.getSize(displayPoint);
            int maxX = displayPoint.x;
            int maxY = displayPoint.y;
            Log.d("DB", maxX + " " + maxY);
            Paint paint = new Paint() {
                {
                    setStyle(Style.FILL);
                    setStrokeCap(Paint.Cap.ROUND);
                    setAntiAlias(true);
                    setColor(Color.WHITE);
                }
            };

            final Path path = new Path();
            float scale = MainActivity.this.getResources().getDisplayMetrics().density;
            path.moveTo(startPointX - (32 * scale + 0.5f), startPointY);

            path.cubicTo(startPointX, startPointY + (2 * scale + 0.5f),//starting point
                    (startPointX + endPointX) / 2, startPointY - (30 * scale + 0.5f), //mid point
                    endPointX, startPointY + (2 * scale + 0.5f));//end point

            canvas.drawPath(path, paint);

            //new path to draw the symmetric curve from both end
            Path newPath = new Path();
            newPath.moveTo((endPointX + (32 * scale + 0.5f)), startPointY);

            newPath.cubicTo(endPointX, startPointY + (2 * scale + 0.5f),
                    (startPointX + endPointX) / 2, startPointY - (30 * scale + 0.5f), //mid point
                    startPointX, startPointY + (2 * scale + 0.5f));

            Paint newPaint = new Paint() {
                {
                    setStyle(Style.FILL);
                    setStrokeCap(Paint.Cap.ROUND);
                    setAntiAlias(true);
                    setColor(Color.WHITE);
                }
            };
            canvas.drawPath(newPath, newPaint);
        }
    }
}
