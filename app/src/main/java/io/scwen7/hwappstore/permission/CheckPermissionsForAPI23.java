package io.scwen7.hwappstore.permission;

import android.app.Activity;

/**
 * ......................我佛慈悲......................
 * 　　　　　　　　　　　　　　　_oo0oo_
 * 　　　　　　　　　　　　　　o8888888o
 * 　　　　　　　　　　　　　　88" . "88
 * 　　　　　　　　　　　　　　(| -_- |)
 * 　　　　　　　　　　　　　　0\  =  /0
 * 　　　　　　　　　　　　　___/`---'\___
 * 　　　　　　　　　　　.' \\|     |// '.
 * 　　　　　　　　　　　/ \\|||  :  |||// \
 * 　　　　　　　　　　/ _||||| -卍-|||||- \
 * 　　　　　　　　　　|   | \\\  -  /// |   |
 * 　　　　　　　　　　| \_|  ''\---/''  |_/ |
 * 　　　　　　　　　　\  .-\__  '-'  ___/-. /
 * 　　　　　　　　___'. .'  /--.--\  `. .'___
 * 　　　　　　."" '<  `.___\_<|>_/___.' >' "".
 * 　　　　　　| | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * 　　　　　　\  \ `_.   \_ __\ /__ _/   .-` /  /
 * 　　　=====`-.____`.___ \_____/___.-`___.-'=====
 * 　　　　　　　　　　　　　　　`=---='
 * ..................佛祖开光 ,永无BUG...................
 */
public class CheckPermissionsForAPI23 {

    public CheckPermissionsForAPI23(Activity mActivity, String... permissions) {
        PermissionsChecker permissionsChecker = new PermissionsChecker(mActivity);
        if (permissionsChecker.lacksPermissions(permissions)) {
            PermissionsActivity.startActivityForResult(mActivity, PermissionsActivity.REQUEST_CODE, permissions);
        }
    }
}
