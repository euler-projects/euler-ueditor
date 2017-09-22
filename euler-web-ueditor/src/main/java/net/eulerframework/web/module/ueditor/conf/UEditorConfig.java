/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2013-2017 cFrost.sun(孙宾, SUN BIN) 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * For more information, please visit the following website
 * 
 * https://eulerproject.io
 * https://github.com/euler-form/web-form
 * https://cfrost.net
 */
package net.eulerframework.web.module.ueditor.conf;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import net.eulerframework.common.util.DateUtils;
import net.eulerframework.web.module.ueditor.entity.UeConfig;

/**
 * @author cFrost
 *
 */
public class UEditorConfig {
    
    public static String getUeUplaodPath() {
        return "/ueditor/upload";
    }
    
    public static String getUeImageUploadPath() {
        String ueUploadPath = UEditorConfig.getUeUplaodPath();
        return ueUploadPath + "/image/" + DateUtils.formatDate(new Date(), "yyyyMMdd");
    }

    public static String getUeFileUploadPath() {
        String ueUploadPath = UEditorConfig.getUeUplaodPath();
        return ueUploadPath + "/file/" + DateUtils.formatDate(new Date(), "yyyyMMdd") + "/" + UUID.randomUUID().toString();
    }

    public static String getUeVideoUploadPath() {
        String ueUploadPath = UEditorConfig.getUeUplaodPath();
        return ueUploadPath + "/video/" + DateUtils.formatDate(new Date(), "yyyyMMdd");
    }

    public static UeConfig getUeConfig(String urlPrefix) {
        UeConfig c = new UeConfig();
        c.setImageActionName("uploadimage");
        c.setImageFieldName("upfile");
        c.setImageMaxSize(10485760);
        c.setImageAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"));
        c.setImageCompressEnable(true);
        c.setImageCompressBorder(1600);
        c.setImageInsertAlign("none");
        c.setImageUrlPrefix(urlPrefix);
        c.setImagePathFormat("/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");

        c.setScrawlActionName("uploadscrawl");
        c.setScrawlFieldName("upfile");
        c.setScrawlMaxSize(10485760);
        c.setScrawlInsertAlign("none");
        c.setScrawlUrlPrefix(urlPrefix);
        c.setScrawlPathFormat("/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        
        c.setCatcherLocalDomain(Arrays.asList("127.0.0.1", "localhost", "img.baidu.com"));
        c.setCatcherActionName("catchimage");
        c.setCatcherFieldName("source");
        c.setCatcherPathFormat("/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        c.setCatcherUrlPrefix(urlPrefix);
        c.setCatcherMaxSize(10485760);
        c.setCatcherAllowFiles(Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp4"));
        
        c.setVideoActionName("uploadvideo");
        c.setVideoFieldName("upfile");
        c.setVideoPathFormat("/ueditor/php/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}");
        c.setVideoUrlPrefix(urlPrefix);
        c.setVideoMaxSize(10485760);
        c.setVideoAllowFiles(Arrays.asList(".mp4", ".avi", ".wmv", ".rm", ".rmvb", ".mkv"));
        
        c.setFileActionName("uploadfile");
        c.setFileFieldName("upfile");
        c.setFilePathFormat("/ueditor/php/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}");
        c.setFileUrlPrefix(urlPrefix);
        c.setFileMaxSize(10485760);
        c.setFileAllowFiles(Arrays.asList(".txt", ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".zip",
                ".rar", ".jpg", ".jpeg", ".png", ".gif"));
        
        c.setImageManagerActionName("listimage");
        c.setImageManagerListPath("/ueditor/php/upload/image/");
        c.setImageManagerListSize(20);
        c.setImageManagerUrlPrefix(urlPrefix);
        c.setImageManagerInsertAlign("none");
        c.setImageManagerAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"));
        
        c.setFileManagerActionName("listfile");
        c.setFileManagerListPath("/ueditor/php/upload/file/");
        c.setFileManagerUrlPrefix(urlPrefix);
        c.setFileManagerListSize(20);
        c.setFileManagerAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv",
                ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3",
                ".wav", ".mid", ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls",
                ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"));
        return c;
    }
}
