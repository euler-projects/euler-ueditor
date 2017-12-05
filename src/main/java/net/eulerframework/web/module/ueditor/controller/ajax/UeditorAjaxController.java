package net.eulerframework.web.module.ueditor.controller.ajax;

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

import net.eulerframework.common.util.io.file.FileUtils;
import net.eulerframework.web.core.annotation.AjaxController;
import net.eulerframework.web.core.base.controller.AjaxSupportWebController;
import net.eulerframework.web.module.ueditor.conf.UEditorConfig;
import net.eulerframework.web.module.ueditor.vo.FileUploadResult;
import net.eulerframework.web.module.ueditor.vo.UeConfig;

@AjaxController
@RequestMapping("ueditor")
public class UeditorAjaxController extends AjaxSupportWebController {

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
