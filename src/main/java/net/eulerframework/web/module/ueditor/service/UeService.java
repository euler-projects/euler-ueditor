package net.eulerframework.web.module.ueditor.service;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.eulerframework.web.core.base.service.impl.BaseService;
import net.eulerframework.web.module.ueditor.entity.FileUploadResult;
import net.eulerframework.web.module.ueditor.entity.UeConfig;

@Service
public class UeService extends BaseService {

    public UeConfig config(HttpServletRequest request) {
        UeConfig c = new UeConfig();
        c.setImageActionName("uploadImg");
        c.setImageFieldName("file");
        c.setImageUrlPrefix(request.getContextPath());
        c.setImageAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"));
        c.setImageCompressBorder(1600);
        c.setImageCompressEnable(true);
        
        c.setFileActionName("uploadFile");
        c.setFileFieldName("file");
        c.setFileUrlPrefix(request.getContextPath());
        c.setFileAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp",
        ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
        ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
        ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"));
        return c;
    }

    public FileUploadResult uploadImg(HttpServletRequest request, MultipartFile file) {
        
//        String uploadPath = WebConfig.getUploadPath()+"/ueditor/images/" + CalendarTool.formatDate(new Date(), "yyyy-MM-dd");
//
//        File savedImg = WebFileTool.saveMultipartFile(file, uploadPath);
//        
//        String sourceFileName = file.getOriginalFilename();
//        
//        String extension = FileReader.getFileExtension(sourceFileName);
//        
//        FileUploadResult ret = new FileUploadResult();
//        ret.setOriginal(file.getOriginalFilename());
//        ret.setSize(String.valueOf(savedImg.length()));
//        ret.setState("SUCCESS");
//        ret.setTitle(savedImg.getName());
//        ret.setType(extension);
//        ret.setUrl(uploadPath+"/"+savedImg.getName());
        return null;
        
    }

    public FileUploadResult uploadFile(HttpServletRequest request, MultipartFile file) {
        
//        String uploadPath = WebConfig.getUploadPath()+"/ueditor/files/" + CalendarTool.formatDate(new Date(), "yyyy-MM-dd");
//        
//        File savedFile = WebFileTool.saveMultipartFile(file, uploadPath);
//        
//        String sourceFileName = file.getOriginalFilename();
//        
//        String extension = FileReader.getFileExtension(sourceFileName);
//        
//        FileUploadResult ret = new FileUploadResult();
//        ret.setOriginal(file.getOriginalFilename());
//        ret.setSize(String.valueOf(savedFile.length()));
//        ret.setState("SUCCESS");
//        ret.setTitle(savedFile.getName());
//        ret.setType(extension);
//        ret.setUrl(uploadPath+"/"+savedFile.getName());
        return null;
    }
    
}
