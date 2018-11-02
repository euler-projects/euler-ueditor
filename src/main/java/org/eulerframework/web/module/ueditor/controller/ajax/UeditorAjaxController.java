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
package org.eulerframework.web.module.ueditor.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.eulerframework.common.util.io.file.FileUtils;
import org.eulerframework.web.core.annotation.AjaxController;
import org.eulerframework.web.core.base.controller.ApiSupportWebController;
import org.eulerframework.web.module.ueditor.conf.UEditorConfig;
import org.eulerframework.web.module.ueditor.vo.FileUploadResult;
import org.eulerframework.web.module.ueditor.vo.UeConfig;

@AjaxController
@RequestMapping("ueditor")
public class UeditorAjaxController extends ApiSupportWebController {

    @ResponseBody
    @RequestMapping(value = "/controller", method = RequestMethod.GET)
    public Object controllerGet(String action) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class<? extends UeditorAjaxController> clazz = this.getClass();
        Method m = clazz.getDeclaredMethod(action);
        return m.invoke(this);
    }

    @ResponseBody
    @RequestMapping(value = "/controller", method = RequestMethod.POST)
    public Object controllerPost(String action, HttpServletRequest request, MultipartFile upfile)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        Class<? extends UeditorAjaxController> clazz = this.getClass();
        Method m = clazz.getDeclaredMethod(action, MultipartFile.class);
        return m.invoke(this, upfile);
    }

    public UeConfig config() {
        String urlPrefix = this.getRequest().getContextPath();
        
        return UEditorConfig.getUeConfig(urlPrefix);
    }
    
    public FileUploadResult uploadimage(MultipartFile multipartFile) {
        return this.saveUploadFile(UEditorConfig.getUeImageUploadPath(), multipartFile, true);
    }
    
    public FileUploadResult uploadfile(MultipartFile multipartFile) {
        return this.saveUploadFile(UEditorConfig.getUeFileUploadPath(), multipartFile, false);
    }
    
    public FileUploadResult uploadvideo(MultipartFile multipartFile) {
        return this.saveUploadFile(UEditorConfig.getUeVideoUploadPath(), multipartFile, true);
    }
    
    private FileUploadResult saveUploadFile(String savePath, MultipartFile multipartFile, boolean saveFileNameAsUUID) {
        String realPaht = this.getServletContext().getRealPath(savePath);
        
        String originalFilename = multipartFile.getOriginalFilename();
        String targetFilename;
        
        if(saveFileNameAsUUID) {
            targetFilename = UUID.randomUUID().toString() + FileUtils.extractFileExtension(originalFilename);
        } else {
            targetFilename = originalFilename;
        }

        File targetFile = new File(realPaht, targetFilename);

        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        try {
            multipartFile.transferTo(targetFile);
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }
        
        FileUploadResult ret = new FileUploadResult();
        ret.setOriginal(originalFilename);
        ret.setSize(String.valueOf(targetFile.length()));
        ret.setState("SUCCESS");
        ret.setTitle(targetFile.getName());
        //ret.setType(WebFileTool.extractFileExtension(originalFilename));
        ret.setUrl(savePath+"/"+targetFile.getName());
        
        return ret;
    }
}
