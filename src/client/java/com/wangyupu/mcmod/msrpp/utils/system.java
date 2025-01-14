package com.wangyupu.mcmod.msrpp.utils;

import java.awt.Desktop;
import java.net.URI;
import com.wangyupu.mcmod.msrpp.McmodSearchRebornPlusPlusClient;

public class system {
    public static boolean openURLInBrowser(String url) { // 来自 https://github.com/yiyuyan/McModSearchRebornAgain
        try {
            Runtime runtime = Runtime.getRuntime();
            if(Desktop.isDesktopSupported() || System.getProperty("os.name").contains("Windows")){
                //Windows
                Desktop.getDesktop().browse(new URI(url));
            }
            else{
                if(System.getProperty("os.name").contains("Mac")){
                    //Mac
                    runtime.exec(new String[]{"xdg-open","\""+url+"\""});
                }
                else{
                    runtime.exec(new String[]{"xdg-open",url});
                }
            }
            return true;
        } catch (Exception e) {
            McmodSearchRebornPlusPlusClient.LOGGER.error("无法打开 URL!");
            e.printStackTrace();
            return false;
        }
    }
}
