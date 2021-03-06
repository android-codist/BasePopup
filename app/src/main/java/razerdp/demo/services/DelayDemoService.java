package razerdp.demo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Gravity;

import razerdp.demo.popup.DemoPopup;
import razerdp.demo.utils.UIHelper;
import razerdp.demo.utils.rx.RxHelper;
import razerdp.util.SimpleAnimationUtils;

/**
 * Created by 大灯泡 on 2019/5/16
 * <p>
 * Description：
 */
public class DelayDemoService extends Service {
    private static final String TAG = "DemoService";
    DemoPopup demoPopup;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        UIHelper.toast("已经启动服务，将在3s后弹出Popup");
        RxHelper.delay(3000, data -> {
            if (demoPopup == null) {
                demoPopup = new DemoPopup(getApplicationContext());
                demoPopup.setPopupGravity(Gravity.CENTER)
                        .setBlurBackgroundEnable(true)
                        .setShowAnimation(SimpleAnimationUtils.getDefaultAlphaAnimation(true))
                        .setDismissAnimation(SimpleAnimationUtils.getDefaultAlphaAnimation(false));
                demoPopup.setText("在Service弹出Popup，创建该Popup的context：" + getApplicationContext());
            }
            demoPopup.showPopupWindow();
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
