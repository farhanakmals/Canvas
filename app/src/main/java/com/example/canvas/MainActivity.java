package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;

    // akan menggambar disini
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    // untuk batasan batasan gambarnya
    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final  int MULTIPLIER = 100;

    private int mColorBackground;
    private int mColorRectangle;
    private int mColorCircle;
    private int mColorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackgraund, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(), R.color.colorRectangle, null);
        mColorCircle = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mColorText = ResourcesCompat.getColor(getResources(), R.color.black, null);

        mPaint.setColor(mColorBackground);
        mPaintText.setColor(mColorText);
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.my_imege_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view);
            }
        });

    }

    public void drawSomething(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();

        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);

            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);
            mOffset += OFFSET;

        } else {
            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.setColor(mColorRectangle - MULTIPLIER + mOffset);
                mRect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;

            } else {
                mPaint.setColor(mColorCircle - MULTIPLIER * mOffset);
                mCanvas.drawCircle(halfWidth,halfHeight, halfHeight/3, mPaint);

                // menambahan text
                String text = getString(R.string.done);

                // mencari panjang dari text
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);

                // memindahkan text ke tengah
                int x = halfWidth - mBounds.centerX();
                int y =  halfHeight- mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);

                mOffset += OFFSET;

                // membuat segitiga
//                mPaint.setColor(mColorBackground - MULTIPLIER * mOffset);
//                Point a = new Point(halfWidth - 50, halfHeight - 50);
//                Point b = new Point(halfWidth + 50, halfHeight - 50);
//                Point c = new Point(halfWidth, halfHeight + 250);
//
//                Path path = new Path();
//                path.setFillType(Path.FillType.EVEN_ODD);
//                path.lineTo(a.x, a.y);
//                path.lineTo(b.x, b.y);
//                path.lineTo(c.x, c.y);
//                path.lineTo(a.x, a.y);
//                path.close();
//
//                mCanvas.drawPath(path, mPaint);
//                mOffset += OFFSET;

                // tugas
                mPaint.setColor(mColorBackground - MULTIPLIER * mOffset);
                Point a = new Point(halfWidth - 310, halfHeight - 50);
                Point b = new Point(halfWidth - 85, halfHeight - 70);
                Point c = new Point(halfWidth, halfHeight - 310);
                Point d = new Point(halfWidth + 85, halfHeight - 70);
                Point e = new Point(halfWidth + 310, halfHeight - 50);
                Point f = new Point(halfWidth + 100, halfHeight + 50);
                Point g = new Point(halfWidth + 170, halfHeight + 270);
                Point h = new Point(halfWidth, halfHeight + 100);
                Point i = new Point(halfWidth - 170, halfHeight + 270);
                Point j = new Point(halfWidth - 100, halfHeight + 40);

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(a.x, a.y);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(d.x, d.y);
                path.lineTo(e.x, e.y);
                path.lineTo(f.x, f.y);
                path.lineTo(g.x, g.y);
                path.lineTo(h.x, h.y);
                path.lineTo(i.x, i.y);
                path.lineTo(j.x, j.y);

                path.lineTo(a.x, a.y);

                path.close();

                mCanvas.drawPath(path, mPaint);
                mOffset += OFFSET;
            }

        }

        view.invalidate();

    }

}