package obrien.Project.three.controller;


import obrien.Project.three.dto.LibraryDto;
import obrien.Project.three.dto.ResourceHubDto;
import obrien.Project.three.entity.Library;
import obrien.Project.three.entity.ResourceHub;
import obrien.Project.three.repository.LibraryRepository;
import obrien.Project.three.utils.Constants;
import obrien.Project.three.utils.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ELibraryController {


    private final LibraryRepository libraryRepository;

    public ELibraryController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }


    @GetMapping("/library")
    public String initForm(){
       return "ResourceHub/LibraryDetails";
    }

    @GetMapping("/chemistry-lib")
    public ModelAndView initChemistry(Model model){
     ModelAndView mv=new ModelAndView("ResourceHub/LibraryDownload");
     model.addAttribute("text","E-Chemistry");
     model.addAttribute("resource",this.libraryRepository.findAllBySubject("chemistry"));
     return mv;
    }

    @GetMapping("/biology-lib")
    public ModelAndView initBiology(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/LibraryDownload");
        model.addAttribute("text","E-Biology");
        model.addAttribute("resource",this.libraryRepository.findAllBySubject("biology"));
        return mv;
    }

    @GetMapping("/mathematics-lib")
    public ModelAndView initMathematics(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/LibraryDownload");
        model.addAttribute("text","E-Mathematics");
        model.addAttribute("resource",this.libraryRepository.findAllBySubject("mathematics"));
        return mv;
    }

    @GetMapping("/physics-lib")
    public ModelAndView initPhysics(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/LibraryDownload");
        model.addAttribute("text","E-Physics");
        model.addAttribute("resource",this.libraryRepository.findAllBySubject("physics"));
        return mv;
    }

    @GetMapping("/Computer-Science-lib")
    public ModelAndView initComputerScience(Model model){
        ModelAndView mv=new ModelAndView("ResourceHub/LibraryDownload");
        model.addAttribute("text","E-Computer Science");
        model.addAttribute("resource",this.libraryRepository.findAllBySubject("Computer Science"));
        return mv;
    }

    @GetMapping("/book-upload")
    public ModelAndView initUploadBook(Model model){
        model.addAttribute("library",new LibraryDto());
        ModelAndView mv=new ModelAndView("ResourceHub/LibraryCreate");
        return mv;
    }

    @PostMapping("/documentUpload-book")
    public  String DocumentUpload(@RequestParam("document") MultipartFile file, @ModelAttribute("library") LibraryDto libraryDto, Model model, RedirectAttributes ra) throws IOException {
        Library document=new Library();
        document.setBookName(libraryDto.getBookName());
        document.setSubject(libraryDto.getSubject());
        document.setAuthor(libraryDto.getAuthor());
        document.setDatePublish(libraryDto.getDatePublish());
        document.setContent(file.getBytes());
        document.setSize(file.getSize());
        document.setUploadTime(new Date());
        document.setCreatedBy(SecurityUtils.findUsername().get());
        document.setCreatedDate(LocalDateTime.now());
        this.libraryRepository.save(document);
        return "redirect:/book-upload?success";
    }

    @GetMapping("/download-book")
    public  void downloadFile(@RequestParam("id")Integer id, HttpServletResponse response, RedirectAttributes ra) throws Exception {
        final Optional<Library> document = this.libraryRepository.findById(id);
        if(!document.isPresent()){
            throw new Exception("could not find document with id "+id);
        }
        var doc=document.get();
        response.setContentType("application/octet-stream");
        String keyHeader="Content-Disposition";
        String valueHeader="attachment; filename="+doc.getBookName();
        response.setHeader(keyHeader,valueHeader);
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(doc.getContent());
        outputStream.close();

    }

    @ModelAttribute("subCategoryList")
    public List<String> subCategoryList(){
        return Arrays.asList(Constants.subCategory);
    }

}
