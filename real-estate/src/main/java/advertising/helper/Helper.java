package advertising.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Helper {

/*	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	*/
	
	public static String CONTENT_TYPE;

    @Value("${content-type}")
    public void setDatabase(String contentType) {
    	CONTENT_TYPE = contentType;
    }

//    @PostConstruct
//    public void init() {
//    	User user = new User();
//    	user.setUsername("test");
//    	user.setPassword("testpass");
//    	user.setFirstName("nebitno");
//    	user.setLastName("nebitno");
//    	user.setEmail("femf@gmaicl.com");
//    	user.setPhone("nebitno");
//    	Set<Role> roles = new HashSet<>();
//    	roles.add(roleRepository.findOne(1L));
//    	user.setRoles(roles);
//    	userService.save(user);
    	
    	
//    	Category c1 = new Category();
//    	c1.setName("stan");
//    	Category c2 = new Category();
//    	c2.setName("kuca");
//    	Category c3 = new Category();
//    	c3.setName("lokal");
//    	Category c4 = new Category();
//    	c4.setName("govno");
//    	List<Category> cas = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
//    	for (Category c : cas) {
//    		categoryService.save(c);
//    	}
//    }
}
