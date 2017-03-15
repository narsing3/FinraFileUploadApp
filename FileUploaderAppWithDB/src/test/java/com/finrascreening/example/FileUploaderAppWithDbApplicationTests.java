package com.finrascreening.example;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.finrascreening.example.domain.FileMeta;
import com.finrascreening.example.repository.FileRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class FileUploaderAppWithDbApplicationTests {

	@Autowired
	private FileRepository filerepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void contextLoads() {
	}
	
    @Test
    public void findAllByOwnerOrderByPostedOnDescTest() {

    	entityManager.persist(new FileMeta("TestFIle","Test Owner", new Long(666), new Date(), "user/narsing"));
        List<FileMeta> searchResults = filerepository.findAllByOwnerOrderByPostedOnDesc("Test Owner");
        assertTrue(searchResults.size()>0);
    }
     
    @Test
    public void findAllByNameOrderByPostedOnDescTest() {
    	entityManager.persist(new FileMeta("TestFIle","Test Owner", new Long(666), new Date(), "user/narsing"));
        List<FileMeta> searchResults = filerepository.findAllByNameOrderByPostedOnDesc("TestFIle");
        assertTrue(searchResults.size()>0);
    }
    
    @Test
    public void findAllBySizeLessThanTest() {
    	entityManager.persist(new FileMeta("TestFIle","Test Owner", new Long(300), new Date(), "user/narsing"));
        List<FileMeta> searchResults = filerepository.findAllBySizeLessThan(new Long(666));
        assertTrue(searchResults.size()>0);
    } 
    
    

}
