package net.androidwing.hotxposeddemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import net.androidwing.hotxposed.debug.DebugListner;
import net.androidwing.hotxposed.debug.Trace;
import net.androidwing.hotxposed.hot.BaseHook;
import net.androidwing.hotxposed.log.Logs;

/**
 * Created  on 2018/3/30.
 */
public class Main extends BaseHook {

    private static final String LOCALE_PACKAGE = BuildConfig.APPLICATION_ID;
    public static final String[] TARGET_PACKAGES = {"com.wingsofts.zoomimageheader","com.tencent.mm"};

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        startHotXPosed(Main.class, loadPackageParam, LOCALE_PACKAGE, TARGET_PACKAGES);
    }


    @Override
    public void dispatch(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        XposedBridge.log("Main hook dispatch ->" + loadPackageParam.packageName + "-" + LOCALE_PACKAGE + "-" + loadPackageParam.packageName);
        Logs.init("z.houbin.lib.test");
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                Logs.printMethodParam("attach", param);

                Application context = (Application) param.thisObject;

                dispatchAttach(context);

            }
        });
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        super.onActivityCreated(activity, bundle);

        Toast.makeText(activity, "hello", Toast.LENGTH_SHORT).show();
        Trace.traceClass(activity.getClass(), new DebugListner());

    }

}
