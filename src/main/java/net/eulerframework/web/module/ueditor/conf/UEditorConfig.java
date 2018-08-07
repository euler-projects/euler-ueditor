/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.eulerframework.web.module.ueditor.conf;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.eulerframework.cache.inMemoryCache.DefaultObjectCache;
import net.eulerframework.cache.inMemoryCache.ObjectCachePool;
import net.eulerframework.common.util.DateUtils;
import net.eulerframework.common.util.property.PropertyReader;
import net.eulerframework.web.module.ueditor.vo.UeConfig;

/**
 * @author cFrost
 *
 */
public class UEditorConfig {
    protected static final Logger LOGGER = LoggerFactory.getLogger(UEditorConfig.class);

    private static final DefaultObjectCache<String, Object> CONFIG_CAHCE = ObjectCachePool
            .generateDefaultObjectCache(Long.MAX_VALUE);

    private static final PropertyReader properties = new PropertyReader("/config-ueditor.properties");

    private static class UEditorConfigKey {
        private final static String ENABLE_IMAGE_UPLOAD = "ueditor.enableImageUpload";
        private final static String ENABLE_FILE_UPLOAD = "ueditor.enableFileUpload";
       private final static String ENABLE_VIDEO_UPLOAD = "ueditor.enableVideoUpload";
       private final static String ENABLE_SCRAWL_UPLOAD = "ueditor.enableScrawlUpload";
       private final static String ENABLE_CATCHER_UPLOAD = "ueditor.enableCatcherUpload";
       
       private final static String MAX_IMAGE_FILE_SIZE = "ueditor.maxImageFileSize";
       private final static String MAX_FILE_FILE_SIZE = "ueditor.maxFileFileSize";
       private final static String MAX_VIDEO_FILE_SIZE = "ueditor.maxVideoFileSize";
       private final static String MAX_SCRAWL_FILE_SIZE = "ueditor.maxScrawlFileSize";
       private final static String MAX_CATCHER_FILE_SIZE = "ueditor.maxCatcherFileSize";
    }

    private static class UEditorConfigDefault {
        private final static boolean ENABLE_IMAGE_UPLOAD = true;
        private final static boolean ENABLE_FILE_UPLOAD = true;
        private final static boolean ENABLE_VIDEO_UPLOAD = false;
        private final static boolean ENABLE_SCRAWL_UPLOAD = false;
        private final static boolean ENABLE_CATCHER_UPLOAD = false;
        
        private final static long MAX_IMAGE_FILE_SIZE = 10485760;
        private final static long MAX_FILE_FILE_SIZE = 52428800;
        private final static long MAX_VIDEO_FILE_SIZE = 52428800;
        private final static long MAX_SCRAWL_FILE_SIZE = 10485760;
        private final static long MAX_CATCHER_FILE_SIZE = 10485760;
    }

    public static boolean clearUEditorConfigCache() {
        properties.refresh();
        return CONFIG_CAHCE.clear();
    }
    
    public static boolean isImageUploadEnabled() {
        return (boolean)CONFIG_CAHCE.get(UEditorConfigKey.ENABLE_IMAGE_UPLOAD, 
                key -> properties.getBooleanValue(key, UEditorConfigDefault.ENABLE_IMAGE_UPLOAD));
    }
    
    public static boolean isFileUploadEnabled() {
        return (boolean)CONFIG_CAHCE.get(UEditorConfigKey.ENABLE_FILE_UPLOAD, 
                key -> properties.getBooleanValue(key, UEditorConfigDefault.ENABLE_FILE_UPLOAD));
    }
    
    public static boolean isVideoUploadEnabled() {
        return (boolean)CONFIG_CAHCE.get(UEditorConfigKey.ENABLE_VIDEO_UPLOAD, 
                key -> properties.getBooleanValue(key, UEditorConfigDefault.ENABLE_VIDEO_UPLOAD));
    }
    
    public static boolean isScrawlUploadEnabled() {
        return (boolean)CONFIG_CAHCE.get(UEditorConfigKey.ENABLE_SCRAWL_UPLOAD, 
                key -> properties.getBooleanValue(key, UEditorConfigDefault.ENABLE_SCRAWL_UPLOAD));
    }
    
    public static boolean isCatcherUploadEnabled() {
        return (boolean)CONFIG_CAHCE.get(UEditorConfigKey.ENABLE_CATCHER_UPLOAD, 
                key -> properties.getBooleanValue(key, UEditorConfigDefault.ENABLE_CATCHER_UPLOAD));
    }
    
    public static long getMaxImageFileSize() {
        return (long)CONFIG_CAHCE.get(UEditorConfigKey.MAX_IMAGE_FILE_SIZE, 
                key -> properties.getLongValue(key, UEditorConfigDefault.MAX_IMAGE_FILE_SIZE));
    }
    
    public static long getMaxFileFileSize() {
        return (long)CONFIG_CAHCE.get(UEditorConfigKey.MAX_FILE_FILE_SIZE, 
                key -> properties.getLongValue(key, UEditorConfigDefault.MAX_FILE_FILE_SIZE));
    }
    
    public static long getMaxVideoFileSize() {
        return (long)CONFIG_CAHCE.get(UEditorConfigKey.MAX_VIDEO_FILE_SIZE, 
                key -> properties.getLongValue(key, UEditorConfigDefault.MAX_VIDEO_FILE_SIZE));
    }
    
    public static long getMaxScrawlFileSize() {
        return (long)CONFIG_CAHCE.get(UEditorConfigKey.MAX_SCRAWL_FILE_SIZE, 
                key -> properties.getLongValue(key, UEditorConfigDefault.MAX_SCRAWL_FILE_SIZE));
    }
    
    public static long getMaxCatcherFileSize() {
        return (long)CONFIG_CAHCE.get(UEditorConfigKey.MAX_CATCHER_FILE_SIZE, 
                key -> properties.getLongValue(key, UEditorConfigDefault.MAX_CATCHER_FILE_SIZE));
    }
    
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
        
        if(UEditorConfig.isImageUploadEnabled()) {
            c.setImageActionName("uploadimage");
            c.setImageFieldName("upfile");
            c.setImageMaxSize(UEditorConfig.getMaxImageFileSize());
            c.setImageAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"));
            c.setImageCompressEnable(true);
            c.setImageCompressBorder(1600);
            c.setImageInsertAlign("none");
            c.setImageUrlPrefix(urlPrefix);
            c.setImagePathFormat("/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");            
        }

        if(UEditorConfig.isScrawlUploadEnabled()) {
            c.setScrawlActionName("uploadscrawl");
            c.setScrawlFieldName("upfile");
            c.setScrawlMaxSize(UEditorConfig.getMaxScrawlFileSize());
            c.setScrawlInsertAlign("none");
            c.setScrawlUrlPrefix(urlPrefix);
            c.setScrawlPathFormat("/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        }
        
        if(UEditorConfig.isCatcherUploadEnabled()) {
            c.setCatcherLocalDomain(Arrays.asList("127.0.0.1", "localhost", "img.baidu.com"));
            c.setCatcherActionName("catchimage");
            c.setCatcherFieldName("source");
            c.setCatcherPathFormat("/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
            c.setCatcherUrlPrefix(urlPrefix);
            c.setCatcherMaxSize(UEditorConfig.getMaxCatcherFileSize());
            c.setCatcherAllowFiles(Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp4"));
        }
        
        if(UEditorConfig.isVideoUploadEnabled()) {
            c.setVideoActionName("uploadvideo");
            c.setVideoFieldName("upfile");
            c.setVideoPathFormat("/ueditor/php/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}");
            c.setVideoUrlPrefix(urlPrefix);
            c.setVideoMaxSize(UEditorConfig.getMaxVideoFileSize());
            c.setVideoAllowFiles(Arrays.asList(".mp4"));            
        }
        
        if(UEditorConfig.isFileUploadEnabled()) {
            c.setFileActionName("uploadfile");
            c.setFileFieldName("upfile");
            c.setFilePathFormat("/ueditor/php/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}");
            c.setFileUrlPrefix(urlPrefix);
            c.setFileMaxSize(UEditorConfig.getMaxFileFileSize());
            c.setFileAllowFiles(Arrays.asList(".txt", ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".zip",
                    ".rar", ".jpg", ".jpeg", ".png", ".gif"));
        }
        
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
