package com.wangyupu.mcmod.msrpp.mcmodapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;

public class mcmodAPIAccess {
    public static URL getMCMODItemIdByItem(String itemID) throws MalformedURLException {
        URL url = URI.create("https://api.mcmod.cn/getItem/?regname="+itemID).toURL();
        return url;
    }
}
