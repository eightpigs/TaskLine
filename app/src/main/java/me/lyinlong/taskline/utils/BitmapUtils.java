package me.lyinlong.taskline.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Bitmap工具类
 * Created by eightpigs on 16/10/23.
 */

public class BitmapUtils {

    /**
     * 重新设置Bitmap的尺寸
     * @param bm        原bitmap
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @return
     */
    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
