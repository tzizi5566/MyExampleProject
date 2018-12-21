package com.example.kop.myexampleproject.ui.camera;

import static android.os.Environment.DIRECTORY_PICTURES;

import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.kop.myexampleproject.R;
import java.io.File;
import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {

    private static int PERMISSION_CAMERA = 666;

    private static int TACK_PHOTO = 999;

    private static int CROP_PHOTO = 333;

    @BindView(R.id.iv_photo)
    AppCompatImageView mIvPhoto;

    @BindView(R.id.tv_photo_address)
    AppCompatTextView mTvPhotoAddress;

    private String mCropName;

    private ArrayList<String> mList = new ArrayList<>();

    private String mPath;

    private Uri mUri;

    private String[] permissions = new String[]{
            permission.CAMERA,
            permission.READ_EXTERNAL_STORAGE,
            permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TACK_PHOTO) {
                cropPhoto();
            } else if (requestCode == CROP_PHOTO) {
                Glide.with(this).load(mPath + "/" + mCropName).into(mIvPhoto);
                mTvPhotoAddress.setText(String.format("%s/%s", mPath, mCropName));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
            @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CAMERA) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    boolean showRequestPermission = ActivityCompat
                            .shouldShowRequestPermissionRationale(this, permissions[i]);
                    // TODO: 2018/12/14 需要弹窗提示
                    if (showRequestPermission) {
                        ToastUtils.showShort("权限未申请");
                    } else {
                        ToastUtils.showShort("设置打开申请");
                    }
                }
            }
        }
    }

    /**
     * 获得照片储存路径
     */
    public File getPhotoFile(Context context) {
        final File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDirectory = context.getExternalFilesDir(DIRECTORY_PICTURES);
            if (cacheDirectory == null) {
                return null;
            }
            file = cacheDirectory;
        } else {
            File cacheDirectory = context.getFilesDir();
            if (cacheDirectory == null) {
                return null;
            }
            file = cacheDirectory;
        }
        return file;
    }

    public Uri getUri(Context context, String path) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= VERSION_CODES.N) {
            //参数1 上下文；参数2 Provider主机地址 authorities 和配置文件中保持一致 ；参数3  共享的文件
            uri = FileProvider
                    .getUriForFile(context, "com.example.kop.myexampleproject.fileprovider", new File(path));
        } else {
            uri = Uri.fromFile(new File(path));
        }
        return uri;
    }

    @OnClick(R.id.btn_take_photo)
    public void onViewClicked() {
        if (checkPermission()) {
            takePhoto();
        }
    }

    /**
     * 检查拍照权限
     */
    private boolean checkPermission() {
        mList.clear();
        for (final String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                mList.add(permission);
            }
        }
        if (!mList.isEmpty()) {
            String[] array = mList.toArray(new String[0]);
            ActivityCompat.requestPermissions(this, array, PERMISSION_CAMERA);
            return false;
        }
        return true;
    }

    /**
     * 调用系统剪裁
     */
    private void cropPhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(mUri, "image/*");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 480);
        intent.putExtra("scale", true);
        mCropName = String.valueOf(System.currentTimeMillis()) + "_crop.jpg";
        File file = new File(mPath + "/" + mCropName);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent = Intent.createChooser(intent, "裁剪图片");
        startActivityForResult(intent, CROP_PHOTO);
    }

    /**
     * 调用系统相机拍照
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPath = getPhotoFile(this).getPath();
        String photoName = String.valueOf(System.currentTimeMillis()) + ".jpg";
        mUri = getUri(this, mPath + "/" + photoName);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, TACK_PHOTO);
    }
}
