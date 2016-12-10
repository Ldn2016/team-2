package bhf.commerce.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtil {
    public static Bitmap rotateBitmap(ContentResolver cr, Uri uri){
        try {
            Bitmap bitmap = MediaStore.Images.Media
                    .getBitmap(cr, uri);
            Bitmap newImage = resizeImage(bitmap);
            Bitmap rotatedBitmap = newImage;

            ExifInterface ei = new ExifInterface(uri.getPath());
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch(orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    System.out.println("Rotate: 90");
                    rotatedBitmap = rotateImage(newImage, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    System.out.println("Rotate: 180");
                    rotatedBitmap = rotateImage(newImage, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    System.out.println("Rotate: 270");
                    break;
                default:
                    System.out.println("Rotate: 0");
            }

            return rotatedBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Bitmap resizeImage(Bitmap bitmap) {
        int newWidth = (int) (bitmap.getWidth() * 0.03);
        int newHeight = (int) (bitmap.getHeight() * 0.03);
        return Bitmap.createScaledBitmap(bitmap,
                newWidth,
                newHeight,
                false);
    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }
}
