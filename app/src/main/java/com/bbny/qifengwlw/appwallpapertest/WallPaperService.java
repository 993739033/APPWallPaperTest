package com.bbny.qifengwlw.appwallpapertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by ZWX on 2019/2/11.
 */

public class WallPaperService extends WallpaperService {
    static final String Tag="TAG";
    static final int D=50;


    @Override
    public Engine onCreateEngine() {
        return new MyEngine();
    }

    class MyEngine extends WallpaperService.Engine{
        //获取SurfaceHolder时调用
        Paint mpaint;

        @Override
        public SurfaceHolder getSurfaceHolder() {
            return super.getSurfaceHolder();
        }

        //手势移动时回调
        @Override
        public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset);
            Log.d(Tag, "xOffset: "+xOffset);
            Log.d(Tag, "yOffset: "+yOffset);
            Log.d(Tag, "xOffsetStep: "+xOffsetStep);
            Log.d(Tag, "yOffsetStep: "+yOffsetStep);
            Log.d(Tag, "xPixelOffset: "+xPixelOffset);
            Log.d(Tag, "yPixelOffset: "+yPixelOffset);
            Canvas canvas = getSurfaceHolder().lockCanvas();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
            canvas.drawBitmap(bitmap,0,0,null);

            canvas.drawText("xOffset: "+xOffset,300,2200,mpaint);
            canvas.drawText("yOffset: "+yOffset,300,2200+D,mpaint);
            canvas.drawText("xOffsetStep: "+xOffsetStep,300,2200+D,mpaint);
            canvas.drawText("yOffsetStep: "+yOffsetStep,300,2200+2*D,mpaint);
            canvas.drawText("xPixelOffset: "+xPixelOffset,300,2200+3*D,mpaint);
            canvas.drawText("yPixelOffset: "+yPixelOffset,300,2200+4*D,mpaint);
            getSurfaceHolder().lockCanvas();
        }

        //Surface创建时回调
        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            Canvas canvas=holder.lockCanvas();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
            canvas.drawBitmap(bitmap,0,0,null);
            holder.unlockCanvasAndPost(canvas);

            mpaint = new Paint();
            mpaint.setColor(Color.WHITE);
            mpaint.setTextSize(20);
            mpaint.setAntiAlias(true);
        }

        //Surface销毁时回调
        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }
    }
}


