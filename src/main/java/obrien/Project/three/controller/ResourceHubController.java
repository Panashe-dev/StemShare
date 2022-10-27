package obrien.Project.three.controller;

import obrien.Project.three.dto.ResourceHubDto;
import obrien.Project.three.entity.ResourceHub;
import obrien.Project.three.repository.ResourceHubRepository;
import obrien.Project.three.utils.Constants;
import obrien.Project.three.utils.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@Controller
public class ResourceHubController {

    private final ResourceHubRepository repository;

    public ResourceHubController(ResourceHubRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/resources")
    public ModelAndView initForm(){
        ModelAndView mv=new ModelAndView("ResourceHub/ResourceHubDetails");
        return mv;
    }
    @GetMapping("/science")
    public ModelAndView downloadScienceForm(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/ResourceHubDownload");
        model.addAttribute("resource",this.repository.findAllByCategory("science"));
        model.addAttribute("text","Science");
        return mv;
    }

    @GetMapping("/technology")
    public ModelAndView downloadTechnologyForm(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/ResourceHubDownload");
        model.addAttribute("resource",this.repository.findAllByCategory("technology"));
        model.addAttribute("text","Technology");
        return mv;
    }

    @GetMapping("/engineering")
    public ModelAndView downloadEngineeringForm(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/ResourceHubDownload");
        model.addAttribute("resource",this.repository.findAllByCategory("engineering"));
        model.addAttribute("text","Engineering");
        return mv;
    }

    @GetMapping("/mathematics")
    public ModelAndView downloadMathematicsForm(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/ResourceHubDownload");
        model.addAttribute("resource",this.repository.findAllByCategory("mathematics"));
        model.addAttribute("text","Mathematics");
        return mv;
    }

    @GetMapping("/resources-upload")
    public ModelAndView initCreateForm(Map<String,Object> model){
        model.put("ResourceHub",new ResourceHubDto());
        ModelAndView mv=new ModelAndView("ResourceHub/ResourceHubCreate");
        return mv;
    }

    @PostMapping("/documentUpload")
    public  String DocumentUpload(@RequestParam("document") MultipartFile file,@ModelAttribute("ResourceHub") ResourceHubDto resourceHubDto,Model model, RedirectAttributes ra) throws IOException {
        Optional<ResourceHub> name = this.repository.findByDocument_Name(resourceHubDto.getDocumentName());
        if (name.isPresent()){
            model.addAttribute("error","file with that name exist");
            return "ResourceHub/ResourceHubCreate";
        }
        ResourceHub document=new ResourceHub();
        document.setDocument_Name(resourceHubDto.getDocumentName());
        document.setCategory(resourceHubDto.getCategory());
        document.setSubCategory(resourceHubDto.getSubCategory());
        document.setContent(file.getBytes());
        document.setSize(file.getSize());
        document.setUploadTime(new Date());
        document.setCreatedBy(SecurityUtils.findUsername().get());
        document.setCreatedDate(LocalDateTime.now());
        this.repository.save(document);
        return "redirect:/resources-upload?success";
    }

    @GetMapping("/download")
    public  void downloadFile(@RequestParam("id")Integer id, HttpServletResponse response, RedirectAttributes ra) throws Exception {
        final Optional<ResourceHub> document = this.repository.findById(id);
        if(!document.isPresent()){
            throw new Exception("could not find document with id "+id);
        }
         var doc=document.get();
        response.setContentType("application/octet-stream");
        String keyHeader="Content-Disposition";
        String valueHeader="attachment; filename="+doc.getDocument_Name();
        response.setHeader(keyHeader,valueHeader);
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(doc.getContent());
        outputStream.close();
    }

    @ModelAttribute("categoryList")
    public List<String> categoryList(){
       return Arrays.asList(Constants.category);
    }

    @ModelAttribute("subCategoryList")
    public List<String> subCategoryList(){
        return Arrays.asList(Constants.subCategory);
    }






}
