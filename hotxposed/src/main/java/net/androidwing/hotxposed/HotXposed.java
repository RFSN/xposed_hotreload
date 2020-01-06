package net.androidwing.hotxposed;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created  on 2018/3/30.
 */
public class HotXposed {

    public static void hook(Class clazz, XC_LoadPackage.LoadPackageParam lpparam) {
        String packageName = clazz.getName().replace("." + clazz.getSimpleName(), "");


        String appPath = getApkPath(packageName);
        XposedBridge.log("ysnows: " + appPath);

        if (appPath != null) {

            PathClassLoader classLoader =
                    new PathClassLoader(appPath, lpparam.getClass().getClassLoader());

            try {
                XposedHelpers.callMethod(classLoader.loadClass(clazz.getName()).newInstance(), "dispatch", lpparam);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


//        filterNotify(lpparam);

    }

//    private static void filterNotify(XC_LoadPackage.LoadPackageParam lpparam)
//            throws ClassNotFoundException {
//        if ("de.robv.android.xposed.installer".equals(lpparam.packageName)) {
//            XposedHelpers.findAndHookMethod(lpparam.classLoader.loadClass("de.robv.android.xposed.installer.util.NotificationUtil"),
//                    "showModulesUpdatedNotification", new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            param.setResult(new Object());
//                        }
//
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                        }
//                    });
//        }
//    }

    private static String getApkPath(final String packageName) {
        String notContains = PreferencesUtils.notContains(packageName);
        return notContains;
    }


    private static File[] getApkFile(final String packageName) {
        final File file = new File("/sdcard/apppath.txt");

//        File[] list = file.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String filename) {
////                XposedBridge.log("list:" + filename);
//
////                XposedBridge.log("packageName:" + packageName);
//
//                boolean contains = filename.contains(packageName);
////                XposedBridge.log("contains:" + contains);
//
//                XposedBridge.log("list: " + filename + " packageName: " + packageName + " contains: " + contains);
//
//                return contains;
//            }
//        });
//        XposedBridge.log("reallist: " + list.toString() + list.length);


        File[] list = new File[]{};
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[100000];
            int read = fileInputStream.read(bytes);
            String toString = bytes.toString();
            XposedBridge.log(toString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
