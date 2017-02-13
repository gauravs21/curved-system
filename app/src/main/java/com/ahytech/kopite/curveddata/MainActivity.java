package com.ahytech.kopite.curveddata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView header;
    LinearLayout linearLayout;
    AppBarLayout appBarLayout;
    int startPointX, startPointY, endPointX, endPointY, maxPointX, maxPointY;
    float scale;
    SinWave drawView;
    SensorManager mSensorManager;
    Sensor mSensor;


//    Switch OnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout_parent);
        header = (TextView) findViewById(R.id.textView_header);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

//        OnOff = (Switch) findViewById(R.id.switch1);
//
//        OnOff.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (OnOff.isChecked()) {
//                    Toast.makeText(MainActivity.this, "Switch is on", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Switch is Off", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
        linearLayout.post(new Runnable() {
            @Override
            public void run() {
                startPointX = header.getLeft();
                startPointY = header.getTop();
                endPointX = header.getRight();
                endPointY = header.getBottom();
                maxPointY = maxPointX = (int) (/*(header.getX() + header.getWidth() / 2) + */(16 * scale + 0.5));
//                maxPointX = (int) offsetViewBounds.centerY();
//                int startY = offsetViewBounds.top;
                Log.e("DB", "startX " + startPointX + " startY " + startPointY + " midX " + maxPointX + " midY " + maxPointY +
                        " endX " + endPointX + " endY " + endPointY);
//
//                drawView = new SinWave(header);
//                setContentView(drawView);

//                header.setBackground(drawView);


                SurfaceView surface = (SurfaceView) findViewById(R.id.surfaceView);
                surface.getHolder().addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        Canvas canvas=holder.lockCanvas();
                        Paint paint = new Paint() {
                            {
                                setStyle(Style.FILL_AND_STROKE);
                                setStrokeCap(Paint.Cap.ROUND);
//                    setStrokeWidth(5.0f);
                                setAntiAlias(true);
                                setColor(Color.BLACK);
                            }
                        };
                        final Path path = new Path();
                        path.moveTo(0, endPointY);
//            path.quadTo((startPointX + endPointX)/2, 20, endPointX, endPointY);
                        path.cubicTo(startPointX, endPointY, (startPointX + endPointX) / 2, endPointY - 50, endPointX, endPointY);
                        path.moveTo(0, endPointY + 20);

                        path.cubicTo(startPointX, endPointY + 20, (startPointX + endPointX) / 2, endPointY + 70, endPointX, endPointY + 20);

                        canvas.drawPath(path, paint);
                        holder.unlockCanvasAndPost(canvas);

                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {

                    }
                });
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
////        linearLayout.offsetDescendantRectToMyCoords(header, offsetViewBounds);
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

    public class SinWave extends View {

        private float first_X = 50;
        private float first_Y = 230;
        private float end_X = 100;
        private float end_Y = 230;
        private float Max = 50;

        public SinWave(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public SinWave(TextView textView) {
            super(MainActivity.this);
        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint() {
                {
                    setStyle(Style.FILL_AND_STROKE);
                    setStrokeCap(Paint.Cap.ROUND);
//                    setStrokeWidth(5.0f);
                    setAntiAlias(true);
                    setColor(Color.BLACK);
                }
            };
            final Path path = new Path();
            path.moveTo(0, endPointY);
//            path.quadTo((startPointX + endPointX)/2, 20, endPointX, endPointY);
            path.cubicTo(startPointX, endPointY, (startPointX + endPointX) / 2, endPointY - 50, endPointX, endPointY);
            path.moveTo(0, endPointY + 20);

            path.cubicTo(startPointX, endPointY + 20, (startPointX + endPointX) / 2, endPointY + 70, endPointX, endPointY + 20);

            canvas.drawPath(path, paint);
        }
    }
}
