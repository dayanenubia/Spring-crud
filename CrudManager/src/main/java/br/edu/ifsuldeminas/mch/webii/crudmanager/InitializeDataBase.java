package br.edu.ifsuldeminas.mch.webii.crudmanager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.dao.UserRepository;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User dayane = new User();
		dayane.setName("Dayane");
		dayane.setEmail("dayane@gmail.com");
		dayane.setGender("F");
		
		User emily = new User();
		emily.setName("Emily");
		emily.setEmail("emily@gmail.com");
		emily.setGender("F");
		
		User sabrina = new User();
		sabrina.setName("Sabrina");
		sabrina.setEmail("sabrina@gmail.com");
		sabrina.setGender("F");
		
		userRepository.save(dayane);
		userRepository.save(emily);
		userRepository.save(sabrina);
		
		List<User> users = userRepository.findAll();
		
		for(User user : users) {
			System.out.println(user.getName());
			System.out.println(user.getEmail());
			System.out.println(user.getGender());
		}
		
	}
}
