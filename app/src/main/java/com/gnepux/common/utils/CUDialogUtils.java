package com.gnepux.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.*;

/**
 * Dialog相关辅助类
 * Created by Gnepux on 2015/10/22.
 */
public class CUDialogUtils {


    /**
     * Opens android share dialog pass one of uri or shareText
     *
     * @param context
     * @param title
     * @param uri
     * @param shareText
     */
    public static void openShareDialog(Context context, String title, String uri, String shareText, String shareSubject) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, shareText);
        share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

        if (!android.text.TextUtils.isEmpty(uri)) {
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(uri));
        }
        context.startActivity(Intent.createChooser(share, title));
    }

}
