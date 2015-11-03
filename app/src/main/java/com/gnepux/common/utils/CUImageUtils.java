package com.gnepux.common.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.gnepux.common.algorithm.BlurEffect;
import com.gnepux.common.algorithm.PinchZoom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 图像辅助类
 * Created by Gnepux on 2015/10/20.
 */
public class CUImageUtils {

    /**
     * 为ImageView添加pinch缩放功能
     *
     * @param mImageView\
     * ImageView中需要设置android:scaleType="matrix"
     */
    public static void applyPinchZoomImage(ImageView mImageView) {
        mImageView.setOnTouchListener(new PinchZoom());
    }

    /**
     * Get bitmap from specified image path
     *
     * @param imgPath
     * @return
     */
    public static Bitmap getBitmap(String imgPath) {
        // Get bitmap through image path
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        // Do not compress
        newOpts.inSampleSize = 1;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }

    /**
     * Store bitmap into specified image path
     *
     * @param bitmap
     * @param outPath
     * @throws java.io.FileNotFoundException
     */
    public static void storeImage(Bitmap bitmap, String outPath) throws FileNotFoundException {
        FileOutputStream os = new FileOutputStream(outPath);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
    }

    /**
     * Compress image by pixel, this will modify image width/height.
     * Used to get thumbnail
     *
     * @param imgPath image path
     * @param pixelW  target pixel of width
     * @param pixelH  target pixel of height
     * @return
     */
    public static Bitmap ratio(String imgPath, float pixelW, float pixelH) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        // Get bitmap info, but notice that bitmap is null now
        Bitmap bitmap;

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 想要缩放的目标尺寸
        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
        float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        // 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
        // 压缩好比例大小后再进行质量压缩
        //        return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap;
    }

    /**
     * Compress image by size, this will modify image width/height.
     * Used to get thumbnail
     *
     * @param image
     * @param pixelW target pixel of width
     * @param pixelH target pixel of height
     * @return
     */
    public static Bitmap ratio(Bitmap image, float pixelW, float pixelH) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, os);
        if (os.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory
            // .decodeStream）时溢出
            os.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap;
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
        float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        is = new ByteArrayInputStream(os.toByteArray());
        bitmap = BitmapFactory.decodeStream(is, null, newOpts);
        //压缩好比例大小后再进行质量压缩
        //	    return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap;
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param image
     * @param outPath
     * @param maxSize target will be compressed to be smaller than this size.(kb)
     * @throws java.io.IOException
     */
    public static void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws
            IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 100;
        // Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while (os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            if (options < 0) {
                options = 0;
                image.compress(Bitmap.CompressFormat.JPEG, options, os);
                break;
            }
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
        }

        // Generate compressed image file
        FileOutputStream fos = new FileOutputStream(outPath);
        fos.write(os.toByteArray());
        fos.flush();
        fos.close();
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param imgPath
     * @param outPath
     * @param maxSize     target will be compressed to be smaller than this size.(kb)
     * @param needsDelete Whether delete original file after compress
     * @throws IOException
     */
    public static void compressAndGenImage(String imgPath, String outPath, int maxSize, boolean
            needsDelete) throws IOException {
        compressAndGenImage(getBitmap(imgPath), outPath, maxSize);

        // Delete original file
        if (needsDelete) {
            File file = new File(imgPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Ratio and generate thumb to the path specified
     *
     * @param image
     * @param outPath
     * @param pixelW  target pixel of width
     * @param pixelH  target pixel of height
     * @throws FileNotFoundException
     */
    public static void ratioAndGenThumb(Bitmap image, String outPath, float pixelW, float pixelH) throws
            FileNotFoundException {
        Bitmap bitmap = ratio(image, pixelW, pixelH);

        storeImage(bitmap, outPath);
    }

    /**
     * Ratio and generate thumb to the path specified
     *
     * @param imgPath
     * @param outPath
     * @param pixelW      target pixel of width
     * @param pixelH      target pixel of height
     * @param needsDelete Whether delete original file after compress
     * @throws FileNotFoundException
     */
    public static void ratioAndGenThumb(String imgPath, String outPath, float pixelW, float pixelH,
                                 boolean needsDelete) throws FileNotFoundException {
        Bitmap bitmap = ratio(imgPath, pixelW, pixelH);
        storeImage(bitmap, outPath);

        // Delete original file
        if (needsDelete) {
            File file = new File(imgPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 根据路径获取图片资源（已缩放）
     *
     * @param url    图片存储路径
     * @param width  缩放的宽度
     * @param height 缩放的高度
     * @return
     */
    public static Bitmap getBitmapFromUrl(String url, double width, double height) throws FileNotFoundException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        if (bitmap == null) {
            throw new FileNotFoundException();
        }
        // 防止OOM发生
        options.inJustDecodeBounds = false;
        int mWidth = bitmap.getWidth();
        int mHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = 1;
        float scaleHeight = 1;
        // 按照固定宽高进行缩放
        // 这里希望知道照片是横屏拍摄还是竖屏拍摄
        // 因为两种方式宽高不同，缩放效果就会不同
        // 这里用了比较笨的方式
        if (mWidth <= mHeight) {
            scaleWidth = (float) (width / mWidth);
            scaleHeight = (float) (height / mHeight);
        } else {
            scaleWidth = (float) (height / mWidth);
            scaleHeight = (float) (width / mHeight);
        }
        // 按照固定大小对图片进行缩放
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight, matrix, true);
        // 用完了记得回收
        bitmap.recycle();
        try {
            storeImage(newBitmap, url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newBitmap;
    }

    /**
     * 通过Resource Id获得Bitmap
     *
     * @param context
     * @param resId
     */
    public static Bitmap getBitmapFromResId(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    /**
     * 在相册中打开某路径下的图片
     *
     * @param mContext
     * @param imagePath
     */
    public static void openImage(Context mContext, String imagePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(imagePath));
        intent.setDataAndType(uri, "image/*");
        mContext.startActivity(intent);
    }

    /**
     * 在相册中打开某路径下的视频
     *
     * @param mContext
     * @param videoPath
     */
    public static void openVideo(Context mContext, String videoPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        mContext.startActivity(intent);
    }

    /**
     * 从指定URL下载图片显示到ImageView中
     *
     * @param imgurl     of the image.
     * @param mImageView in which you have to set image
     */
    public static void downloadImageFromURL(final String imgurl,
                                            final ImageView mImageView) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Bitmap bitmap = BitmapFactory
                            .decodeStream((InputStream) new URL(imgurl)
                                    .getContent());
                    mImageView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bitmap != null) {

                                mImageView.setImageBitmap(bitmap);
                            }

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 对drawable进行模糊处理
     *
     * @param mContext
     * @param drawable for applying effect
     * @param radius   for blur effect 0 to 25
     * @return drawable
     */
    public static Drawable blurEffectsOnDrawable(Context mContext, int drawable, int radius) {

        if (radius == 0)
            radius = 20;
        Bitmap blurBitmap;
        Bitmap bitmap = drawableTobitmap(mContext, drawable);
        blurBitmap = BlurEffect.fastblur(mContext, bitmap, radius);
        return new BitmapDrawable(blurBitmap);
    }

    /**
     * 将drawable转成bitmap
     *
     * @param mContext
     * @param drawable for convert to bitmap
     * @return bitmap image
     */
    public static Bitmap drawableTobitmap(Context mContext, int drawable) {
        Drawable myDrawable = mContext.getResources().getDrawable(drawable);
        return ((BitmapDrawable) myDrawable).getBitmap();
    }

    /**
     * 将bitmap转成drawable
     *
     * @param mContext
     * @param bitmap
     * @return
     */

    public static Drawable bitmapToDrawable(Context mContext, Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }


    /**
     * 生成圆角bitmap
     * Get Rounded cornered bitmap
     *
     * @param bitmap
     * @param roundPixels
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int roundPixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = roundPixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 截取view的图像并返回bitmap
     * Captures the view and returns bitmap
     *
     * @param v
     * @return bitmap of view captured
     */
    public static Bitmap captureView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
//        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    /**
     * 抓取view中某点的颜色
     *
     * @param view
     * @param x
     * @param y
     * @return
     * @throws NullPointerException
     */
    public static int pickColor(View view, int x, int y)
            throws NullPointerException {

        int red = 0;
        int green = 0;
        int blue = 0;
        int color = 0;

        int offset = 1; // 3x3 Matrix
        int pixelsNumber = 0;

        int xImage = 0;
        int yImage = 0;


        ImageView imageView = (ImageView) view;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap imageBitmap = bitmapDrawable.getBitmap();


        xImage = (int) (x * ((double) imageBitmap.getWidth() / (double) imageView.getWidth()));
        yImage = (int) (y * ((double) imageBitmap.getHeight() / (double) imageView.getHeight()));


        for (int i = xImage - offset; i <= xImage + offset; i++) {
            for (int j = yImage - offset; j <= yImage + offset; j++) {
                try {
                    color = imageBitmap.getPixel(i, j);
                    red += Color.red(color);
                    green += Color.green(color);
                    blue += Color.blue(color);
                    pixelsNumber += 1;
                } catch (Exception e) {
                    //Log.w(TAG, "Error picking color!");
                }
            }
        }
        red = red / pixelsNumber;
        green = green / pixelsNumber;
        blue = blue / pixelsNumber;

        return Color.rgb(red, green, blue);
    }

}
