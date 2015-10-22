【TextUtils---文本辅助类】
1. 判断EditText的文本是否为空
    boolean isEmptyEditText(EditText edText)
2. 判断Email格式是否正确
    boolean isEmailIdValid(String email)

【NetUtils---网络辅助类】
1. 判断网络是否连接或正在连接
    boolean isNetworkConnectedOrConnecting(Context context)
2. 判断网络是否连接
    boolean isNetWorkConnected(Context context)
3. 判断是否是WiFi连接
    boolean isWifi(Context context)
4. 判断是否是流量连接
    isMobile(Context context)
5. 打开网络设置页面
    openNetworkSetting(Activity activity)
6. 获取ip地址，优先获取wifi连接下的ip地址
    String getIpAddress(Context context)
7. 获取wifi连接下的ip地址
    String getWifiIpAddress(Context context)
8. 获取流量连接下的ip地址
    String get3GIpAddress()
9. 在浏览器中打开某个url
    void openURL(Context mContext, String url)
10.


【DateTimeUtils---日期时间辅助类】


【DeviceUtils---设备辅助类】
1. 获取设备IMEI
    String getIMEI(Context context)
2. 获取mac地址
    String getMacAddress(Context context)
3. 获取手机类型，NONE、GSM、CDMA、SIP
    int getPhoneType(Context context)
4. 获取设备系统版本
    int getSysVersion()
5. 获取total memory, in MB
    long getTotalMem(Context context)
6. 获取total memory, in MB
    long getFreeMem(Context context)
7. 获取CPU型号
    String getCpuInfo()
8. 获取设备生产商
    String getProductName()
9. 获取设备型号
    String getModelName()
10. 获取设备序列号
    String getSerial()
11. 获取设备硬件制造商
    String getManufacturerName()
12. 使屏幕保持常亮
    void disableSleepMode(Context context)
13. 不使屏幕保持常亮
    void enableSleepMode(Context context)
14. 获得屏幕高
    int getDeviceHeight(Context context)
15. 获得屏幕宽
    int getDeviceWidth(Context context)

【SPUtils---SharedPreferences封装类】
1. 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法，使用默认的file
    void put(Context context, String key, Object object)
2. 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
    void put(Context context, String fileName, String key, Object object)
3. 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值，使用默认的file
    Object get(Context context, String key, Object defaultObject)
4. 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
    Object get(Context context, String fileName, String key, Object defaultObject)
5. 移除某个key值已经对应的值，使用默认的file
    void remove(Context context, String key)
6. 移除某个key值已经对应的值
    void remove(Context context, String fileName, String key)
7. 清除所有数据，使用默认的file
    void clear(Context context)
8. 清除所有数据
    void clear(Context context, String fileName)
9. 查询某个key是否已经存在，使用默认的file
    boolean contains(Context context, String key)
10. 查询某个key是否已经存在
    boolean contains(Context context, String fileName, String key)
11. 返回所有的键值对，使用默认的file
    Map<String, ?> getAll(Context context)
12. 返回所有的键值对
    Map<String, ?> getAll(Context context, String fileName)
13. 将bitmap存入sp
    void setBitmapToPreference(Bitmap bitmap, Context context, String name, String PREFS_FILE_NAME)
14. 从sp中读取bitmap
    Bitmap getBitmapFromPreference(Context mContext, String name, String PREFS_FILE_NAME)

【LocationUtils---Location辅助类】
1. 获取设备当前GPS状态（是否开启）
    boolean getGpsStatus(Context context)
2. 获取设备当前Location实例
    Location getCurrentLocation(Context context)
3. 获取纬度
    double getLatitude(Context context)
4. 获取经度
    double getLongitude(Context context)

【ImageUtils---图像辅助类】
1. 为ImageView添加pinch缩放功能
    void applyPinchZoomImage(ImageView mImageView)
2. 从指定路径获取Bitmap实例
    Bitmap getBitmap(String imgPath)
3. 将Bitmap存入指定路径
    void storeImage(Bitmap bitmap, String outPath)
*4. Compress image by pixel, this will modify image width/height.Used to get thumbnail.
    Bitmap ratio(String imgPath, float pixelW, float pixelH)
*5. Compress image by size, this will modify image width/height.Used to get thumbnail.
    Bitmap ratio(Bitmap image, float pixelW, float pixelH)
6. 按质量压缩图片
    void compressAndGenImage(Bitmap image, String outPath, int maxSize)
7. 按质量压缩图片
    void compressAndGenImage(String imgPath, String outPath, int maxSize, boolean needsDelete)
*8. Ratio and generate thumb to the path specified
    void ratioAndGenThumb(Bitmap image, String outPath, float pixelW, float pixelH)
*9. Ratio and generate thumb to the path specified
    void ratioAndGenThumb(String imgPath, String outPath, float pixelW, float pixelH, boolean needsDelete)
*10. 根据路径获取图片资源（已缩放）
    Bitmap getBitmapFromUrl(String url, double width, double height)
11. 通过Resource Id获得Bitmap
    Bitmap getBitmapFromResId(Context context, int resId)
12. 在相册中打开某路径下的图片
    void openImage(Context mContext, String imagePath)
13. 从指定URL下载图片显示到ImageView中
    void downloadImageFromURL(final String imgurl, final ImageView mImageView)
14. 对drawable进行模糊处理
    blurEffectsOnDrawable(Context mContext, int drawable, int radius)
15. 将drawable转成bitmap
    Bitmap drawableTobitmap(Context mContext, int drawable)
16. 将bitmap转成drawable
    Drawable bitmapToDrawable(Context mContext, Bitmap bitmap)
TODO: 4,5,8,9,10无用
TODO: iStudy项目中用到的图像压缩类

【AppUtils---App相关辅助类】
1. 获取App Icon
    Drawable getAppIcon(Context mContext)
2. 获取应用程序名称
    String getAppName(Context context)
3. 获取应用程序版本名称信息
    String getVersionName(Context context)
4. 获取应用程序版本号信息
    int getVerCode(Context context)
TODO：获取机器上安装的所有应用的信息

【NotificationUtils---Notification辅助类】
1. 发送Notification，requestCode默认为0
    void sendNoticifaction(Context context, String tickerText, int iconId, String title, String content, Intent intent)
2. 发送Notification，需传requestCode
    void sendNoticifaction(Context context, String tickerText, int iconId, String title, String content, Intent intent, int reuqstCode)
3. 清除当前context下的所有通知
    void cancelAll(Context context)
4. 清除当前context下号为id的通知
    void cancel(Context context, int id)
TODO: 更多style的接口，BigTextStyle和BigPictureStyle

【MapUtils---地图辅助类】
1. 在地图上显示地点
    void showAddressOnMap(Context mContext, String address)

【FileUtils---文件辅助类】
1. 在指定目录下创建新目录
    boolean createFolder(Context mContext, String path, String folderName)
2. 获取指定目录下某后缀文件的数量
    int getFileCounts(String format, String directoryPath)

【NumUtils--数字相关的辅助类】
1. 获得a-z中的随机字符
    char getRandomCharacter()
2. 获取0到指定范围内的随机数
    int getRandom(int maxNum)
3. 为数字添加后缀 like: 1st, 3rd.
    String getPostFixForNumber(int number)

【MediaUtils---多媒体相关的辅助类】
*1. 将device volumen设为app volumen
    void setCurrentDeviceVolume(Context mContext)
TODO:播放音乐 Music On/Off
TODO: 1待验证

【SDCardUtils---SDCard相关辅助类】
1. 判断SDCard是否可用
    boolean isSDCardAvailable()
2. 获取SD卡路径
    String getSDCardPath()
3. 获取SD卡的总容量 in byte
    long getSDCardTotalSize()
4. 获取SD卡的可用容量 in byte
    long getSDCardAvailSize()
5. 获取SD卡的总容量 Format in hunman.
    String getSDCardTotalSizeInHuman(Context mContext)
6. 获取SD卡的可用容量 Format in hunman.
    String getSDCardAvailSizeInHuman(Context mContext)
7. 获取机身的存储路径
    String getSystemDataPath()
8. 获取机身的总容量 in byte
    long getSystemTotalSize()
9. 获取机身的可用容量 in byte
    long getSystemAvailSize()
10. 获取机身的总容量 Format in hunman.
    String getSystemTotalSizeInHuman(Context mContext)
11. 获取机身的可用容量 Format in hunman.
    String getSystemAvailSizeInHuman(Context mContext)
12. 判断某个文件是否存在于SDCard中
    boolean isFileExistInSDCard(String filePath, String fileName)
13. 向sdcard上写文件
    boolean saveFileToSDCard(String filePath, String fileName, String content)
14. 从sdcard上读文件
    byte[] readFileFromSDCard(String filePath, String fileName)
15. 从sdcard上删除某路径的文件
    boolean deleteFileFromSDCard(String filePath, String fileName)

【ViewUtils---View相关辅助类】
1. 设置在minClickInterval的时间间隔内View不能双击
    void preventDoubleClick(final View view, long minClickInterval)