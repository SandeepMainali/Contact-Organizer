package org.example.contacts.Repository;


import org.example.contacts.Model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  contactRepo extends JpaRepository<Details,Integer> {
    //create repo
}
