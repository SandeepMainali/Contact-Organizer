package org.example.contacts.Controller;

import org.example.contacts.Model.Details;
import org.example.contacts.Repository.contactRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/call")
public class MainController {

    @Autowired
    contactRepo ContactRepo;


    @PostMapping("/contact")
    public ResponseEntity<String> CreatedNewContact(@RequestBody Details details){
        ContactRepo.save(details);
        return new ResponseEntity<>("Created Success ", HttpStatus.OK);

    }

    @GetMapping("/contact")
    public ResponseEntity<List<Details>> getAllContact(){
        List<Details> DetailsList= new ArrayList<>();
        ContactRepo.findAll().forEach(DetailsList::add);
        return new ResponseEntity<List<Details>>(DetailsList,HttpStatus.OK);
    }

    @GetMapping("/contact/{ctcid}")
    public  ResponseEntity<Details> getContactById(@PathVariable int ctcid){
        Optional<Details>  dtd=ContactRepo.findById(ctcid);
        if(dtd.isPresent()){
            return new ResponseEntity<Details>(dtd.get(),HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(dtd.get(),HttpStatus.NOT_FOUND);

        }

    }

    @PutMapping("/contact/{ctdid}")
    public ResponseEntity<String> UpdateById(@PathVariable int ctdid,@RequestBody Details details){
        Optional<Details> dtd=ContactRepo.findById(ctdid);
        if(dtd.isPresent()){
            Details exist = dtd.get();
            exist.setName(details.getName());
            exist.setMobileno(details.getMobileno());
            exist.setAddress(details.getAddress());
            ContactRepo.save(exist);
            return new ResponseEntity<>("Contact Details of ID " +ctdid+"Updated",HttpStatus.OK);


        }
        else {
            return new ResponseEntity<>("Contact Details of ID " +ctdid+"Not_FOUND",HttpStatus.OK);
        }

    }

    @DeleteMapping("/contact/{ctdid}")
    public ResponseEntity<String> DeleteById(@PathVariable int ctdid){
        ContactRepo.deleteById(ctdid);
        return new ResponseEntity<>("Contact Delete of ID "+ ctdid ,HttpStatus.OK);

    }

    @DeleteMapping("/contact")
        public ResponseEntity<String> deleteAllContact () {
            ContactRepo.deleteAll();
            return new ResponseEntity<>("All Contact are Deleted From Database", HttpStatus.OK);

        }

    @GetMapping(value = "/openpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ContactReport()  throws IOException {
        List<Details> details = (List<Details>) ContactRepo.findAll();


        ByteArrayInputStream bis = DatabasePDF.DeatailReport(details);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename= SandeepMainali.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
